import org.w3c.dom.css.RGBColor;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ComboBoxUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;

//Класс-обработчик фракталов. Создает интерфейс работы с фракталами при помощи инструментов Swing.

public class FractalExplorer {
    private int displaySize;
    private JImageDisplay imageDisplay;
    private Rectangle2D.Double rect = new Rectangle2D.Double(); //Прямоугольник-отображение рабочей области

    //Создание объектов фрактала для выпадающего списка
    private Mandelbrot mandelbrot = new Mandelbrot();
    private Tricorn tricorn = new Tricorn();
    private BurningShip burningShip = new BurningShip();

    private FractalGenerator fractalGenerator = mandelbrot; //При включении программы стандартно отображается фрактал Мандельброта
    private JFrame jfrm = new JFrame("Fractal Explorer"); //Ящик, хранящий все элементы программы
    private SwingWorker[] workerArray; //Массив для работника прорисовки каждой строки
    private int unDrawnLineCount; //Количество непрорисованных линий изображения

    //Создаем экземпляры всех нужных нам элементов
    private JButton resetbtn = new JButton();
    private JButton saveBtn = new JButton();
    private JPanel panelWithBtn = new JPanel();
    private JPanel jPanel = new JPanel();
    private JLabel jLabel = new JLabel();
    private JComboBox jComboBox = new JComboBox();

    FractalExplorer(int displaySize) {
        this.displaySize = displaySize;
        fractalGenerator.getInitialRange(rect);
        imageDisplay = new JImageDisplay(displaySize, displaySize);
        workerArray = new SwingWorker[displaySize];

    }

    /*Основной метод программы. Создает и показывает интерфейс. Создает и инициализирует все элементы Swing(кнопки перерисовки и сохранения,
    текстовое поле, выпадающий список), добавляет их в ящик, подключает слушателей.
     */
    public void createAndShowGUI() {
        jfrm.setSize(displaySize, displaySize);
        saveBtn.setText("Save image");
        resetbtn.setText("Reset Display");
        jLabel.setText("Select fractal type: ");
        jPanel.setBackground(Color.ORANGE);

        //Создаем слушателей, привязываем их к элементам
        MouseListener msListener = new MouseListener();
        ActionListener saveBtnListener = new SaveBtnListener();
        ActionListener btnListener = new BtnActionListener();
        ActionListener comboBoxListener = new ComboBoxListener();
        saveBtn.addActionListener(saveBtnListener);
        resetbtn.addActionListener(btnListener);
        jComboBox.addActionListener(comboBoxListener);
        jfrm.addMouseListener(msListener);

        //Добавляем элементы в ящик, панель с кнопками и выпадающий список
        jComboBox.addItem(mandelbrot);
        jComboBox.addItem(burningShip);
        jComboBox.addItem(tricorn);
        jPanel.add(jLabel);
        jPanel.add(jComboBox);
        panelWithBtn.add(resetbtn);
        panelWithBtn.add(saveBtn);
        panelWithBtn.setBackground(Color.orange);
        resetbtn.setFocusPainted(false);
        jfrm.add(imageDisplay, BorderLayout.CENTER);
        jfrm.add(panelWithBtn, BorderLayout.SOUTH);
        jfrm.add(jPanel, BorderLayout.NORTH);

        //Заканчиваем работу с ящиком, включаем его видимость
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.pack();
        jfrm.setVisible(true);
        jfrm.setResizable(false);
        jfrm.setLocationRelativeTo(null);
    }

    //Слушает кнопку перерисовки.
    public class BtnActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            imageDisplay.clearImage();
            fractalGenerator.getInitialRange(rect);
            imageDisplay.repaint();
            drawFractal();
        }
    }

    //Слушает кнопку сохранения. По идее должен запрещать сохранять "не png" картинки. В этом ему помогает фильтр.
    public class SaveBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
            fileChooser.setFileFilter(filter);
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.showSaveDialog(jfrm);
            try {
                ImageIO.write(imageDisplay.img, "PNG", fileChooser.getSelectedFile());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(jfrm, ex.getMessage(), "Error saving", JOptionPane.ERROR_MESSAGE);
            }
            ;
        }
    }

    //Слушает выпадающий список
    public class ComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox box = (JComboBox) e.getSource();
            fractalGenerator = (FractalGenerator) box.getSelectedItem();
            fractalGenerator.getInitialRange(rect);
            imageDisplay.repaint();
            drawFractal();
        }
    }

    //Слушает нажатие мышкой на экран. Масштабирает изображение (в соответствии с методом zoomAndZoomRange в 0.5 раз).
    public class MouseListener extends MouseAdapter implements java.awt.event.MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (unDrawnLineCount != 0) return;
                Point point = e.getPoint();
                double x = FractalGenerator.getCoord(rect.x, rect.x + rect.width, displaySize, point.x);
                double y = FractalGenerator.getCoord(rect.y, rect.y + rect.height, displaySize, point.y);
                fractalGenerator.recenterAndZoomRange(rect, x, y, 0.5);
                drawFractal();

        }
    }

    //Метод прорисовывает фрактал. Важно, что необходимо перевести размеры нашего экрана к нашей системе координат (метод .getCoord()).
    //Задает цвет пикселя на экране в соответствии с кол-вом итераций, полученных из метода .numIterations().
    public void drawFractal() {
        for (int i=0;i<displaySize;i++){
            unDrawnLineCount = displaySize;
            enableUI(false);
            workerArray[i] = new FractalWorker(i);
            workerArray[i].execute();
        }
        unDrawnLineCount = displaySize;
        enableUI(true);
    }

    //Создаем класс для многопоточности
    private class FractalWorker extends SwingWorker<Object, Object> {
        private int yLine;
        private int[] RGBColors;

        @Override
        protected Object doInBackground() throws Exception {
            RGBColors = new int[displaySize];
            for (int i = 0; i < displaySize; i++) {
                double xCoord = FractalGenerator.getCoord(rect.x, rect.x + rect.width, displaySize, i);
                double yCoord = FractalGenerator.getCoord(rect.y, rect.y + rect.height, displaySize, yLine);
                int color = fractalGenerator.numIterations(xCoord, yCoord);
                if (color == -1) RGBColors[i] = 0;
                else {
                    float hue = 0.7f + (float) color / 200f;
                    RGBColors[i] = Color.HSBtoRGB(hue, 1f, 1f);
                }
            }
            return null;
        }


        @Override
        protected void done() {
            for (int i = 0; i < displaySize; i++) {
                imageDisplay.drawPixel(i,yLine,RGBColors[i]);
            }
            imageDisplay.repaint(0,yLine,displaySize,1);
            unDrawnLineCount--;
        }

        FractalWorker(int y){
            yLine = y;
        }
    }

    //Метод отключает кнопки во время прорисовки фрактала (и включает после прорисовки)
    void enableUI(boolean val){
      resetbtn.setEnabled(val);
      saveBtn.setEnabled(val);
      jComboBox.setEnabled(val);
    }
}
/*
По моему мнению прогу еще нужно допилить. Фильтр не работает так, как необходимо. При нажатии на область с кнопками происходит масштабирование,
что нежелательно.
 */
