package ihm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Glenn on 22/08/2014.
 */
public class GameMenu extends JPanel implements MouseListener {

    // Menu
    private JButton newGame;
    private JButton editor;
    private JButton quit;

    boolean gameStart;
    boolean editorStart;
    boolean close;

    public GameMenu() {

        gameStart = false;
        editorStart = false;
        close = false;

        // Menu
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        newGame = new JButton("New Game");
        editor = new JButton("Editor");
        quit = new JButton("Quit");

        newGame.addMouseListener(this);
        editor.addMouseListener(this);
        quit.addMouseListener(this);
        setBorder(new EmptyBorder(new Insets(40, 60, 40, 60)));
        addMouseListener(this);

        add(newGame);
        add(editor);
        add(quit);
    }


    public boolean gameMenuLoop() {

            try {
                Thread.sleep(1000);
                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        return true;

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        JButton pressedButton = (JButton) e.getSource();
        System.out.println("Click");
        if(pressedButton == newGame) gameStart = true;
        else if(pressedButton == editor) editorStart = true;
        else if(pressedButton == quit) close = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public boolean isGameStart() {
        return gameStart;
    }

    public boolean isEditorStart() {
        return editorStart;
    }

    public boolean isClose() {
        return close;
    }
}
