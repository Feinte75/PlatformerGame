package world;

import graphic.SpriteAnimation;

import java.awt.image.BufferedImage;

/**
 * Created by Glenn on 26/06/2014.
 */
public class ActionHandler {

    private Action currentAction;
    private int timer = 0;
    private SpriteAnimation sa;

    public ActionHandler(SpriteAnimation sa) {

        this.sa = sa;
    }

    public void startAction(Action action) {

        if (currentAction == null) currentAction = action;
    }

    public void handleAction(GameActor character) {

        if (timer >= currentAction.getLength()) {
            currentAction = null;
            timer = 0;
            return;
        }
        if (timer == currentAction.getLength() - 1) {
            switch (currentAction) {
                case TELEPORT:
                    switch (character.getMovement()) {
                        case JUMPINGLEFT:
                            character.updatePosition(-Action.TELEPORT.getValue1(), -Action.TELEPORT.getValue2());
                            break;
                        case JUMPINGRIGHT:
                            character.updatePosition(Action.TELEPORT.getValue1(), -Action.TELEPORT.getValue2());
                            break;
                        case MOVINGLEFT:
                            character.updatePosition(-Action.TELEPORT.getValue1(), 0);
                            break;
                        case MOVINGRIGHT:
                            character.updatePosition(Action.TELEPORT.getValue1(), 0);
                            break;
                        case IDLE:
                            break;
                    }
                    break;
                case ATTACK:
                    break;
            }
        }
        timer++;
    }

    public BufferedImage getActionImage() {

        return sa.getActiveImage();
    }

    public boolean actionPlaying() {

        return currentAction != null;
    }


}
