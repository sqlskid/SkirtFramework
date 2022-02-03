package xyz.sqlskid.skirtframework.display;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {
    private BufferedImage sheet;

    public SpriteSheet(String path)
    {
        try {
            this.sheet = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage crop(int x, int y, int width, int height)
    {
        return sheet.getSubimage(x,y,width,height);
    }
}
