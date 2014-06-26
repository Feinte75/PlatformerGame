package action;

import input.Input;
import input.InputEvent;
import listeners.InputListener;
import world.GameActor;
import world.Movement;

import java.awt.event.ActionEvent;

public class MoveAction extends KeyboardManager implements Command {

    public MoveAction(Movement movement, boolean keyPressed) {
        super(keyPressed);
        this.movement = movement;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fireChangeEvent();
    }

    @Override
    protected void fireChangeEvent() {

        InputEvent evt = null;
        if (keyPressed) {
            if (movement == Movement.MOVINGRIGHT) evt = new InputEvent(Input.MOVERIGHT, true);
            else if (movement == Movement.MOVINGLEFT) evt = new InputEvent(Input.MOVELEFT, true);
        } else {
            if (movement == Movement.MOVINGRIGHT) evt = new InputEvent(Input.MOVERIGHT, false);
            else if (movement == Movement.MOVINGLEFT) evt = new InputEvent(Input.MOVELEFT, false);
        }

        for (InputListener l : listeners) {
            l.inputEvent(evt);
        }
    }

    @Override
    public void execute(GameActor character) {

        if (keyPressed) {
            character.startMoving(movement);
        } else {

            character.stopMoving(movement);
        }
    }
}
