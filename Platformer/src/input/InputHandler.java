package input;

import world.CharacterAction;
import world.MainCharacter;

import javax.swing.*;
import java.util.HashMap;

/**
 * Created by Glenn on 15/06/2014.
 * Handle input and key bindings with actions
 */
public class InputHandler {

    // Input actions

    private HashMap<Input, State> inputMap;

    private KeyboardInput specialAction1Pressed = null;
    private KeyboardInput specialAction1Released = null;
    private KeyboardInput specialAction2Pressed = null;
    private KeyboardInput specialAction2Released = null;
    private KeyboardInput specialAction3Pressed = null;
    private KeyboardInput specialAction3Released = null;

    private KeyboardInput moveRightPressed = null;
    private KeyboardInput moveRightReleased = null;
    private KeyboardInput moveLeftPressed = null;
    private KeyboardInput moveLeftReleased = null;
    private KeyboardInput jumpPressed = null;
    private KeyboardInput jumpReleased = null;
    private KeyboardInput duckPressed = null;
    private KeyboardInput duckReleased = null;
    private KeyboardInput attackPressed = null;
    private KeyboardInput attackReleased = null;

    public InputHandler(JPanel canvas, MainCharacter character) {

        inputMap = new HashMap<Input, State>();

        // Key bindings

        specialAction1Pressed = new KeyboardInput(true, this);
        specialAction1Released = new KeyboardInput(false, this);
        specialAction2Pressed = new KeyboardInput(true, this);
        specialAction2Released = new KeyboardInput(false, this);
        specialAction3Pressed = new KeyboardInput(true, this);
        specialAction3Released = new KeyboardInput(false, this);

        setBindings(canvas, "A", "specialAction1", specialAction1Pressed, specialAction1Released);
        setBindings(canvas, "Z", "specialAction2", specialAction2Pressed, specialAction2Released);
        setBindings(canvas, "E", "specialAction3", specialAction3Pressed, specialAction3Released);

        moveRightPressed = new KeyboardInput(true, this);
        moveRightReleased = new KeyboardInput(false, this);

        setBindings(canvas, "RIGHT", "moveRight", moveRightPressed, moveRightReleased);

        moveLeftPressed = new KeyboardInput(true, this);
        moveLeftReleased = new KeyboardInput(false, this);

        setBindings(canvas, "LEFT", "moveLeft", moveLeftPressed, moveLeftReleased);

        jumpPressed = new KeyboardInput(true, this);
        jumpReleased = new KeyboardInput(false, this);

        setBindings(canvas, "UP", "jump", jumpPressed, jumpReleased);

        duckPressed = new KeyboardInput(true, this);
        duckReleased = new KeyboardInput(false, this);

        setBindings(canvas, "DOWN", "duck", duckPressed, duckReleased);

        attackPressed = new KeyboardInput(true, this);
        attackReleased = new KeyboardInput(false, this);

        setBindings(canvas, "Z", "attack", attackPressed, attackReleased);

        inputMap.put(Input.MOVELEFT, State.KEYRELEASED);
        inputMap.put(Input.MOVERIGHT, State.KEYRELEASED);
        inputMap.put(Input.JUMP, State.KEYRELEASED);
        inputMap.put(Input.DUCK, State.KEYRELEASED);
        inputMap.put(Input.ATTACK, State.KEYRELEASED);
        inputMap.put(Input.SPECIALACTION1, State.KEYRELEASED);
        inputMap.put(Input.SPECIALACTION2, State.KEYRELEASED);
        inputMap.put(Input.SPECIALACTION3, State.KEYRELEASED);

    }

    public void setBindings(JPanel canvas, String keyName, String actionName, KeyboardInput actionPressed, KeyboardInput actionReleased) {

        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed " + keyName), actionName + "Pressed");
        canvas.getActionMap().put(actionName + "Pressed", actionPressed);
        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released " + keyName), actionName + "Released");
        canvas.getActionMap().put(actionName + "Released", actionReleased);
    }

    /**
     * Update the inputMap with the new input state
     * When the event come from a pressed key, check if it is the first time
     * If it is -> update the inputMap to KEYPRESSEDONCE
     * If not -> update to KEYPRESSED
     *
     * @param keyboardInput keyboardInput The event source
     */

