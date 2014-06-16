package ihm;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import action.InputHandler;
import action.SpriteSheet;
import world.MainCharacter;
import world.Movement;
import action.JumpAction;
import action.MoveAction;

public class DrawingCanvas extends JPanel implements Runnable{
	
	private Thread thread;
	private boolean playing = true;

    // Input
    private InputHandler inputHandler;

    // Graphical variables
    private SpriteSheet ss;
    private BufferedImage run1;
    private BufferedImage run2;
    private BufferedImage run3;
    private BufferedImage run4;
    private BufferedImage run5;
    private BufferedImage run6;
    private BufferedImage jump1;
    private BufferedImage idle;

	float gravity;
	
	MainCharacter character;
	
	
	public DrawingCanvas(Dimension size){

		setSize(size);
		setVisible(true);
		
		gravity = 0.9f;
		character = new MainCharacter(50, 500, 4.0f, 0.0f);

        inputHandler = new InputHandler(this, character);

        ss = new SpriteSheet("Platformer/res/spritesheet.png");

        run1 = ss.getRun1();
        run2 = ss.getRun2();
        run3 = ss.getRun3();
        run4 = ss.getRun4();
        run5 = ss.getRun5();
        run6 = ss.getRun6();

        jump1 = ss.getJump1();
        idle = ss.getIdle();
	}
	
	/**
	 * Game loop. Runs the game and keep it running at good FPS. 
	 */
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long cycleTime;
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks; // number of nanoseconds per ticks
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		cycleTime = System.currentTimeMillis();

		while (playing) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns; 
			lastTime = now;
			if (delta >= 1) { // update 
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) { // reset every second
				timer += 1000;
				//System.out.println(updates + "Ticks, Fps" + frames);
				updates = 0;
				frames = 0;
			}
			cycleTime = synchroFramerate(cycleTime);
		}
	}
	
	public long synchroFramerate(Long cycleTime) {
		cycleTime = cycleTime + 10; // add the number of millisecond necessary to complete a loop iteration
		long difference = cycleTime - System.currentTimeMillis();
		try {
			Thread.sleep(Math.max(0, difference));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return cycleTime;
	}
	
	/**
	 * Compute AI and update the world
	 */
	public void tick() {
		
		character.update(gravity);
	}
	
	/**
	 * renders the world
	 */
	public void render(){
		repaint();
	}
	
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		Graphics2D graphic2D = (Graphics2D) g;

		/*
		 * Game Rendering
		 */
        // Flip the image vertically


        switch (character.getMovement()){
            case MOVINGRIGHT: ((Graphics2D) g).drawImage(run1, null, character.getX(), character.getY());
                break;
            case MOVINGLEFT: ((Graphics2D) g).drawImage(flipImage(run1), null, character.getX(), character.getY());
                break;
            case JUMPING: ((Graphics2D) g).drawImage(jump1, null, character.getX(), character.getY());
                break;
            case IDLE: ((Graphics2D) g).drawImage(idle, null, character.getX(), character.getY());
                break;
        }


		graphic2D.dispose();
	}
	
	/**
	 * Starts a thread which launches the run method
	 */
	public void start() {

		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Stop the thread
	 */
	public void stop() {
		
		playing = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}

    public BufferedImage flipImage(BufferedImage original){

        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-original.getWidth(),0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        original = op.filter(original, null);

        return original;
    }

	public void setPlaying(boolean b) {
		playing = b;
	}

	
}
