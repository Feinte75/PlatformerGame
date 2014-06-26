package input;

import action.Command;
import action.JumpAction;
import action.MoveAction;
import action.TeleportAction;
import listeners.InputListener;
import world.MainCharacter;
import world.Movement;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

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
    private TeleportAction teleport;
    private TeleportAction teleportReleased;

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

        teleport = new TeleportAction(true);
        teleportReleased = new TeleportAction(false);

        // Key bindings
        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed LEFT"), "moveLeft");
        canvas.getActionMap().put("moveLeft", moveLeft);
        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released LEFT"), "moveLeftReleased");
        canvas.getActionMap().put("moveLeftReleased", stopMovingLeft);

        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed RIGHT"), "moveRight");
        canvas.getActionMap().put("moveRight", moveRight);
        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released RIGHT"), "moveRightReleased");
        canvas.getActionMap().put("moveRightReleased", stopMovingRight);

        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed UP"), "jump");
        canvas.getActionMap().put("jump", jump);
        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released UP"), "jumpReleased");
        canvas.getActionMap().put("jumpReleased", jumpReleased);

        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed A"), "teleport");
        canvas.getActionMap().put("teleport", teleport);
        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released A"), "teleportReleased");
        canvas.getActionMap().put("teleportReleased", teleportReleased);

        // Subscribe the input handler to every actions
        moveLeft.addInputListener(this);
        moveRight.addInputListener(this);
        stopMovingLeft.addInputListener(this);
        stopMovingRight.addInputListener(this);
        jump.addInputListener(this);
        jumpReleased.addInputListener(this);
        teleport.addInputListener(this);
        teleportReleased.addInputListener(this);

    }

    /**
     * Update the inputMap with the new input state
     * When the event come from a pressed key, check if it is the first time
     * If it is -> update the inputMap to KEYPRESSEDONCE
     * If not -> update to KEYPRESSED
     *
     * @param input InputEvent The event source
     */
    @Override
    public void inputEvent(InputEvent input) {

        Input source = (Input) input.getSource();

        if (input.isKeyPressed()) {
            if (inputMap.get(source) == State.KEYRELEASED) inputMap.put(source, State.KEYPRESSEDONCE);
            else inputMap.put(source, State.KEYPRESSED);
        } else {
            //if (inputMap.get(source) == State.KEYPRESSED) inputMap.put(source, State.KEYJUSTRELEASED);
            //else
            inputMap.put(source, State.KEYRELEASED);
        }

        for (Map.Entry<Input, State> is : inputMap.entrySet()) {
            System.out.println("Value : " + is.getValue() + " Key : " + is.getKey());
        }
    }

    /**
     * Handle input combinations
     *
     * @return the command to play
     */
    public Command handleInput() {


        if (checkPressedOnce(Input.TELEPORT)) return teleport;
        if (checkPressed(Input.JUMP) && checkPressed(Input.MOVERIGHT) && checkPressed(Input.MOVELEFT)) return jump;
        if (checkPressed(Input.JUMP) && checkPressed(Input.MOVERIGHT)) return jumpRight;
        if (checkPressed(Input.JUMP) && checkPressed(Input.MOVELEFT)) return jumpLeft;
        if (checkPressed(Input.JUMP)) return jump;
        if (checkPressed(Input.MOVERIGHT) && checkPressed(Input.MOVELEFT)) return idle;
        if (checkPressed(Input.MOVERIGHT)) return moveRight;
        if (checkPressed(Input.MOVELEFT)) return moveLeft;

        return idle;
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

    private enum State {

        KEYPRESSEDONCE,

        KEYPRESSED,

        KEYJUSTRELEASED,

        KEYRELEASED;
    }
}
