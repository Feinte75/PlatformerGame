package graphic;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Created by Glenn on 24/06/2014.
 * Sprite animation class
 * Used to store multiple images
 */
public class SpriteAnimation {

    private LinkedList<BufferedImage> images;
    private int index = 0;
    private int counter = 0;
    private int animationSpeed;

    public SpriteAnimation(SpriteSheet ss, String identifier, int animationSpeed) {

        images = new LinkedList<BufferedImage>();
        images = ss.getAnimation(identifier);
        this.animationSpeed = animationSpeed;
    }

    public BufferedImage getActiveImage() {

        counter++;

        if (counter % animationSpeed == 0) index++;

        if (index == images.size()) {
            index = 0;
            counter = 0;
        }
        return images.get(index);
    }
}
