package input;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Glenn on 02/07/2014.
 */
public class KeyboardInput extends AbstractAction {

    protected boolean keyPressed;
    protected InputHandler inputHandler;
    protected KeyStroke key;
    private State state = State.KEYRELEASED;

    public KeyboardInput(boolean keyPressed, InputHandler inputHandler) {

        this.keyPressed = keyPressed;
        this.inputHandler = inputHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        inputHandler.inputEvent(this);
    }
}
