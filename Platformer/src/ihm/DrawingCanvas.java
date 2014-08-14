package ihm;

import input.InputHandler;
import world.CharacterAction;
import world.MainCharacter;
import world.Tilemap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DrawingCanvas extends JPanel implements Runnable {

    float gravity = 1f;
    MainCharacter character;
    private Thread thread;
    private boolean playing = true;
    // Input
    private InputHandler inputHandler;
    // Graphical variables
    private BufferedImage bufferImage;
    private BufferedImage background;

    private Tilemap tilemap;

    // Fps display
    private int ticks, fps;

    public DrawingCanvas(Dimension size) {

        setSize(size);
        setVisible(true);

        character = new MainCharacter(50, 400, 0f, 0.0f);

        inputHandler = new InputHandler(this, character);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Platformer/res/backgrounds/bridge_background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image bg = img.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        background = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        background.getGraphics().drawImage(bg, 0, 0, null);
        background.getGraphics().dispose();

        tilemap = new Tilemap();
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
                ticks = updates;
                fps = frames;
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
     * @param cycleTime elapsed time since the beginning of the cycle
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
     * Updates the world : character, ennemies. Compute AI. Handle collision
     */
    public void tick() {

        /*
        *   Input handling
        */
        CharacterAction action = inputHandler.handleInput();
        character.handleInput(action);

        /*
         *  Gravity
         */
        character.update(gravity);

        /*
        *   Collision with environment
        */

        //character.handleCollision();
        Rectangle temp = character.getActiveHitbox();
        Point p = tilemap.collisionDetection(temp);

        System.out.println("X : " + character.getX() + " Y : " + character.getY() + " Velocity X :"
                        + character.getVelocityX() + " Velocity Y:" + character.getVelocityY()
                        + " Correction position X : " + p.getX() + " Correction position Y : " + p.getY()
                        + " point precedent : " + temp.getLocation() + " nouveau point : " + p
        );

        if (!p.equals(temp.getLocation())) {
            character.setX((int) p.getX());
            character.setY((int) p.getY() + (int) temp.getHeight());
            character.setOnGround(true);
            tilemap.setCollide(true);
        } else {
            character.setOnGround(false);
            tilemap.setCollide(false);
        }

        /*
        *   Collision with moving entities
        */
        // TODO
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
        g2D.setColor(Color.CYAN);
        g2D.fillRect(0, 0, this.getWidth(), this.getHeight());

        //g2D.drawImage(background, null, 0, 0);

        tilemap.renderTiles(g2D);
        g2D.setFont(new Font(null, Font.ITALIC, 25));
        g2D.drawString(fps + ": Fps , Ticks :" + ticks, 600, 550);
        g2D.drawImage(character.render(), null, character.getX() - 10, character.getY() - 40); // minus to adapt to screen coordinate

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

    public void setPlaying(boolean b) {
        playing = b;
    }


}
