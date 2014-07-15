package graphic;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Created by Glenn on 24/06/2014.
 * Sprite animation class
 * Used to store multiple images
 */
public class SpriteAnimation {

    protected LinkedList<BufferedImage> images;
    protected LinkedList<Rectangle> boundingBoxes;
    protected int index = 0;
    protected int counter = 0;
    protected int animationSpeed;
    boolean lock;

    public SpriteAnimation(SpriteSheet ss, String identifier, int animationSpeed) {

        images = new LinkedList<BufferedImage>();
        images = ss.getAnimation(identifier);
        this.animationSpeed = animationSpeed;
    }

    public void update() {

        counter++;
        if (counter % animationSpeed == 0) index++;
        if (index >= images.size()) index = 0;
    }

    public void resetIndex() {
        index = 0;
    }

    public BufferedImage getActiveImage() {
        return images.get(index);
    }

}
