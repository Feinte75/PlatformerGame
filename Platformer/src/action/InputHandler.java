package action;

import world.MainCharacter;
import world.Movement;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Created by Glenn on 15/06/2014.
 */
public class InputHandler {

    // Input actions
    private MoveAction moveRight;
    private MoveAction stopMoving;
    private MoveAction moveLeft;
    private JumpAction jump;
    private JumpAction jumpReleased;

    public InputHandler(JPanel canvas, MainCharacter character) {

        moveRight = new MoveAction(Movement.MOVINGRIGHT, character, true);
        stopMoving = new MoveAction(Movement.IDLE, character, false);
        moveLeft = new MoveAction(Movement.MOVINGLEFT, character, true);

        jump = new JumpAction(-20f, character, true);
        jumpReleased = new JumpAction(-12f, character, false);

        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0),"moveLeft");
        canvas.getActionMap().put("moveLeft", moveLeft);
        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true),"moveLeftReleased");
        canvas.getActionMap().put("moveLeftReleased", stopMoving);

        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0),"moveRight");
        canvas.getActionMap().put("moveRight", moveRight);
        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true),"moveRightReleased");
        canvas.getActionMap().put("moveRightReleased", stopMoving);

        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0),"jump");
        canvas.getActionMap().put("jump", jump);
        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true),"jumpReleased");
        canvas.getActionMap().put("jumpReleased", jumpReleased);
    }

    public Command handleInput(){

        return null;
    }
}
