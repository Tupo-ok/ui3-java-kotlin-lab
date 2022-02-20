# Lab Java and Kotlin 

# Lab 1
## Задание #1. Знакомство с языком Java

На этой неделе начнем изучение основ синтаксиса языка Java с решения нескольких
простых программных проблем. Вы также научитесь использовать компилятор Java и
виртуальную машину Java для запуска ваших программ. Ниже приведено описание задач,
которые вам необходимо решить:
Простые числа
Простые числа часто встречаются в задачах по программированию и наш курс не
исключение! Для начала вы должны сделать программу, которая находит и печатает все
простые числа меньше 100. Эта задача даст вам возможность попрактиковаться в
написании циклов и функций на языке Java.
Создайте файл Primes.java, и в этом файле создайте класс как этот:
```java
/**
* TODO: Комментарии, описывающие ваш класс
  */
  public class Primes {
    /**
     * TODO: Комментарии, описывающие этот метод
     */
    public static void main(String[] args) {
        // TODO: здесь напишите свой код
    }
}
  ```
  После создания файла вы можете откомпилировать и запустить программу, но конечно
  пока еще рано делать это. Сначала надо добавить нужный код.
  Внутри класса, после метода main(), добавьте функцию isPrime(int n) которая определяет,
  является ли число простым. Можно считать, что входное значение n всегда больше 2.
  Сигнатура этой функции будет такой:
```java
  public static boolean isPrime(int n){
        // TODO: ваша реализация функции
  }
  ```
  Вы можете реализовать этот метод так, как считаете нужным, но довольно просто сделать
  это с помощью цикла for который перебирает все значения, начиная с 2 до (но, не
  включая) n, проверяя, делится ли n нацело на это значение. Это можно проверить с
  помощью оператора деления по модулю %; например, 17 % 7 равно 3, а 16 % 4 равно 0.
  Если какое либо значение нацело делит аргумент n, return false; если такое значение не
  найдено, то аргумент это простое число: return true. (Оператор return в языке Java
  завершает исполнение метода и возвращает значение вызывающей программе.)
  После того как этот метод написан, вы можете добавить в метод main() другой цикл,
  который перебирает числа от 2 до 100, включительно, печатая числа
  которые isPrime() определяет как простые.
  Откомпилируйте программу и проверьте корректность ее работы! В сети Интернет можно
  найти много таблиц простых чисел, так что проверить результат работы программы будет
  несложно.

Так же там где это указано в заготовке программы добавьте комментарии описывающие
назначение класса и каждого из методов класса. Очень важно вставлять такие
комментарии в свои программы.
Палиндромы
Вторая ваша программа должна определять является ли строка палиндромом. Есть
несколько способов решения этой задачи, но мы выберем тот, который позволит вам
познакомится на практике со свойствами языка Java, которые обсуждались на первой
лекции.
Для этой программы, вы должны создать класс с именем Palindrome, в
файле Palindrome.java. На этот раз можете начать с такого исходного текста:
```java
/**
* TODO: Комментарии с описанием класса
  */
  public class Palindrome {
    /**
     * TODO: Комментарии с описанием метода
     */
    public static void main(String[] args) {
        for(int i = 0; i < args.length; i++) {
            String s = args[i];
            // TODO: определить палиндром ли это и вывести на консоль
            // результат. Поместите s в двойные кавычки при выводе
            // на консоль.
        }
    }
}
  ```
  Как и раньше вы можете сразу откомпилировать и запустить эту программу, хотя в таком
  виде она не покажет ничего интересного.
  Ваша первая задача заключается в том, чтобы создать метод, переставляющий в обратном
  порядке символы в строке. Сигнатура у метода должна быть такая:
```java
  public static String reverseString(String s)
  ```
  Вы можете реализовать этот метод, используя локальную переменную, которая в начале
  инициализируется как пустая строка "", и затем к ней добавляются символы входной
  строки в обратном порядке Объект. String имеет метод length() который возвращает длину
  строки, и метод charAt(int index) который возвращает символ в указанной позиции.
  Индекс должен меняться от 0 до size - 1. Например:
