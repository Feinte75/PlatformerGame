package ihm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Interface extends JFrame {

	private static DrawingCanvas drawingCanvas;
	
	public Interface(){
		
		setTitle("Platformer");
        setPreferredSize(new Dimension(900, 700));
        setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		drawingCanvas = new DrawingCanvas(getSize());
		add(drawingCanvas);
		pack();
		
		drawingCanvas.start(); // start a thread hosting the game
	}
	
	public static void main(String[] args){
		
		Interface i = new Interface();
		
		i.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				drawingCanvas.stop();
				System.exit(0);
			}
		});
	}
}
