package action;

import world.CharacterAction;
import world.GameActor;

/**
 * Created by Glenn on 10/08/2014.
 */
public class AttackAction extends Command {

    public AttackAction() {

        super();
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

        System.out.println("Counter : " + counter);
        if (counter % 20 == 0) {

            running = false;
            stoppable = true;
            //spriteAnimation.resetIndex();
            character.setCurrentAction(action);
        }

    }

}
