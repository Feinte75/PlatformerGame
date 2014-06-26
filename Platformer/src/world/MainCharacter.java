package world;

import graphic.SpriteAnimation;
import graphic.SpriteSheet;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
        this.movement = Movement.IDLE;

        ss = new SpriteSheet("Platformer/res/sprite_sheet_kabuto.png");
        move = new SpriteAnimation(ss, "move", 10);
        jump = new SpriteAnimation(ss, "jump", 10);
        idle = new SpriteAnimation(ss, "idle", 10);

        actionHandler = new ActionHandler(new SpriteAnimation(ss, "teleport", 60));
    }

    public void startMoving(Movement movement) {

        // Check if moving while jumping
        if (onGround) this.movement = movement;
        velocityX = movement.getVelocityX();
    }

    public void stopMoving(Movement movement) {
        velocityX = 0;
        if (onGround) this.movement = movement;
        else this.movement = Movement.JUMPING;

        System.out.println("Stopmoving");
    }

    public void jump(Movement jump) {

        movement = jump;
        this.velocityX = jump.getVelocityX();

        if (onGround) {
            this.velocityY = jump.getVelocityY();
            onGround = false;
        }
    }

    @Override
    public void startSpecialAction1(Action action) {

        actionHandler.startAction(action);
    }

    @Override
    public void stopSpecialAction1(Action action) {

    }

    public void update(float gravity) {

        if (actionHandler.actionPlaying()) {

            actionHandler.handleAction(this);
        } else {
            velocityY += gravity;
            y += velocityY;
            x += velocityX;

            if (y > 500) {
                y = 500;
                onGround = true;
                velocityY = 0;
            }
            //System.out.println(movement);
            if (onGround && (movement == Movement.JUMPING || movement == Movement.JUMPINGRIGHT || movement == Movement.JUMPINGLEFT))
                movement = Movement.IDLE;
        }

    }

    @Override
    public BufferedImage render() {

        //System.out.println("Action : "+action);
        if (actionHandler.actionPlaying()) {

            switch (movement) {
                case MOVINGRIGHT:
                case JUMPINGRIGHT:
                case JUMPING:
                case IDLE:
                    return actionHandler.getActionImage();
                case MOVINGLEFT:
                case JUMPINGLEFT:
                    return flipImage(actionHandler.getActionImage());
            }
        }

        switch (movement) {
            case MOVINGRIGHT:
                return getMoveImage();
            case MOVINGLEFT:
                return flipImage(getMoveImage());
            case JUMPING:
                return getJumpImage();
            case JUMPINGRIGHT:
                return getJumpImage();
            case JUMPINGLEFT:
                return flipImage(getJumpImage());
            case IDLE:
                return getIdleImage();
        }
        return null;
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

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

}
