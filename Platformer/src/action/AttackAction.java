package action;

import world.CharacterAction;
import world.GameActor;

/**
 * Created by Glenn on 10/08/2014.
 */
public class AttackAction extends Command {

    public AttackAction(String name, String identifier) {

        super(name, identifier);
        stoppable = true;
    }

    @Override
    public void execute(GameActor character, CharacterAction action) {

        if (!running) {

            stoppable = false;
            running = true;
            counter = 0;
            character.updateVelocity(0, 0);
        }

        counter++;

        defaultFlipping(action);

        System.out.println("Counter : " + counter);
        if (counter % 20 == 0) {

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
