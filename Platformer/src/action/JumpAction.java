package action;

import input.Input;
import input.InputEvent;
import input.KeyboardManager;
import listeners.InputListener;
import world.MainCharacter;

import java.awt.event.ActionEvent;

public class JumpAction extends KeyboardManager implements Command{

	private float velocityY;

    public JumpAction(float velocityY, boolean keyPressed) {
        super(keyPressed);
        this.velocityY = velocityY;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
        fireChangeEvent();
    }

    @Override
    protected void fireChangeEvent() {

        InputEvent evt;
        evt = new InputEvent(Input.JUMP);

        //else evt = new InputEvent(Input.JUMPRELEASED);

        for (InputListener l : listeners) {
            l.inputEvent(evt);
        }
    }

    @Override
    public void execute(MainCharacter character) {

        if(keyPressed){
            System.out.println("Jump");
            character.jump(velocityY);
        }
        /*else{
            System.out.println("Stop jumping");
            character.stopJump();
        }*/
    }
}
