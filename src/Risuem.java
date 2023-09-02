import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Risuem extends Applet implements MouseListener, MouseMotionListener {
    private final static int
    BLACK = 0,
    RED = 1,
    GREEN = 2,
    BLUE =3,
    CYAN = 4,
    MAGENTA = 5,
    YELLOW = 6;
    private int currentColor = BLACK; // Текущий цвет рисования
    /*
    Переменные, определенные в этом фрагменте кода,
    используются при рисовании
     */
    private int prevX, prevY; // Предыдущее положение мыши
    private boolean dragging;  // true, если петаскивание еще
                        // не завершилось
    private Graphics graphicsForDrawing;  // Графический контекст,
                        // Используемый для Рисования Кривых.
    public void init() {
        // Прослушиватель Событий Мыши
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    public void update(Graphics g) {
        // Переопределение метода update
        paint(g);
    }

//    @Override
    public void paint(Graphics g) {
        // Рисование Содержимого Апплета
        int width = getSize().width; // Ширина
        int height = getSize().height; // Высота
        int colorSpacing = (height - 56) / 7; // Вычисление размера
                                    // Квадратлв Палитры
        /*
        Заполнение белым цветом области Рисования.
        Оставляем по три пиксела для границ и 56 пикселов для палитры.
         */
        g.setColor(Color.white);
        g.fillRect(3, 3, width - 59, height - 6);
        // Рисование границ
        g.setColor(Color.gray);
        g.drawRect(0, 0, width-1, height-1);
        g.drawRect(1, 1, width-3, height-3);
        g.drawRect(3, 2, width-5, height-4);
        // Серый треугольник справа шириной в 56 Пикселов.
        g.fillRect(width - 56, 0, 56, height);
        // Кнопка "Ochistim"
        g.setColor(Color.white);
        g.fillRect(width-53, height-54, 50, 50);
        g.setColor(Color.black);
        g.drawRect(width-53, height-53, 49, 49);
        g.drawString("Ochistim", width-48, height-23);
        // 7 Цветных Прямоугольников Паллитрв.
        g.setColor(Color.black);
        g.fillRect(width-53, 3 + 0*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.red);
        g.fillRect(width-53, 3 + 1*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.green);
        g.fillRect(width-53, 3 + 2*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.blue);
        g.fillRect(width-53, 3 + 3*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.cyan);
        g.fillRect(width-53, 3 + 4*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.magenta);
        g.fillRect(width-53, 3 + 5*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.yellow);
        g.fillRect(width-53, 3 + 6*colorSpacing, 50, colorSpacing-3);
        //  Граница Толщиной 2 Пиксела вокруг цветных Прямоугольнеков Палитры.
        g.setColor(Color.white);
        g.drawRect(width-55, 1 + currentColor*colorSpacing, 53, colorSpacing);
        g.drawRect(width-54, 2 + currentColor*colorSpacing, 51, colorSpacing-2);
    }

    public void changeColor(int y) {
        // Изменение цвета после щелчка на прямоугольнике политры.
        int width = getSize().width;  // Ширина.
        int height = getSize().height; // Высота
        int colorSpacing = (height - 56) / 7;  // Высота одного прямоугольника палитры.
        int newColor = y / colorSpacing;  // Номер выбранного цвета.
        if (newColor < 0 || newColor > 6)  // Проверка номера цвета.
            return;
        /*
        Удаление подсветки с прямоугольеика старого цвета.
        Изменение текущего цвета рисования.
        Рисование подсветки вокруг прямоугольника нового цвета.
         */
        Graphics g = getGraphics();
        g.setColor(Color.gray);
        g.drawRect(width-55, 1 + currentColor*colorSpacing, 53, colorSpacing);
        g.drawRect(width-54, 2 + currentColor*colorSpacing, 51, colorSpacing-2);
        currentColor = newColor;
        g.setColor(Color.white);
        g.drawRect(width-55, 1 + currentColor*colorSpacing, 53, colorSpacing);
        g.drawRect(width-54, 2 + currentColor*colorSpacing, 51, colorSpacing-2);
        g.dispose();
    }  // end changeColor()
    private void setUpDrawingGraphics() {
        // Функция вызывается в mousePressed при щелчке на
        // Прямоугольнике цветовой Палитры и
        // Задает графический контекст.
        graphicsForDrawing = getGraphics();

        switch(currentColor) {
            case BLACK:
                graphicsForDrawing.setColor(Color.black);
                break;
            case RED:
                graphicsForDrawing.setColor(Color.red);
                break;
            case GREEN:
                graphicsForDrawing.setColor(Color.green);
                break;
            case BLUE:
                graphicsForDrawing.setColor(Color.blue);
                break;
            case CYAN:
                graphicsForDrawing.setColor(Color.cyan);
            case MAGENTA:
                graphicsForDrawing.setColor(Color.magenta);
                break;
            case YELLOW:
                graphicsForDrawing.setColor(Color.yellow);
                break;
        }
    }

public void mousePressed(MouseEvent evt) {
    // Вызывается при нажатии кнопки мыши в апплете, а также
    // При рисовании, смене цвцта и очистке области рисования.
    int x = evt.getX();  // Горизонтальная координата щелчка.
    int y = evt.getY();  // Вертикальная координата щелчка.
    int width = getSize().width;  // Ширина Апплета.
    int height = getSize().height;  // Высота Апплета.
    if (dragging == true) return;
    if (x > width - 53) {
        // Щелчок Мыши Правее Области Рисования
        if (y > height - 53)
            repaint();  // Щелчок на кнопке Очисткм.
        else
            changeColor(y);  // Поменять Цвет Рисования.
    }
    else if (x > 3 && x < width - 56 && y > 3 && y < height - 3) {
        // Щелчок в Области Рисования
        // Начало рисования кривой от Точки (х,у).
        prevX = x;
        prevY = y;
        dragging = true;
        setUpDrawingGraphics();
    }
}
public void mouseReleased(MouseEvent evt) {
    if (dragging == false)
        return;
    dragging = false;
    graphicsForDrawing.dispose();
    graphicsForDrawing = null;
}
public void mouseDragged(MouseEvent evt) {
    // Вызывается при перемещении мыши с удерживаемой нажатой кнопкой.
    if (dragging == false)
        return;
    int x = evt.getX();  // Горизонтальная координата мыши.
    int y = evt.getY();  // Вертикальная координата мыши.
    if (x < 3)  // Проверка координат.
        x = 3;
    if (x > getSize().width - 57)
        x = getSize().width - 57;
    if (y < 3)
        y = 3;
    if (y > getSize().height -4)
        y = getSize().height - 4;
    graphicsForDrawing.drawLine(prevX, prevY, x, y);  // Прорисовка линии
    prevX = x;
    prevY = y;
}

public void mouseEntered(MouseEvent evt) {}
public void mouseExited(MouseEvent evt) {}
public void mouseClicked(MouseEvent evt) {}
public void mouseMoved(MouseEvent evt) {}


//
//    @Override
//    public void mouseDragged(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseMoved(MouseEvent e) {
//
//    }
}
