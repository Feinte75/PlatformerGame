package ihm;

import action.Command;
import graphic.SpriteSheet;
import input.InputHandler;
import world.MainCharacter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class DrawingCanvas extends JPanel implements Runnable{

    float gravity = 1f;
    MainCharacter character;
    private Thread thread;
    private boolean playing = true;
    // Input
    private InputHandler inputHandler;
    // Graphical variables
    private BufferedImage bufferImage;
    private SpriteSheet ss;
    private BufferedImage run1;
    private BufferedImage run2;
    private BufferedImage run3;
    private BufferedImage run4;
    private BufferedImage run5;
    private BufferedImage run6;
    private BufferedImage jump1;
    private BufferedImage idle;
	
	
	public DrawingCanvas(Dimension size){

		setSize(size);
		setVisible(true);

        character = new MainCharacter(50, 500, 0f, 0.0f);

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
            repaint();
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

    /**
     * Ensure that the game loop runs at good fps by sleeping if game loop was finished to quickly
     *
     * @param cycleTime
     * @return
     */
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

        Command command = inputHandler.handleInput();

        if (command != null) {
            //System.out.println("Commande : " +command);
            command.execute(character);
        }
        character.update(gravity);
    }
	
	/**
	 * renders the world
	 */
	public void render() {

        if (bufferImage == null) {
            bufferImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        }

        Graphics2D g2D = (Graphics2D) bufferImage.getGraphics();

        // Set background
        g2D.setColor(Color.WHITE);
        g2D.fillRect(0, 0, this.getWidth(), this.getHeight());

        // Flip the image vertically
        switch (character.getMovement()) {
            case MOVINGRIGHT:
                g2D.drawImage(run1, null, character.getX(), character.getY());
                break;
            case MOVINGLEFT:
                g2D.drawImage(flipImage(run1), null, character.getX(), character.getY());
                break;
            case JUMPING:
                g2D.drawImage(jump1, null, character.getX(), character.getY());
                break;
            case JUMPINGRIGHT:
                g2D.drawImage(jump1, null, character.getX(), character.getY());
                break;
            case JUMPINGLEFT:
                g2D.drawImage(flipImage(jump1), null, character.getX(), character.getY());
                break;
            case IDLE:
                g2D.drawImage(idle, null, character.getX(), character.getY());
                break;
        }

        g2D.dispose();
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

		/*
         * Game Painting
		 */

        g2D.drawImage(bufferImage, null, 0, 0);

        g2D.dispose();
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

    /**
     * Flip image
     * @param original image to flip
     * @return Flipped image
     */
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
