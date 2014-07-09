package action;

import graphic.SpriteSheet;
import world.CharacterAction;
import world.GameActor;

public class JumpAction extends Command {


    public JumpAction(SpriteSheet ss, String identifier, int animationSpeed) {

        super(ss, identifier, animationSpeed);
        stoppable = true;
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
                flip = false;
                return;
            case JUMPLEFT:
                if (character.isOnGround()) character.updateVelocity(-7, -20);
                character.setOnGround(false);
                flip = true;
                return;
            case JUMP:
                if (character.isOnGround()) character.updateVelocity(0, -20);
                character.setOnGround(false);
                return;
        }

        if (character.isOnGround()) character.setCurrentAction(action);


    }

}
