package action;

import world.GameActor;

/**
 * Created by Glenn on 15/06/2014.
 */
public interface Command {

    // MainCharacter can change to a more global type like "GameActor"
    public void execute(GameActor character);
}
