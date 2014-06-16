package input;

import listeners.InputListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public abstract class KeyboardManager extends AbstractAction {

    protected final ArrayList<InputListener> listeners;
    protected boolean keyPressed;

    public KeyboardManager(boolean keyPressed) {

        listeners = new ArrayList<InputListener>();
        this.keyPressed = keyPressed;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

    }

    protected abstract void fireChangeEvent();

    public void addInputListener(InputListener l) {
        listeners.add(l);
    }

    public void removeInputListener(InputListener l) {
        listeners.remove(l);
    }
}