```java
  String s = "pizzeria";
  System.out.println(s.length()); // печатает 8
  System.out.println(s.charAt(5)); // печатает r
  ```
  Можете использовать оператор соединения строк +, или составной оператор
  присвоения += если вам так больше нравится.
  Закончив реализацию метода reverseString(), можете создать другой метод public static
  boolean isPalindrome(String s). Эти методы нужны для того чтобы сделать перестановку
  символов строки s, а затем сравнить ее с исходной строкой. Для проверки равенства

строк String s (или любых других объектов Java), можно использовать
метод equals(Object). Например:
```java
String s1 = "hello";
String s2 = "Hello";
String s3 = "hello";
s1.equals(s2); // равно false
s1.equals(s3); // равно true
```
Не используйте оператор == для сравнения строк! Этот оператор выполняет совсем
другое сравнение в языке Java, о чем мы поговорим в следующей лекции.
Когда закончите писать свой код, откомпилируйте и проверьте вашу программу! На этот
раз передайте программе входные параметры через командную строку, вот так:
java Palindrome madam racecar apple kayak song noon
Ваша программа должна вывести в ответ является ли палиндромом каждое из указанных
слов. Как и раньше добавьте комментарии содержащие описание класса и всех методов в
программе.
Все готово!
Выполнив работу, отправьте файлы .java в moodle для проверки! Не надо отсылать
файлы .class.
Дополнительное задание!
Если у вас появилось желание усовершенствовать вашу программу, сделайте
преобразование строк в нижний регистр и фильтрацию знаков препинания перед
сравнением строк. Есть много фраз палиндромов и будет неплохо добавить в вашу
программу возможность их определения. Например: "Was it a rat I saw?" Or, "Able was I,
ere I saw Elba."
Для идентификации символов пунктуации, букв или пробела, Java имеет много полезных
методов в классе java.lang.Character. Можете познакомиться с этими методами
в документации Java API. Эти методы вызываются так: Character.isLetter(someChar)
Если вы добавите к своей программе возможность анализировать фразы, ее придется
запускать немного иначе. Вы должны заключать фразы в командной строке в двойные
кавычки:
java Palindrome "A man, a plan, a canal -- Panama!"
За выполнение дополнительного задания вы получите дополнительные баллы, но только в
случае если программа будет работать корректно. Для вас это хорошая возможность
попрактиковаться в написании программ на языке Java.

# Lab 2

## Задание #2. Формула Герона

Задание #2: Усложняем задачу
Java объектно-ориентированный язык. В каждом исходном файле .java может быть
объявлен один класс. Класс можно назвать шаблоном для создания объектов. Ниже
показан код простого класса, который описывает точку в двумерном пространстве:
```java
/**
* Точка в двумерном пространстве.
  **/
  public class Point2d {
    /** Координата X точки **/
    private double xCoord;
    /** координата Y точки **/
    private double yCoord;

    /** Конструктор инициализирующий начальное значение координат точки (x,
     y). **/
    public Point2d(double x, double y) {
        xCoord = x;
        yCoord = y;
    }

    /** конструктор без аргументов: по умолчанию точка ставится в начало
     координат. **/
    public Point2d() {
        // Вызываем конструктор с двумя аргументами для инициализации позиции.
        this(0, 0);
    }

    /** Возвращает координату X точки. **/
    public double getX() {
        return xCoord;
    }

    /** Возвращает координату Y точки. **/
    public double getY() {
        return yCoord;
    }

    /** Изменяет координату X точки. **/
    public void setX(double val) {
        xCoord = val;
    }

    /** Изменяет координату Y точки. **/
    public void setY(double val) {
        yCoord = val;
    }
}
  ```
  Этот код должен храниться в файле с именем Point2d.java, по правилам именования
  классов и файлов принятым в языке Java. Копия этого файла нужна вам для выполнения
  этой лабораторной работы. Перепишите ее на ваш компьютер.
  Напомним, что экземпляр класса создается с помощью конструктора в любом месте кода:
