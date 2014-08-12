package action;

import world.CharacterAction;
import world.GameActor;

/**
 * Created by Glenn on 09/07/2014.
 */
public class IdleAction extends Command {

    public IdleAction(String entityName, String actionIdentifier, String category) {

        super(entityName, actionIdentifier, category);
    }

    @Override
    public void execute(GameActor character, CharacterAction action) {

        defaultFlipping(action);

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
