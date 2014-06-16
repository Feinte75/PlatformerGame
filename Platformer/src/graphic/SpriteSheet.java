package graphic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by feinte on 31/05/2014.
 */
public class SpriteSheet {

    private BufferedImage spriteSheet;
    private BufferedImage run1;
    private BufferedImage run2;
    private BufferedImage run3;
    private BufferedImage run4;
    private BufferedImage run5;
    private BufferedImage run6;
    private BufferedImage jump1;
    private BufferedImage idle;

    public SpriteSheet(String path) {

        loadImage(path);
        jump1 = spriteSheet.getSubimage(1, 1, 27, 45);
        run1 = spriteSheet.getSubimage(1, 49, 42, 34);
        run2 = spriteSheet.getSubimage(46, 49, 41, 34);
        run3 = spriteSheet.getSubimage(90, 49, 31, 34);
        run4 = spriteSheet.getSubimage(124, 49, 36, 34);
        run5 = spriteSheet.getSubimage(163, 49, 35, 34);
        run6 = spriteSheet.getSubimage(201, 49, 35, 34);
        idle = spriteSheet.getSubimage(1, 86, 16, 44);
    }

    public void loadImage(String path) {

        try {
            spriteSheet = ImageIO.read(new File(path));
            System.out.println("Image grabbed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getRun1() {
        return run1;
    }

    public BufferedImage getRun2() {
        return run2;
    }

    public BufferedImage getRun3() {
        return run3;
    }

    public BufferedImage getRun4() {
        return run4;
    }

    public BufferedImage getRun5() {
        return run5;
    }

    public BufferedImage getRun6() {
        return run6;
    }

    public BufferedImage getJump1() {
        return jump1;
    }

    public BufferedImage getIdle() {
        return idle;
    }
}
