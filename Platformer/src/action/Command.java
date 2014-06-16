package action;

import world.MainCharacter;

/**
 * Created by Glenn on 15/06/2014.
 */
public interface Command {

    // MainCharacter can change to a more global type like "GameActor"
    public void execute(MainCharacter character);
}
