package world;

import graphic.SpriteAnimation;
import graphic.SpriteSheet;

public class MainCharacter extends GameActor {

    private boolean onGround;
    private Movement movement;
    private Movement lastMovement;
    private int nbKeyPressed;

    public MainCharacter(int x, int y, float velocityX, float velocityY) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.movement = Movement.IDLE;
        this.lastMovement = Movement.IDLE;
        this.nbKeyPressed = 0;
        ss = new SpriteSheet("Platformer/res/spritesheet.png");
        move = new SpriteAnimation(ss, "move");
        jump = new SpriteAnimation(ss, "jump");
        idle = new SpriteAnimation(ss, "idle");
    }

    public void startMoving(Movement movement) {

        // Check if moving while jumping
        if (onGround) this.movement = movement;
        velocityX = movement.getVelocityX();
    }

    public void stopMoving(Movement movement) {
        velocityX = 0;
        if (onGround) this.movement = movement;
        else this.movement = Movement.JUMPING;
    }

    public void jump(Movement jump) {

        movement = jump;
        this.velocityX = jump.getVelocityX();
        if (onGround) {

            this.velocityY = jump.getVelocityY();
            onGround = false;
        }
    }

    public void update(float gravity) {

        velocityY += gravity;
        y += velocityY;
        x += velocityX;

        if (y > 500) {
            y = 500;
            onGround = true;
            velocityY = 0;
        }
        //System.out.println(movement);
        if (onGround && (movement == Movement.JUMPING || movement == Movement.JUMPINGRIGHT || movement == Movement.JUMPINGLEFT))
            movement = Movement.IDLE;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

}