```java
Point2d myPoint = new Point2d(); // создает точку в (0,0)
Point2d myOtherPoint = new Point2d(5,3); // создает точку в (5,3)
Point2d aThirdPoint = new Point2d();
```
Будьте внимательны: myPoint != aThirdPoint, даже если их значения одинаковы! Это
происходит потому что оператор сравнения == (и обратный ему оператор !=) проверяют
равенство двух ссылок на объекты. Иначе говоря, == возвращает true если две ссылки
указывают на один и тот же объект. В этом примере,
переменные myPoint и aThirdPoint указывают на разные экземпляры класса Point2d,
следовательно, myPoint == aThirdPoint возвращает false, даже если координаты точек
одинаковы!
Для проверки равенства значений а не ссылок, в классе Point2d нужен метод equals,
который получает через аргумент другой объект (Object), и выполняет нужную проверку
на равенство. Напомним, что типы сравниваемых объектов должны совпадать.
Перед тем отдавать свой код...
Стиль программирования очень важен в любом программном проекте, над которым вы
работаете. Можете верить этому или нет, но большая часть, рабочего времени в
программировании тратится на отладку и сопровождение. На этих этапах жизненного
цикла программ хорошо документированный и читаемый код становится очень важным
фактором экономии времени.
К сожалению, осознание важности хорошего стиля программирования часто приходит
только через болезненный опыт... Но курс, CS11 дает возможность изучить хороший
стиль программирования и попрактиковаться в нем. Перед тем как приступить к работе,
просмотрите руководство по стилю написания программ на языке Java. Имеется также
программа проверки стиля, которая печатает замечания по стилю программирования
вашего кода.
Ваша задача:
Создать новый класс Point3d который описывает, как вы вероятно уже догадались, точку в
трехмерном евклидовом пространстве. Этот класс должен обеспечивать следующий
функционал:
<ul>
<li>Создание новой точки Point3d с тремя указанными значениями координат
(типа double)</li>
<li>Создание новой точки Point3d в позиции (0.0, 0.0, 0.0) по умолчанию,</li>
<li>Чтение и изменение этих трех значений по отдельности, и </li>
<li> Сравнение двух точек Point3ds на равенство значений координат с помощью
метода equals. </li>
</ul>
Внутренние поля объекта Point3d должны быть недоступны напрямую.
Далее, добавьте новый метод distanceTo, который получает другой объект типа Point3d как
аргумент, вычисляет расстояние по прямой до этого объекта, и возвращает это расстояние,
используя тип double.
Создайте второй класс с именем Lab1, который нужен главным образом для объявления
статического метода main. Вспомните, что метод main должен иметь модификатор

доступа public, иметь тип возвращаемого значения void , и иметь один аргумент – массив
элементов типа Strings. Добавьте в класс следующий функционал:
Пользовательский ввод трех троек значений, каждое из которых задает координаты одной
точки в трехмерном пространстве. Создайте из этих входных данных объекты Point3d.
(Пока что, будем считать, что пользователь всегда вводит корректные данные.)
Если вы не знаете, как получить данные от пользователя, найдите нужную функцию
в этом файле.
Сделайте из этой функции метод класса Lab1. Заметим, что этот метод использует классы
из пакета java.io, который не виден вашему коду по умолчанию. Добавьте в начало вашего
файла:
import java.io.*;
Эта строчка добавит классы из пакета java.io к коду класс Lab1. (Это не требуется делать
для классов из пакета java.lang потому что он добавляется к вашему коду автоматически)
Напишите второй статический метод computeArea который получает три точки Point3d's и
вычисляет площадь треугольника ограниченного этими точками. (Возможно, вам
потребуется для этого Формула Герона.) Верните эту площадь в значении типа double.
<li> Используйте данные и код, который вы собрали и написали сами для вычисления и
печати на экране площади треугольника. </li>

Перед вызовом computeArea, однако, проверьте на равенство значений все три
точки Point3d's. Если в какой либо паре точки “одинаковы”, сообщите об этом
пользователю и не вычисляйте площадь.
Откомпилируйте вместе оба ваши файла:
javac Point3d.java Lab1.java
и запустите вашу программу Lab1. Протестируйте ее несколькими простыми
треугольниками.
Когда закончите работу отправьте ее на проверку.