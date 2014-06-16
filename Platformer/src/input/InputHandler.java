package input;

import action.Command;
import action.JumpAction;
import action.MoveAction;
import listeners.InputListener;
import world.MainCharacter;
import world.Movement;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 * Created by Glenn on 15/06/2014.
 */
public class InputHandler implements InputListener {

    // Input actions
    private MoveAction moveRight;
    private MoveAction idle;
    private MoveAction moveLeft;
    private JumpAction jump;
    //private JumpAction jumpReleased;

    private LinkedList<Input> inputQueue;

    public InputHandler(JPanel canvas, MainCharacter character) {

        inputQueue = new LinkedList<Input>();

        moveRight = new MoveAction(Movement.MOVINGRIGHT, true);
        idle = new MoveAction(Movement.IDLE, false);
        moveLeft = new MoveAction(Movement.MOVINGLEFT, true);

        jump = new JumpAction(-20f, true);
        //jumpReleased = new JumpAction(0f, false);

        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0),"moveLeft");
        canvas.getActionMap().put("moveLeft", moveLeft);
        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true),"moveLeftReleased");
        canvas.getActionMap().put("moveLeftReleased", idle);

        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0),"moveRight");
        canvas.getActionMap().put("moveRight", moveRight);
        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true),"moveRightReleased");
        canvas.getActionMap().put("moveRightReleased", idle);

        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0),"jump");
        canvas.getActionMap().put("jump", jump);
        //canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true),"jumpReleased");
        //canvas.getActionMap().put("jumpReleased", jumpReleased);

        moveLeft.addInputListener(this);
        moveRight.addInputListener(this);
        idle.addInputListener(this);
        jump.addInputListener(this);
        //jumpReleased.addInputListener(this);
    }

    @Override
    public void inputEvent(InputEvent input) {
        inputQueue.addFirst((Input) input.getSource());
    }

    public Command handleInput(){
        Input input;

        if (inputQueue.size() != 0) input = inputQueue.removeLast();
        else return null;

        if (input == Input.JUMP) return jump;
        //if(input == Input.JUMPRELEASED) return jumpReleased;
        if (input == Input.MOVERIGHT) return moveRight;
        if (input == Input.MOVELEFT) return moveLeft;
        if (input == Input.IDLE) return idle;

        return null;
    }
}
