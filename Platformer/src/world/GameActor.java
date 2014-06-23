package world;

/**
 * Created by Glenn on 19/06/2014.
 */
public abstract class GameActor {

    protected int x, y;
    protected float velocityX, velocityY;

    public abstract void jump(Movement movement);

    public abstract void startMoving(Movement movement);

    public abstract void stopMoving(Movement movement);
}
