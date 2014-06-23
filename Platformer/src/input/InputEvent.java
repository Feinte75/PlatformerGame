package input;

import java.util.EventObject;

/**
 * Created by Glenn on 16/06/2014.
 */
public class InputEvent extends EventObject {

    private boolean keyPressed;

    public InputEvent(Object source, boolean keyPressed) {
        super(source);
        this.keyPressed = keyPressed;
    }

    public boolean isKeyPressed() {
        return keyPressed;
    }
}
