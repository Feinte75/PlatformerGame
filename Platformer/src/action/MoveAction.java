package action;

import graphic.SpriteSheet;
import world.CharacterAction;
import world.GameActor;

public class MoveAction extends Command {

    public MoveAction(SpriteSheet ss, String identifier, int animationSpeed) {

        super(ss, identifier, animationSpeed);
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

        switch (action) {
            case MOVERIGHT:
                character.updateVelocity(7, 0);
                flip = false;
                return;
            case MOVELEFT:
                character.updateVelocity(-7, 0);
                flip = true;
                return;
        }

        character.setCurrentAction(action);
    }

}
