package xyz.sqlskid.skirtframework.display;

import javax.swing.*;
import java.awt.*;

public class Display {

    public JFrame jFrame;
    public Canvas canvas;

    private String title;

    public Display(String title,int width, int height, int scale){
        this.title = title;

        resizeDisplay(width,height,scale);
    }

    public void resizeDisplay(int width, int height, int scale){
        if(jFrame != null){
            jFrame.dispose();
        }

        this.jFrame = new JFrame(title);
        this.jFrame.setSize(new Dimension(width * scale, height * scale));
        this.jFrame.setMinimumSize(new Dimension(width * scale, height * scale));
        this.jFrame.setPreferredSize(new Dimension(width * scale, height * scale));
        this.jFrame.setMaximumSize(new Dimension(width * scale, height * scale));
        this.jFrame.setLayout(null);
        this.jFrame.setLocationRelativeTo(null);
        this.jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.canvas = new Canvas();
        this.canvas.setSize(new Dimension(width * scale, height * scale));
        this.canvas.setMinimumSize(new Dimension(width * scale, height * scale));
        this.canvas.setPreferredSize(new Dimension(width * scale, height * scale));
        this.canvas.setMaximumSize(new Dimension(width * scale, height * scale));
        this.canvas.setVisible(true);

        this.jFrame.add(canvas);
        this.jFrame.setVisible(true);
        this.jFrame.pack();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.jFrame.setTitle(title);
    }

    public JFrame getFrame() {
        return jFrame;
    }

    public Canvas getCanvas() {
        return canvas;
    }

}
