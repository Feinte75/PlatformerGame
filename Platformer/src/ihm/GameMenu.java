package ihm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by Glenn on 22/08/2014.
 */
public class GameMenu extends JPanel {

    // Menu
    private JButton newGame;
    private JButton editor;
    private JButton quit;

    public GameMenu() {

        // Menu
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        newGame = new JButton("New Game");
        editor = new JButton("Editor");
        quit = new JButton("Quit");

        setBorder(new EmptyBorder(new Insets(40, 60, 40, 60)));
        add(newGame);
        add(editor);
        add(quit);
    }


    public boolean gameMenuLoop() {

        while (true) {
            try {
                Thread.sleep(1000);
                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
