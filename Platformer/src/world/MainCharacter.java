package world;

import action.*;
import graphic.SpriteAnimation;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * MainCharacter class
 * Used to reify the user character
 */
public class MainCharacter extends GameActor {

    public MainCharacter(int x, int y, int velocityX, int velocityY) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.onGround = true;

        actionAnimations = new HashMap<Command, SpriteAnimation>();

        name = "naruto";
        move = new MoveAction();
        actionAnimations.put(move, new SpriteAnimation(name, "move", "characters"));

        jump = new JumpAction();
        actionAnimations.put(jump, new SpriteAnimation(name, "jump", "characters"));

        idle = new IdleAction();
        actionAnimations.put(idle, new SpriteAnimation(name, "idle", "characters"));

        attack = new AttackAction();
        actionAnimations.put(attack, new SpriteAnimation(name, "attack", "characters"));

        specialAction1 = new TeleportAction();
        actionAnimations.put(specialAction1, new SpriteAnimation(name, "specialaction1", "characters"));

        currentAction = idle;
        currentAnimation = actionAnimations.get(currentAction);
    }

    public void update(float gravity) {

        currentAction.update();
        currentAnimation.update();

        velocityY += gravity;
        if (velocityY < -20) velocityY = -20;
        else if (velocityY > 20) velocityY = 20;

        if (velocityX > 8) velocityX = 8;
        else if (velocityX < -8) velocityX = -8;

        y += velocityY;
        x += velocityX;

        if (velocityX < 0) velocityX++;
        else if (velocityX > 0) velocityX--;
        /*if (onGround) {
            velocityY = 0;
        }*/

    }

    @Override
    public void handleInput(CharacterAction action) {

        //System.out.println(""+currentAction);
        //System.out.println("VelocityX "+velocityX+"     VelocityY"+velocityY);
        if (action == null) {
            currentAction.stop(this);
            action = CharacterAction.DEFAULT;
        }
        defaultFlipping(action);
        currentAction.execute(this, action);
    }

    public void handleCollision() {

        if (x < 0) x = 0;
        else if (x > 800) x = 800;
    }

    @Override
    public BufferedImage render() {

        return getActiveImage();
    }


    public BufferedImage getActiveImage() {

        return currentAction.isFlipped() ? flipImage(currentAnimation.getActiveImage()) : currentAnimation.getActiveImage();
    }

    /**
     * Update flip variable according to the action param
     *
     * @param action
     */
    public void defaultFlipping(CharacterAction action) {

        switch (action) {
            case DEFAULT:
                break;
            case IDLE:
                break;
            case ATTACK:
                break;
            case MOVELEFT:
                currentAction.setFlip(true);
                break;
            case JUMPLEFT:
                currentAction.setFlip(true);
                break;
            case JUMPRIGHT:
                currentAction.setFlip(false);
                break;
            case MOVERIGHT:
                currentAction.setFlip(false);
                break;
            case JUMP:
                break;
        }
    }

    /**
     * Flip image
     *
     * @param original image to flip
     * @return Flipped image
     */
    public BufferedImage flipImage(BufferedImage original) {

        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-original.getWidth(), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        original = op.filter(original, null);

        return original;
    }

    @Override
    public Rectangle getActiveHitbox() {
        Rectangle temp = currentAnimation.getActiveHitBox();
        temp.setLocation(x, (int) (y - temp.getHeight()));
        return temp;
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
