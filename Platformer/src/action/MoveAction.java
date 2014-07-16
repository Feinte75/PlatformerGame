package action;

import world.CharacterAction;
import world.GameActor;

public class MoveAction extends Command {

    public MoveAction(String name, String identifier, int animationSpeed) {

        super(name, identifier, animationSpeed);
        stoppable = true;
    }

    public void updateVelocity(int dx, int dy) {

        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void stop(GameActor character) {
        character.updateVelocity(0, 0);
        super.stop(character);
    }

    @Override
    public void execute(GameActor character, CharacterAction action) {

        defaultFlipping(action);

        switch (action) {
            case MOVERIGHT:
                character.updateVelocity(7, 0);
                return;
            case MOVELEFT:
                character.updateVelocity(-7, 0);
                return;
        }

        character.setCurrentAction(action);
    }

}
