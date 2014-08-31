package action;

import world.CharacterAction;
import world.GameActor;

/**
 * Created by Glenn on 09/07/2014.
 */
public class IdleAction extends Command {

    public IdleAction() {

        super();
    }

    @Override
    public void execute(GameActor character, CharacterAction action) {

        character.updateVelocity(0, 0);
        character.setCurrentAction(action);
    }

    @Override
    public boolean isStoppable() {
        return true;
    }
}
