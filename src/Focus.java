/*
Апплет для демонстрации событий Клавиатуры и Устаровки фокуса.
Нажимая клавиши клавиатуры со стрелками, перемещать квадрат
    в соответствующие стороны.
Нажатие клавиш <R>, <G>, <B>, <K> Приводит к Изменению цвета Квадрата
(красный, зеленый, синий, черный соответственно).
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Focus extends JApplet implements KeyListener, FocusListener, MouseListener {
    // Прослушиватель MoustListener используется только для того,
    // чтобы апплет мог по щулчкш мыши установить на себе фокус.
    static final int SQUARE_SIZE = 40; // Сторона квадрата.
    Color squareColor;  // Цвет Квадрата.
    int squareTop, squareLeft; // Координаты Верхнего Левого Угла Квадрата.
    boolean focussed = false;  // true, если фокус на апплете.
    DisplayPanel canvas;  // Холст для рисования в апплете.

//    @Override
    public void init() {
//        super.init();
        // Инициализация Апплета.
        squareTop = getSize().height / 2 - SQUARE_SIZE / 2;
        squareLeft = getSize().width / 2 - SQUARE_SIZE / 2;
        squareColor = Color.red;
        canvas = new DisplayPanel(); // Создание Холста.
        setContentPane(canvas);
        canvas.setBackground(Color.white);  // Цвет фона холста.
        canvas.addFocusListener(this);
        canvas.addKeyListener(this);
        canvas.addMouseListener(this);
        class DisplayPanel extends JPanel {
            // Описание холста
            public void paintComponent(Graphics g) {
                super.paintComponent(g); // Заполнение холста Цветом Фона (Белый).
                /*
                Рисование границы, Толзиной в Три Пикселя. Цвет
                зависит от того, есть ли На Апплете фщкус.
                 */
                if (focussed)
                    g.setColor(Color.cyan);
                else
                    g.setColor(Color.lightGray);
                int width = getSize().width;  // Ширина Апплета.
                int height = getSize().height;  // Высота Аллдета.
                g.drawRect(0, 0, width-1, height-1);
                g.drawRect(1,1, width-3, height-3);
                g.drawRect(2,2, width-5,height-5);

                // Рисовать Квадрат.
                g.setColor(squareColor);
                g.fillRect(squareLeft, squareTop, SQUARE_SIZE, SQUARE_SIZE);

                // Вывод Сообщения о том, что Фокува Нет.
                if (!focussed) {
                    g.setColor(Color.magenta);
                    g.drawString("Navodim FOCUS", 7, 20);
                }
            }
        }

        // Методы Обработки Событий.
        public void focusGained(FocusEvent evt) {
            focussed = true;
            canvas.repaint();
        }

        public void focusLost(FocusEvent evt) {
            //  Фокус Потерян.
            focussed = false;
            canvas.repaint();  // Перерисовка Границ Апплета.
        }

        public void keyTyped(KeyEvent evt) {
            // Напечатан символ во время Удержания Фокуса.
            // Если Символ Соответствует Символу Задания Цвета,
            // то апплет меняет Цвет Квадрата.
            char ch = evt.getKeyChar();  // Символ.
            if (ch == 'B' || ch == 'b') {
                squareColor = Color.blue;
                canvas.repaint();
            }
            else if (ch =='G' || ch == 'g') {
                squareColor = Color.green;
                canvas.repaint();
            }
            else if (ch == 'R' || ch == 'r') {
                squareColor = Color.red;
                canvas.repaint();
            }
            else if (ch == 'K' || ch == 'k') {
                squareColor = Color.black;
                canvas.repaint();
            }
        }

        public void keyPressed (KeyEvent evt) {
            // Передвижение Квадрата При Нажатии
            // Клавиш Стрелок.
            int key = evt.getKeyCode();  // Код Нажатой Клавиши.
            if (key == KeyEvent.VK_LEFT) {
                squareLeft -= 8;
                if (squareLeft < 3)
                    squareLeft =3;
                canvas.repaint();
            }
            else if (key == KeyEvent.VK_RIGHT) {
                squareLeft += 8;
                if (squareLeft > getSize().width - 3 - SQUARE_SIZE)
                    squareTop = getSize().height - 3 - SQUARE_SIZE;
                canvas.repaint;
            }
            else if (key == keyEvent.VK_DOWN) {
                squareTop += 8;
                if (squareTop > getSize().height - 3 - SQUARE_SIZE)
                    squareTop = getSize().height - 3 - SQUARE_SIZE;
                canvas.repaint;
            }
        }
        public void keyReleased (keyEvent evt) {}
        public void mousePressed (mousePressed evt) {
            // При Щелчке на Апплете требуется установить
            // Фокус на Компоненте Холста
            canvas.requestFocus();
        }
        public void mouseEntered(MouseEvent evt) {}
        public void mouseExited(MouseEvent evt) {}
        public void mouseReleased(MouseEvent evt) {}
        public void mouseClicked(MouseEvent evt) {}

    }

    @Override
    public void focusGained(FocusEvent evt) {

    }

    @Override
    public void focusLost(FocusEvent evt) {

    }

    @Override
    public void keyTyped(KeyEvent evt) {

    }

    @Override
    public void keyPressed(KeyEvent evt) {

    }

    @Override
    public void keyReleased(KeyEvent evt) {

    }

    @Override
    public void mouseClicked(MouseEvent evt) {

    }

    @Override
    public void mousePressed(MouseEvent evt) {

    }

    @Override
    public void mouseReleased(MouseEvent evt) {

    }

    @Override
    public void mouseEntered(MouseEvent evt) {

    }

    @Override
    public void mouseExited(MouseEvent evt) {

    }
}
