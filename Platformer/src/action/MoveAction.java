package action;

import input.Input;
import input.InputEvent;
import input.KeyboardManager;
import listeners.InputListener;
import world.MainCharacter;
import world.Movement;

import java.awt.event.ActionEvent;

public class MoveAction extends KeyboardManager implements Command {

    private Movement movement;

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

        InputEvent evt;
        if (movement == Movement.MOVINGRIGHT) evt = new InputEvent(Input.MOVERIGHT);
        else if (movement == Movement.MOVINGLEFT) evt = new InputEvent(Input.MOVELEFT);
        else evt = new InputEvent(Input.IDLE);

        for (InputListener l : listeners) {
            l.inputEvent(evt);
        }
    }

    @Override
    public void execute(MainCharacter character) {

        System.out.println(movement);
        if (keyPressed) {
            character.startMoving(movement);
        } else {
            character.stopMoving(movement);
        }
    }
}
