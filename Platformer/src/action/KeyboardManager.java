package action;

import listeners.InputListener;
import world.Movement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public abstract class KeyboardManager extends AbstractAction {

    protected final ArrayList<InputListener> listeners;
    protected Movement movement;
    protected boolean keyPressed;

    public KeyboardManager(boolean keyPressed) {

        listeners = new ArrayList<InputListener>();
        this.keyPressed = keyPressed;
    }

    @Override
    public abstract void actionPerformed(ActionEvent arg0);

    protected abstract void fireChangeEvent();

    public void addInputListener(InputListener l) {
        listeners.add(l);
    }

    public void removeInputListener(InputListener l) {
        listeners.remove(l);
    }

    public String toString() {
        return movement.toString();
    }
}
