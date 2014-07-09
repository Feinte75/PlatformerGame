package action;

import graphic.SpriteAnimation;
import graphic.SpriteSheet;
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

    protected SpriteAnimation spriteAnimation;
    protected int dx = 0, dy = 0;
    protected boolean flip = false;
    protected boolean stoppable = true;
    protected boolean running = false;
    protected int counter = 0;
    protected int loadTime;

    public Command(SpriteSheet ss, String identifier, int animationSpeed) {

        spriteAnimation = new SpriteAnimation(ss, identifier, animationSpeed);
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
