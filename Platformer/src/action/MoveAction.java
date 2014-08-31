package action;

import world.CharacterAction;
import world.GameActor;

public class MoveAction extends Command {

    public MoveAction() {

        super();
        stoppable = true;
    }

    @Override
    public void stop(GameActor character) {

        character.updateVelocity(0, 0);
        super.stop(character);
    }

    @Override
    public void execute(GameActor character, CharacterAction action) {

        switch (action) {
            case MOVERIGHT:
                character.updateVelocity(2, 0);
                return;
            case MOVELEFT:
                character.updateVelocity(-2, 0);
                return;
        }
        character.setCurrentAction(action);
    }

}
