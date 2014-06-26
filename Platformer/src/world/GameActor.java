package world;

import graphic.SpriteAnimation;
import graphic.SpriteSheet;

import java.awt.image.BufferedImage;

/**
 * Created by Glenn on 19/06/2014.
 * Abstract GameActor class
 * Used to represent all the different actors of the game
 * Main character and Ennemies
 */
public abstract class GameActor {

    protected int x, y;
    protected float velocityX, velocityY;
    protected boolean onGround;
    protected Movement movement;
    protected ActionHandler actionHandler;
    protected SpriteSheet ss;
    protected SpriteAnimation move;
    protected SpriteAnimation jump;
    protected SpriteAnimation idle;

    public abstract void jump(Movement movement);

    public abstract void startMoving(Movement movement);

    public abstract void stopMoving(Movement movement);

    public abstract void startSpecialAction1(Action action);

    public abstract void stopSpecialAction1(Action action);

    public abstract BufferedImage render();

    public void updatePosition(int dx, int dy) {

        this.x += dx;
        this.y += dy;
    }

    public BufferedImage getMoveImage() {

        return move.getActiveImage();
    }

    public BufferedImage getJumpImage() {

        return jump.getActiveImage();
    }

    public BufferedImage getIdleImage() {

        return idle.getActiveImage();
    }

    public Movement getMovement() {
        return movement;
    }
}
