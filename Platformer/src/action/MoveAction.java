package action;

import java.awt.event.ActionEvent;

import world.MainCharacter;
import world.Movement;

public class MoveAction extends KeyboardManager implements Command {
	
	private Movement movement;
	
	public MoveAction(Movement movement, MainCharacter character, boolean keyPressed){
		super(character, keyPressed);
		this.movement = movement;
		this.character = character;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println(movement);
		if(keyPressed){
			character.startMoving(movement);
		}
		else {
			character.stopMoving(movement);
			//System.out.println("Stop moving !");
		}
	}

    @Override
    public void execute() {

    }
}
