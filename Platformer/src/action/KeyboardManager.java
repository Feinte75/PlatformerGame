package action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import world.MainCharacter;

public abstract class KeyboardManager extends AbstractAction {
	
	protected MainCharacter character;
	protected boolean keyPressed;
	
	public KeyboardManager(MainCharacter character, boolean keyPressed){
		this.character = character;
		this.keyPressed = keyPressed;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
