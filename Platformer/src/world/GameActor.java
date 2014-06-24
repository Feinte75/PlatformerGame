package world;

import graphic.SpriteAnimation;
import graphic.SpriteSheet;

import java.awt.image.BufferedImage;

/**
 * Created by Glenn on 19/06/2014.
 */
public abstract class GameActor {

    protected int x, y;
    protected float velocityX, velocityY;
    protected SpriteSheet ss;
    protected SpriteAnimation move;
    protected SpriteAnimation jump;
    protected SpriteAnimation idle;

    public abstract void jump(Movement movement);

    public abstract void startMoving(Movement movement);

    public abstract void stopMoving(Movement movement);

    public BufferedImage getMoveImage() {

        return move.getActiveImage();
    }

    public BufferedImage getJumpImage() {

        return jump.getActiveImage();
    }

    public BufferedImage getIdleImage() {

        return idle.getActiveImage();
    }
}