    public void inputEvent(KeyboardInput keyboardInput) {

        if (keyboardInput == specialAction1Pressed) {
            if (inputMap.get(Input.SPECIALACTION1) == State.KEYRELEASED) {
                inputMap.put(Input.SPECIALACTION1, State.KEYPRESSEDONCE);
            } else inputMap.put(Input.SPECIALACTION1, State.KEYPRESSED);
        } else if (keyboardInput == specialAction1Released) inputMap.put(Input.SPECIALACTION1, State.KEYRELEASED);

        else if (keyboardInput == moveRightPressed) {
            if (inputMap.get(Input.MOVERIGHT) == State.KEYRELEASED) {
                inputMap.put(Input.MOVERIGHT, State.KEYPRESSEDONCE);
            } else inputMap.put(Input.MOVERIGHT, State.KEYPRESSED);
        } else if (keyboardInput == moveRightReleased) inputMap.put(Input.MOVERIGHT, State.KEYRELEASED);

        else if (keyboardInput == moveLeftPressed) {
            if (inputMap.get(Input.MOVELEFT) == State.KEYRELEASED) {
                inputMap.put(Input.MOVELEFT, State.KEYPRESSEDONCE);
            } else inputMap.put(Input.MOVELEFT, State.KEYPRESSED);
        } else if (keyboardInput == moveLeftReleased) inputMap.put(Input.MOVELEFT, State.KEYRELEASED);

        else if (keyboardInput == jumpPressed) {
            if (inputMap.get(Input.JUMP) == State.KEYRELEASED) {
                inputMap.put(Input.JUMP, State.KEYPRESSEDONCE);
            } else inputMap.put(Input.JUMP, State.KEYPRESSED);
        } else if (keyboardInput == jumpReleased) inputMap.put(Input.JUMP, State.KEYRELEASED);

        else if (keyboardInput == attackPressed) {
            if (inputMap.get(Input.ATTACK) == State.KEYRELEASED) {
                inputMap.put(Input.ATTACK, State.KEYPRESSEDONCE);
            } else inputMap.put(Input.ATTACK, State.KEYPRESSED);
        } else if (keyboardInput == attackReleased) inputMap.put(Input.ATTACK, State.KEYRELEASED);

        /*for(Map.Entry<Input, State> e : inputMap.entrySet()){
            System.out.println(""+e.getKey()+" -> "+e.getValue());
        }*/
    }

    /**
     * Handle input combinations
     *
     * @return the command to play
     */
    public CharacterAction handleInput() {


        if (checkPressedOnce(Input.SPECIALACTION1)) return CharacterAction.SPECIALACTION1;
        if (checkPressedOnce(Input.ATTACK)) return CharacterAction.ATTACK;
        if (checkPressed(Input.JUMP) && checkPressed(Input.MOVERIGHT) && checkPressed(Input.MOVELEFT))
            return CharacterAction.JUMP;
        if (checkPressed(Input.JUMP) && checkPressed(Input.MOVERIGHT)) return CharacterAction.JUMPRIGHT;
        if (checkPressed(Input.JUMP) && checkPressed(Input.MOVELEFT)) return CharacterAction.JUMPLEFT;
        if (checkPressed(Input.JUMP)) return CharacterAction.JUMP;
        if (checkPressed(Input.MOVERIGHT) && checkPressed(Input.MOVELEFT)) return CharacterAction.IDLE;
        if (checkPressed(Input.MOVERIGHT)) return CharacterAction.MOVERIGHT;
        if (checkPressed(Input.MOVELEFT)) return CharacterAction.MOVELEFT;

        return null;
    }

    /**
     * Check if the key is pressed
     *
     * @param input Key to check
     * @return True if the key is pressed, false either
     */
    public boolean checkPressed(Input input) {
        return (inputMap.get(input) == State.KEYPRESSEDONCE || inputMap.get(input) == State.KEYPRESSED);
    }

    public boolean checkPressedOnce(Input input) {
        return inputMap.get(input) == State.KEYPRESSEDONCE;
    }

    public boolean checkJustReleased(Input input) {
        return inputMap.get(input) == State.KEYJUSTRELEASED;
    }

}
