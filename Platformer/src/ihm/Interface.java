package ihm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Interface {

	private static DrawingCanvas drawingCanvas;
    private JFrame gameFrame;
    private GameMenu gameMenu;

    public Interface() {

        gameFrame = new JFrame("Platformer Game !");

        gameFrame.setTitle("Platformer");
        gameFrame.setPreferredSize(new Dimension(900, 700));
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameMenu = new GameMenu();
        gameMenu.setLocation(gameFrame.getWidth() / 4, gameFrame.getHeight() / 4);
        gameFrame.add(gameMenu);
        gameFrame.setContentPane(gameMenu);
        gameFrame.pack();
        gameFrame.setVisible(true);
        while (gameMenu.gameMenuLoop()) ;

        drawingCanvas = new DrawingCanvas(gameFrame.getSize());
        gameFrame.add(drawingCanvas);

        gameFrame.pack();
        gameFrame.setVisible(true);

        gameFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                drawingCanvas.stop();
                System.exit(0);
            }
        });

		drawingCanvas.start(); // start a thread hosting the game

    }

    public static void main(String[] args){
		
		Interface i = new Interface();

    }
}
