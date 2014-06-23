package action;

import input.Input;
import input.InputEvent;
import listeners.InputListener;
import world.GameActor;
import world.Movement;

import java.awt.event.ActionEvent;

public class JumpAction extends KeyboardManager implements Command{

    public JumpAction(Movement movement, boolean keyPressed) {
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
            evt = new InputEvent(Input.JUMP, true);
        } else {
            evt = new InputEvent(Input.JUMP, false);
        }

        //System.out.println("Event thrown : " + evt.getSource());
        for (InputListener l : listeners) {
            l.inputEvent(evt);
        }
    }

    @Override
    public void execute(GameActor character) {

        character.jump(movement);
    }
}
