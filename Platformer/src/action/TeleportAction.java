package action;

import input.Input;
import input.InputEvent;
import listeners.InputListener;
import world.Action;
import world.GameActor;

import java.awt.event.ActionEvent;

/**
 * Created by Glenn on 25/06/2014.
 */
public class TeleportAction extends KeyboardManager implements Command {

    private int counter = 0;

    public TeleportAction(boolean keyPressed) {
        super(keyPressed);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        System.out.println("Key pressed: " + keyPressed);
        fireChangeEvent();
    }

    @Override
    protected void fireChangeEvent() {

        InputEvent evt = null;

        if (keyPressed) {
            evt = new InputEvent(Input.TELEPORT, true);
        } else {
            evt = new InputEvent(Input.TELEPORT, false);
        }

        for (InputListener l : listeners) {
            l.inputEvent(evt);
        }
    }

    @Override
    public void execute(GameActor character) {

        if (keyPressed) {
            character.startSpecialAction1(Action.TELEPORT);
        } else {
            character.stopSpecialAction1(null);
        }
        counter++;
    }
}
