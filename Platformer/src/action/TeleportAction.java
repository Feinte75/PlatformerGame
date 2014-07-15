package action;

import graphic.SpriteSheet;
import world.CharacterAction;
import world.GameActor;

/**
 * Created by Glenn on 25/06/2014.
 *
 */
public class TeleportAction extends Command {

    public TeleportAction(SpriteSheet ss, String identifier, int animationSpeed) {

        super(ss, identifier, animationSpeed);
    }

    @Override
    public void stop(GameActor character) {

        if (stoppable) {
            spriteAnimation.resetIndex();
            super.stop(character);
        }
    }

    @Override
    public void update() {

        super.update();
    }

    @Override
    public void execute(GameActor character, CharacterAction action) {

        if (!running) {

            stoppable = false;
            running = true;
            counter = 0;
            loadTime = 60;
            character.updateVelocity(0, 0);
        }

        counter++;

        classicFlipping(action);

        System.out.println("Counter : " + counter);
        if (counter % loadTime == 0) {
            switch (action) {
                case MOVELEFT:
                    character.updatePosition(-160, 0);
                    break;
                case MOVERIGHT:
                    character.updatePosition(160, 0);
                    break;
                case JUMPLEFT:
                    character.updatePosition(-160, -160);
                    character.updateVelocity(-7, -10);
                    character.setOnGround(false);
                    break;
                case JUMPRIGHT:
                    character.updatePosition(160, -160);
                    character.updateVelocity(7, -10);
                    character.setOnGround(false);
                    break;
                case JUMP:
                    character.updatePosition(0, -160);
                    character.updateVelocity(0, -10);
                    character.setOnGround(false);
                    break;
                default:
                    break;
            }
            running = false;
            stoppable = true;
            spriteAnimation.resetIndex();
            character.setCurrentAction(action);
        }


    }

    @Override
    public void updateVelocity(int dx, int dy) {


    }
}
