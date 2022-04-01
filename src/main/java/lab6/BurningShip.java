import java.awt.geom.Rectangle2D;

//Класс, определяющий фрактал "Горящий корабль". Диапазон [-2,-2.5]-[2,1.5]. Формула z(n) = (Real(z(n-1))+i*Im(z(n-1)))^2+c.

public class BurningShip extends FractalGenerator{
    public void getInitialRange(Rectangle2D.Double range){ //Функция задает диапазон отображения
        range.x = -2;
        range.y = -2.5;
        range.height = 4;
        range.width = 4;
    }

    //Функция определяет кол-во итераций. От них зависит цвет. Если множество оказывается замкнутым, то цвет -черный
    public int numIterations(double x, double y){
        double ABSz = 0;
        double xCoord = 0;
        double yCoord = 0;
        double vspom = 0;
        int Iterations = 0;
        while ((ABSz*ABSz)<4.0){
            Iterations++;
            vspom = xCoord * xCoord - yCoord * yCoord + x;
            yCoord = 2*Math.abs(xCoord)*Math.abs(yCoord)+y;
            xCoord = vspom;
            ABSz = Math.sqrt(xCoord*xCoord+yCoord*yCoord);
            if (Iterations==MAX_ITERATIONS) {return -1;}
        }
        return Iterations;
    };

    //При выборе вида фрактала в JComboBox вызывается метод toString().
    public String toString() {
        return "Burning Ship fractal";
    }
}
