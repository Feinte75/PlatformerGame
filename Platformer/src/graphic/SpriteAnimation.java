package graphic;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Created by Glenn on 24/06/2014.
 */
public class SpriteAnimation {

    private LinkedList<BufferedImage> images;
    private int index = 0;
    private int counter = 0;

    public SpriteAnimation(SpriteSheet ss, String identifier) {

        images = new LinkedList<BufferedImage>();
        images = ss.getAnimation(identifier);
    }

    public BufferedImage getActiveImage() {

        counter++;

        if (counter % 10 == 0) index++;

        if (index == images.size()) {
            index = 0;
            counter = 0;
        }
        return images.get(index);
    }
}
