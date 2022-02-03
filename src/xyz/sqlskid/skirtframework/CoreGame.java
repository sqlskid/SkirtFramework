package xyz.sqlskid.skirtframework;

import xyz.sqlskid.skirtframework.display.Display;
import xyz.sqlskid.skirtframework.handlers.InputListener;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class CoreGame implements KeyListener, MouseListener, MouseMotionListener
{
    private int tickrate;
    private String title;
    private int width;
    private int height;
    private int scale;
    private Thread gameThread;

    private List<InputListener> inputListenerList = new ArrayList<>();

    private boolean running = false;

    private int currentFramerate;
    private int currentTickrate;

    public void registerInputListener(InputListener inputListener){
        this.inputListenerList.add(inputListener);
    }

    public CoreGame(String title, int width, int height, int scale, int tickrate){
        this.title = title;
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.tickrate = tickrate;
    }

    public synchronized void start(){
        running = true;
        gameThread = new Thread(this::run);
        gameThread.start();
    }

    public synchronized void stop(){
        running = false;
    }

    public Display display;

    public void run(){
        display = new Display(title,width,height,scale);
        display.getCanvas().addKeyListener(this);
        display.getCanvas().addMouseListener(this);
        display.getCanvas().addMouseMotionListener(this);


        long lastTime = System.nanoTime();
        double unprocessed = 0;
        double nsPerTick = 1000000000.0 / tickrate;
        long lastTime1 = System.currentTimeMillis();
        int frames = 0, ticks = 0;
        while (running)
        {
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerTick;
            lastTime = now;
            frames++;
            while (unprocessed >= 1) {
                ticks++;
                fixedUpdate();
                unprocessed -= 1;
            }

            if (System.currentTimeMillis() - lastTime1 > 1000) {
                lastTime1 += 1000;
                currentFramerate = frames;
                currentTickrate = ticks;
                ticks = 0;
                frames = 0;
            }

            fixedRender();
        }
    }

    public void render(Graphics2D graphics){}
    public void update() {}

    private void fixedUpdate() {
        update();
    }

    private void fixedRender(){
        BufferStrategy bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        Graphics2D graphics = (Graphics2D) bs.getDrawGraphics();

        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, width * scale, height * scale);

        render(graphics);

        graphics.dispose();
        bs.show();
    }

    public int getCurrentFramerate() {
        return currentFramerate;
    }

    public int getCurrentTickrate() {
        return currentTickrate;
    }

    public int getTickrate() {
        return tickrate;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getScale() {
        return scale;
    }

    public boolean isRunning() {
        return running;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        for(InputListener inputListener: inputListenerList){
            inputListener.onKeyType(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for(InputListener inputListener: inputListenerList){
            inputListener.onKeyPress(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for(InputListener inputListener: inputListenerList){
            inputListener.onKeyRelease(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(InputListener inputListener: inputListenerList){
            inputListener.onMouseClick(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(InputListener inputListener: inputListenerList){
            inputListener.onMousePress(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(InputListener inputListener: inputListenerList){
            inputListener.onMouseRelease(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        for(InputListener inputListener: inputListenerList){
            inputListener.onMouseEnter(e);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for(InputListener inputListener: inputListenerList){
            inputListener.onMouseExit(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for(InputListener inputListener: inputListenerList){
            inputListener.onMouseDrag(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(InputListener inputListener: inputListenerList){
            inputListener.onMouseMove(e);
        }
    }
}
