//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//public class Framework extends JApplet implements ActionListener, KeyListener, FocusListener, MouseListener {
//    protected void doInitialization(int width, int height){}
//    protected void drawFrame(Graphics g, int width, int height) {
//        g.setColor(Color.lightGray);
//        g.fillRect(0, 0, width, height);
//        g.setColor(Color.black);
//        g.drawString("Elipsed Time: " + (getElapsedTime()/1000), 10, 20);
//        g.drawString("Frame Number: " + (getFrameNumber()), 10, 35);
//    }
//
//    public int getFrameNumber() {return frameNumber;}
//    public void setFrameNumber(int frameNumber) {
//        if (frameNumber < 0)
//            this.frameNumber = 0;
//        else
//            this.frameNumber = frameNumber;
//    }
//
//    private long getElapsedTime() {return elapsedTime;}
//    public void setFrameCount(int max) {
//        if (max <= 0)
//            this.frameCount = -1;
//        else
//            frameCount = max;
//    }
//    public void setMillisecondsPerFrame(int time) {
//        millisecondsPerFrame = time;
//        if (timer != null)
//            timer.setDelay(millisecondsPerFrame);
//    }
//    public void setFocusBorderColor(Color c) {
//        focusBorderColor = c;
//    }
//    private int frameNumber = 0;
//    private int frameCount = -1;
//    private int millisecondsPerFrame = 40;
//    private long startTime;
//    private long oldElapsedTime;
//    private long elapsedTime;
//    private Timer timer;
//    private JPanel frame;
//    private boolean focussed = false;
//    Color focusBorderColor = Color.cyan;
//    public Framework(){
//        frame = new JPanel() {
//            public void paintComponent(Graphics g) {
//                int width = getSize().width;
//                int height = getSize().height;
//                drawFrame(g, width, height);
//                if (focussed)
//                    g.setColor(focusBorderColor);
//                else
//                    g.setColor(Framework.this.getBackground());
//                g.drawRect(0, 0, width-1, height-1);
//                g.drawRect(1,1, width-3, height-3);
//                g.drawRect(2,2,width-5, height-5);
//                if (!focussed) {
//                    g.setColor(Framework.this.getForeground());
//                    g.drawString("Click to activate", 10, height-12);
//                }
//            }
//        };
//        setContentPane(frame);
//        setBackground(Color.gray);
//        setForeground(Color.red);
//        frame.setFont(new Font("SanSerif", Font.BOLD, 14));
//        frame.addFocusListener(this);
//        frame.addKeyListener(this);
//        addMouseListener(this);
//    }
//    public void init() {
//        doInitialization(getSize().width, getSize().height);
//    }
//    @Override
//    public void actionPerformed(ActionEvent evt) {
//        frameNumber++;
//        if (frameCount > 0 && frameNumber >= frameCount)
//            frameNumber = 0;
//        elapsedTime = oldElapsedTime + (System.currentTimeMillis() - startTime);
//        frame.repaint();
//    }
//    private void startAnimation() {
//        if (focussed) {
//            if (timer == null){
//                timer = new Timer(millisecondsPerFrame, this);
//                timer.start();
//            }
//            else
//                timer.restart();
//            startTime = System.currentTimeMillis();
//        }
//    }
//    private void stopAnimation() {
//        if (focussed) {
//            oldElapsedTime += (System.currentTimeMillis() - startTime);
//            elapsedTime = oldElapsedTime;
//            frame.repaint();
//            timer.stop();
//        }
//    }
//    public void start() {
//        startAnimation();
//    }
//    public void stop() {
//        stopAnimation();
//    }
//
//    @Override
//    public void focusGained(FocusEvent evt) {
//        focussed = true;
//        startAnimation();
//    }
//
//    @Override
//    public void focusLost(FocusEvent evt) {
//        stopAnimation();
//        focussed = false;
//    }
//
//    @Override
//    public void mousePressed(MouseEvent evt) {
//        frame.requestFocus();
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent evt) {}
//
//    @Override
//    public void mouseExited(MouseEvent evt) {}
//
//    @Override
//    public void keyReleased(KeyEvent evt) {}
//
//    @Override
//    public void mouseClicked(MouseEvent evt) {}
//    @Override
//    public void keyTyped(KeyEvent e) {}
//
//    @Override
//    public void keyPressed(KeyEvent evt) {}
//
//    @Override
//    public void mouseReleased(MouseEvent e) {}
//
//}
