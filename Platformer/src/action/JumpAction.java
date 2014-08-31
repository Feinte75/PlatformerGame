package action;

import world.CharacterAction;
import world.GameActor;

public class JumpAction extends Command {


    public JumpAction() {

        super();
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
                if (character.isOnGround()) character.updateVelocity(7, -80);
                else character.updateVelocity(7, 0);
                character.setOnGround(false);
                return;
            case JUMPLEFT:
                if (character.isOnGround()) character.updateVelocity(-7, -80);
                else character.updateVelocity(-7, 0);
                character.setOnGround(false);
                return;
            case JUMP:
                if (character.isOnGround()) character.updateVelocity(0, -80);
                character.setOnGround(false);
                return;
            case MOVERIGHT:
                character.updateVelocity(2, 0);
                break;
            case MOVELEFT:
                character.updateVelocity(-2, 0);
                break;
        }

        if (character.isOnGround()) character.setCurrentAction(action);


    }

}
