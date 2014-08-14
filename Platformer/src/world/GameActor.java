package world;

import action.Command;
import graphic.SpriteAnimation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

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
    protected SpriteAnimation currentAnimation;
    protected Command move;
    protected Command jump;
    protected Command idle;
    protected Command attack;
    protected Command specialAction1;
    protected Command specialAction2;
    protected Command specialAction3;
    protected HashMap<Command, SpriteAnimation> actionAnimations;
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
            case MOVELEFT:
                currentAction = move;
                currentAnimation = actionAnimations.get(currentAction);
                break;
            case JUMP:
            case JUMPLEFT:
            case JUMPRIGHT:
                currentAction = jump;
                currentAnimation = actionAnimations.get(currentAction);
                break;
            case ATTACK:
                currentAction = attack;
                currentAnimation = actionAnimations.get(currentAction);
                break;
            case SPECIALACTION1:
                currentAction = specialAction1;
                currentAnimation = actionAnimations.get(currentAction);
                break;
            case IDLE:
            default:
                currentAction = idle;
                currentAnimation = actionAnimations.get(currentAction);
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

    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }
}
