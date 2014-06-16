package action;

import java.awt.event.ActionEvent;

import world.MainCharacter;

public class JumpAction extends KeyboardManager implements Command{

	private float velocityY;
	
	public JumpAction(float velocityY, MainCharacter character, boolean keyPressed){
		super(character, keyPressed);
		this.velocityY = velocityY;
		this.character = character;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

}

    @Override
    public void execute() {

        if(keyPressed){
            character.jump(velocityY);
        }
        else{
            character.stopJump();
        }
    }
}
