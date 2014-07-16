package action;

import json.JSONObject;
import json.JSONTokener;
import world.CharacterAction;
import world.GameActor;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Glenn on 25/06/2014.
 *
 */
public class TeleportAction extends Command {

    public TeleportAction(String name, String identifier, int animationSpeed) {

        super(name, identifier, animationSpeed);

        FileReader fileReader = null;

        try {
            fileReader = new FileReader("Platformer/conf/actions/TeleportAction.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(new JSONTokener(fileReader));
        loadTime = jsonObject.getInt("loadingtime");

        dx = jsonObject.getJSONObject("positionupdate").getInt("dx");
        dy = jsonObject.getJSONObject("positionupdate").getInt("dy");
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
            character.updateVelocity(0, 0);
        }

        counter++;

        defaultFlipping(action);

        System.out.println("Counter : " + counter);
        if (counter % loadTime == 0) {
            switch (action) {
                case MOVELEFT:
                    character.updatePosition(-dx, 0);
                    break;
                case MOVERIGHT:
                    character.updatePosition(dx, 0);
                    break;
                case JUMPLEFT:
                    character.updatePosition(-dx, -dy);
                    character.updateVelocity(-7, -10);
                    character.setOnGround(false);
                    break;
                case JUMPRIGHT:
                    character.updatePosition(dx, -dy);
                    character.updateVelocity(7, -10);
                    character.setOnGround(false);
                    break;
                case JUMP:
                    character.updatePosition(0, -dy);
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
