package world;

import action.Command;

import java.awt.*;
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
    protected Command currentAction;
    protected Command move;
    protected Command jump;
    protected Command idle;
    protected Command attack;
    protected Command specialAction1;
    protected Command specialAction2;
    protected Command specialAction3;
    String name;



    public abstract BufferedImage render();

    public abstract Rectangle getActiveHitbox();

    public void updatePosition(int dx, int dy) {

        this.x += dx;
        this.y += dy;
    }

    public void updateVelocity(int dx, int dy) {

        this.velocityX = dx;
        this.velocityY = dy;
    }

    public abstract void handleInput(CharacterAction action);

    public void setCurrentAction(CharacterAction action) {

        switch (action) {
            case MOVERIGHT:
                currentAction = move;
                break;
            case MOVELEFT:
                currentAction = move;
                break;
            case JUMP:
                currentAction = jump;
                break;
            case JUMPLEFT:
                currentAction = jump;
                break;
            case JUMPRIGHT:
                currentAction = jump;
                break;
            case ATTACK:
                currentAction = attack;
                break;
            case SPECIALACTION1:
                currentAction = specialAction1;
                break;
            case IDLE:
                currentAction = idle;
                break;
            default:
                currentAction = idle;
                break;
        }
        //System.out.println(""+currentAction);
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

}
