package graphic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

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
    private BufferedImage teleport1;
    private BufferedImage teleport2;
    private BufferedImage teleport3;
    private BufferedImage teleport4;

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
        teleport1 = spriteSheet.getSubimage(1, 133, 26, 37);
        teleport2 = spriteSheet.getSubimage(30, 133, 25, 37);
        teleport3 = spriteSheet.getSubimage(58, 129, 24, 41);
        teleport4 = spriteSheet.getSubimage(86, 122, 26, 48);
    }

    public void loadImage(String path) {

        try {
            spriteSheet = ImageIO.read(new File(path));
            System.out.println("Image grabbed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<BufferedImage> getAnimation(String identifier) {

        LinkedList<BufferedImage> animation = new LinkedList<BufferedImage>();

        if (identifier.equals("move")) {
            animation.add(run1);
            animation.add(run2);
            animation.add(run3);
            animation.add(run4);
            animation.add(run5);
            animation.add(run6);
        }
        if (identifier.equals("jump")) {
            animation.add(jump1);
        }
        if (identifier.equals("idle")) {
            animation.add(idle);
        }
        if (identifier.equals("teleport")) {
            animation.add(teleport1);
            animation.add(teleport2);
            animation.add(teleport3);
            animation.add(teleport4);
        }

        return animation;
    }
}
