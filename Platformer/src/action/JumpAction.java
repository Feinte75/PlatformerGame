package action;

import world.CharacterAction;
import world.GameActor;

public class JumpAction extends Command {


    public JumpAction() {

        super();
    }

    public void updateVelocity(int dx, int dy) {

    }

    @Override
    public void stop(GameActor character) {

        if (character.isOnGround()) {
            character.updateVelocity(0, 0);
            super.stop(character);
        }
    }

    @Override
    public void execute(GameActor character, CharacterAction action) {

        switch (action) {
            case JUMPRIGHT:
                if (character.isOnGround()) character.updateVelocity(7, -20);
                character.setOnGround(false);
                return;
            case JUMPLEFT:
                if (character.isOnGround()) character.updateVelocity(-7, -20);
                character.setOnGround(false);
                return;
            case JUMP:
                if (character.isOnGround()) character.updateVelocity(0, -20);
                character.setOnGround(false);
                return;
        }

        if (character.isOnGround()) character.setCurrentAction(action);


    }

}
