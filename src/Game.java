import java.awt.*;
import java.awt.event.*;

public class Game extends Framework {
    protected void doInitialization (int width, int height) {
        initSubmarine(width, height);
        initBoat(width, height);
        initBomb(width, height);
    }
    synchronized public void drawFrame(Graphics g, int width, int height){
        g.setColor(Color.green);
        g.fillRect(0, 0, width, height);
        doBoatFrame(g, width, height);
        doSubmarineFrame(g, width, height);
        doBombFrame(g, width, height);
    }
    synchronized public void keyPressed(KeyEvent evt) {
        int code = evt.getKeyCode();
        if (code == KeyEvent.VK_LEFT) {
            boatCenterX = -15;
        }
        else if (code == KeyEvent.VK_RIGHT) {
            boatCenterX = +15;
        }
        else if (code == KeyEvent.VK_DOWN) {
            if (!bombIsFalling)
                bombIsFalling = true;
        }
    }
    int bombCenterX;
    int bombCenterY;
    boolean bombIsFalling;
    void initBomb(int width, int height) {
        bombIsFalling = false;
        bombCenterY = 100;
    }
    void doBombFrame(Graphics g, int width, int height){
        if (bombIsFalling) {
            if (bombCenterY > height) {
                initBomb(width, height);
                bombCenterX = boatCenterX;
            }
            else if (Math.abs(bombCenterX - subCenterX) <= 36 &&
            Math.abs(bombCenterY - subCenterY) <= 21) {
                explosionFrameNumber = 1;
                initBomb(width, height);
                bombCenterX = boatCenterX;
            }
            else {
                bombCenterY += 10;
                g.setColor(Color.red);
                g.fillOval(bombCenterX - 8, bombCenterY - 8, 16, 16);
            }
        }
        else {
            bombCenterX = boatCenterX;
            g.setColor(Color.red);
            g.fillOval(bombCenterX - 8, bombCenterY - 8, 16,16);
        }
    }
    int boatCenterX;
    int boatCenterY;
    void initBoat(int width, int height){
        boatCenterX = width/2;
        boatCenterY = 80;
    }
    private void doBoatFrame(Graphics g, int width, int height) {
        if (boatCenterX < 0)
            boatCenterX = 0;
        else if (boatCenterX > width)
            boatCenterX = width;
        g.setColor(Color.blue);
        g.fillRoundRect(boatCenterX - 40, boatCenterY - 20, 80, 40, 20, 20);
    }
    int subCenterX;
    int subCenterY;
    boolean subIsMovingLeft;
    int explosionFrameNumber;

    void initSubmarine(int width, int height) {
        subCenterX = (int)(width*Math.random());
        subCenterY = height -40;
        explosionFrameNumber = 0;
        if (Math.random() < 0.5)
            subIsMovingLeft = true;
        else
            subIsMovingLeft = false;
    }
    private void doSubmarineFrame(Graphics g, int width, int height) {
        if (explosionFrameNumber > 0) {
            if (explosionFrameNumber == 14) {
                initSubmarine(width, height);
            }
            else if (explosionFrameNumber > 10) {
                explosionFrameNumber++;
            }
            else {
                g.setColor(Color.black);
                g.fillOval(subCenterX - 30, subCenterY -15, 60, 30);
                g.setColor(Color.yellow);
                g.fillOval(subCenterX - 4*explosionFrameNumber,
                        subCenterY - 2*explosionFrameNumber,
                        8*explosionFrameNumber,
                        4*explosionFrameNumber);
                g.setColor(Color.red);
                g.fillOval(subCenterX - 2*explosionFrameNumber,
                        subCenterY - explosionFrameNumber/2,
                        4*explosionFrameNumber,
                        explosionFrameNumber);
                explosionFrameNumber++;
            }
        }
        else {
            if (Math.random() < 0.04)
                subIsMovingLeft = !subIsMovingLeft;
            if (subIsMovingLeft) {
                subCenterX -= 5;
                if (subCenterX <= 0) {
                    subCenterX = 0;
                    subIsMovingLeft = false;
                }
            }
            else {
                subCenterX += 5;
                if (subCenterX > width) {
                    subCenterX = width;
                    subIsMovingLeft = true;
                }
            }
            g.setColor(Color.black);
            g.fillOval(subCenterX - 30, subCenterY - 15, 60, 30);
        }
    }

//    private void initBomb(int width, int height) {
//    }
//    private void initBoat(int width, int height) {
//    }
//    private void initSubmarine(int width, int height) {
//    }

}
