package action;

import graphic.SpriteSheet;
import world.CharacterAction;
import world.GameActor;

/**
 * Created by Glenn on 09/07/2014.
 *
 */
public class IdleAction extends Command {

    public IdleAction(SpriteSheet ss, String identifier, int animationSpeed) {
        super(ss, identifier, animationSpeed);
    }

    @Override
    public void execute(GameActor character, CharacterAction action) {

        classicFlipping(action);

        character.updateVelocity(0, 0);
        character.setCurrentAction(action);
    }

    @Override
    public void updateVelocity(int dx, int dy) {

    }

    @Override
    public boolean isStoppable() {
        return true;
    }
}