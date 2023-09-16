import java.applet.Applet;
import java.awt.*;

public class LinesMove extends Applet implements Runnable {
    boolean used = false; //Значение переменной used при первой ориентации
    // на курсор мыши становится равным true.
    int ROWS = 5; // Количество Рядов.
    int COLUMNS = 7; // количество Столбцов.
    int [][] angle; // Угол.
    int [][] driftSpeed; // Угловая скорость.
    long lastTime = 0; // Время последней Инициализации порядка.
    int delayToDrift =1500; // Задержка при упорядочении.
    int width = -1; // Ширина Апплета.
    int height = -1; // Высота Апплета.
    int hSpace; // Горизонтальное Расстояние между Линиями.
    int vSpace; // Вертикальное Расстояние между Линиями.
    int space; // Ширина Каждой Линии.
    int last_x = -1; // Координаты Точки, на которую Линии были
    int last_y = -1; // Ориентированы в Прошлый Раз.
    int sleepTime = 100; // Задержка между Фреймами.
    Thread runner = null; // Поток Анимации.
    Image OSC = null; // Невидимый Холст для Двойной Буферизации.
    Graphics OSCGraphics;
    public void init() {
        setBackground(Color.black);
        setForeground(Color.white);
        String param;
        param = getParameter("rows");
        if (param != null) {
            try{
                ROWS = Integer.parseInt(param);
            }
            catch(NumberFormatException e) { }
        }
        param = getParameter("columns");
        if (param != null) {
            try{
                COLUMNS = Integer.parseInt(param);
            }
            catch(NumberFormatException e) { }
        }
        angle = new int[ROWS][COLUMNS];
        driftSpeed = new int[ROWS][COLUMNS];
        for (int i = 0; i <ROWS; i++)
            for (int j =0; j<COLUMNS; j++) {
                angle[i][j] = (int)(360*Math.random());
                driftSpeed[i][j] = (int)(41*Math.random())-20;
            }
    }
    public void start() {
        if (runner == null) {
            int h = getSize().height;
            int w = getSize().width;
            if (h != height || w != width)
                doResize(w,h);
            runner = new Thread(this);
            runner.start();
        }
    }
    public void stop() {
        // Остановка Анимации.
        if (runner != null) {
            runner.stop();
            runner.null;
        }
    }

}
