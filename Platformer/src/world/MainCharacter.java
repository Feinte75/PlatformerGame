package world;

import action.*;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * MainCharacter class
 * Used to reify the user character
 */
public class MainCharacter extends GameActor {

    public MainCharacter(int x, int y, float velocityX, float velocityY) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;

        name = "kabuto";
        move = new MoveAction(name, "move", "characters");
        jump = new JumpAction(name, "jump", "characters");
        idle = new IdleAction(name, "idle", "characters");
        attack = new AttackAction(name, "attack", "characters");
        specialAction1 = new TeleportAction(name, "specialaction1", "characters");
        currentAction = idle;

    }

    public void update(float gravity) {

        currentAction.update();

        velocityY += gravity;

        y += velocityY;
        x += velocityX;

        /*if(velocityX > 0) velocityX--;
        else if(velocityX < 0) velocityX++;*/

        if (y > 500) {
            y = 500;
            onGround = true;
            velocityY = 0;
        }

        //System.out.println(movement);
        //if (onGround && currentAction == jump)
        //  currentAction = idle;
    }

    @Override
    public void handleInput(CharacterAction action) {

        //System.out.println(""+currentAction);
        //System.out.println("VelocityX "+velocityX+"     VelocityY"+velocityY);
        if (action == null) {
            currentAction.stop(this);
            action = CharacterAction.DEFAULT;
        }
        currentAction.execute(this, action);
    }

    public void handleCollision() {

        if (x < 0) x = 0;
        else if (x > 800) x = 800;
    }

    @Override
    public BufferedImage render() {

        return currentAction.getActiveImage();
    }

    @Override
    public Rectangle getActiveHitbox() {
        return null;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}
