package action;

import graphic.SpriteAnimation;
import world.CharacterAction;
import world.GameActor;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * Created by Glenn on 15/06/2014.
 * Command interface
 * Used to implement actions
 */
public abstract class Command {

    protected static boolean flip = false;
    protected SpriteAnimation spriteAnimation;
    protected int dx = 0, dy = 0;
    protected boolean stoppable = true;
    protected boolean running = false;
    protected int counter = 0;
    protected int loadTime = 0;

    public Command(String name, String identifier, int animationSpeed) {

        spriteAnimation = new SpriteAnimation(name, identifier, animationSpeed);
    }

    // MainCharacter can change to a more global type like "GameActor"
    public abstract void execute(GameActor character, CharacterAction action);

    public BufferedImage getActiveImage() {

        return flip ? flipImage(spriteAnimation.getActiveImage()) : spriteAnimation.getActiveImage();
    }

    public void update() {
        spriteAnimation.update();
    }

    public void stop(GameActor character) {
        if (isStoppable()) character.setCurrentAction(CharacterAction.IDLE);
    }

    public abstract void updateVelocity(int dx, int dy);

    public boolean isStoppable() {
        return stoppable;
    }

    /**
     * Update flip variable according to the action param
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
                flip = true;
                break;
            case JUMPLEFT:
                flip = true;
                break;
            case JUMPRIGHT:
                flip = false;
                break;
            case MOVERIGHT:
                flip = false;
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

}
