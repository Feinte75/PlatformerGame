package action;

import world.CharacterAction;
import world.GameActor;

/**
 * Created by Glenn on 15/06/2014.
 * Command interface
 * Used to implement actions
 */
public abstract class Command {

    protected static boolean flip = false;
    protected int dx = 0, dy = 0;
    protected boolean stoppable = true;
    protected boolean running = false;
    protected int counter = 0;
    protected int loadTime = 0;

    public Command() {

    }

    // MainCharacter can change to a more global type like "GameActor"
    public abstract void execute(GameActor character, CharacterAction action);


    public void update() {

        /*LinkedList<Rectangle> test = spriteAnimation.getActiveBoundingBoxes();
        System.out.println(test.getFirst().toString());*/
    }

    public void stop(GameActor character) {
        if (isStoppable()) character.setCurrentAction(CharacterAction.IDLE);
    }

    public abstract void updateVelocity(int dx, int dy);

    public boolean isStoppable() {
        return stoppable;
    }

    public void setFlip(boolean flip) {
        this.flip = flip;
    }

    public boolean isFlipped() {
        return flip;
    }

}
