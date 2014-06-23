package input;

import action.Command;
import action.JumpAction;
import action.MoveAction;
import listeners.InputListener;
import world.MainCharacter;
import world.Movement;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Created by Glenn on 15/06/2014.
 */
public class InputHandler implements InputListener {

    // Input actions
    private MoveAction moveRight;
    private MoveAction stopMovingRight;
    private MoveAction idle;
    private MoveAction moveLeft;
    private MoveAction stopMovingLeft;
    private JumpAction jump;
    private JumpAction jumpReleased;
    private JumpAction jumpRight;
    private JumpAction jumpLeft;

    private HashMap<Input, State> inputMap;

    public InputHandler(JPanel canvas, MainCharacter character) {

        inputMap = new HashMap<Input, State>();
        for (Input i : Input.values()) {
            inputMap.put(i, State.KEYRELEASED);
        }

        moveRight = new MoveAction(Movement.MOVINGRIGHT, true);
        stopMovingRight = new MoveAction(Movement.MOVINGRIGHT, false);
        moveLeft = new MoveAction(Movement.MOVINGLEFT, true);
        stopMovingLeft = new MoveAction(Movement.MOVINGLEFT, false);

        idle = new MoveAction(Movement.IDLE, true);

        jump = new JumpAction(Movement.JUMPING, true);
        jumpReleased = new JumpAction(Movement.JUMPING, false);
        jumpRight = new JumpAction(Movement.JUMPINGRIGHT, true);
        jumpLeft = new JumpAction(Movement.JUMPINGLEFT, true);


        // Key bindings
        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "moveLeft");
        canvas.getActionMap().put("moveLeft", moveLeft);
        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "moveLeftReleased");
        canvas.getActionMap().put("moveLeftReleased", stopMovingLeft);

        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "moveRight");
        canvas.getActionMap().put("moveRight", moveRight);
        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "moveRightReleased");
        canvas.getActionMap().put("moveRightReleased", stopMovingRight);

        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "jump");
        canvas.getActionMap().put("jump", jump);
        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "jumpReleased");
        canvas.getActionMap().put("jumpReleased", jumpReleased);

        // Subscribe the input handler to every actions
        moveLeft.addInputListener(this);
        moveRight.addInputListener(this);
        stopMovingLeft.addInputListener(this);
        stopMovingRight.addInputListener(this);
        jump.addInputListener(this);
        jumpReleased.addInputListener(this);

    }

    /**
     * Update the inputMap with the new input state
     *
     * @param input InputEvent
     */
    @Override
    public void inputEvent(InputEvent input) {

        Input source = (Input) input.getSource();

        if (!input.isKeyPressed()) inputMap.put(source, State.KEYRELEASED);
        else {
            if (inputMap.get(input) == State.KEYRELEASED) inputMap.put(source, State.KEYPRESSEDONCE);
            else inputMap.put(source, State.KEYPRESSED);
        }
    }

    /**
     * Handle input combinations
     *
     * @return the command to play
     */
    public Command handleInput() {

        /*for(Map.Entry<Input, State> e : inputMap.entrySet()){
            if(e.getValue() != State.KEYRELEASED)System.out.println("Key : "+e.getKey() + " Value : " +e.getValue());
        }*/

        if (checkPressed(Input.JUMP) && checkPressed(Input.MOVERIGHT) && checkPressed(Input.MOVELEFT)) return jump;
        if (checkPressed(Input.JUMP) && checkPressed(Input.MOVERIGHT)) return jumpRight;
        if (checkPressed(Input.JUMP) && checkPressed(Input.MOVELEFT)) return jumpLeft;
        if (checkPressed(Input.JUMP)) return jump;
        if (checkPressed(Input.MOVERIGHT) && checkPressed(Input.MOVELEFT)) return idle;
        if (checkPressed(Input.MOVERIGHT)) return moveRight;
        if (checkPressed(Input.MOVELEFT)) return moveLeft;

        return idle;
    }

    public boolean checkPressed(Input input) {
        return (inputMap.get(input) == State.KEYPRESSEDONCE || inputMap.get(input) == State.KEYPRESSED);
    }

    private enum State {

        KEYPRESSEDONCE,

        KEYPRESSED,

        KEYRELEASED;
    }
}
