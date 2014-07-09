package world;

import action.IdleAction;
import action.JumpAction;
import action.MoveAction;
import action.TeleportAction;
import graphic.SpriteSheet;

import java.awt.image.BufferedImage;

/**
 * MainCharacter class
 * Used to reify the user character
 */
public class MainCharacter extends GameActor {

    public MainCharacter(int x, int y, float velocityX, float velocityY) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;

        ss = new SpriteSheet("Platformer/res/sprite_sheet_kabuto.png");
        move = new MoveAction(ss, "move", 10);
        jump = new JumpAction(ss, "jump", 10);
        idle = new IdleAction(ss, "idle", 10);
        specialAction1 = new TeleportAction(ss, "teleport", 16);
        currentAction = idle;

    }

    /*public void startMoving(Movement movement) {

        // Check if moving while jumping
        if (onGround) this.movement = movement;
        velocityX = movement.getVelocityX();
    }

    public void stopMoving(Movement movement) {
        velocityX = 0;
        if (onGround) this.movement = movement;
        else this.movement = Movement.JUMPING;

        System.out.println("Stopmoving");
    }

    public void jump(Movement jump) {

        movement = jump;
        this.velocityX = jump.getVelocityX();

        if (onGround) {
            this.velocityY = jump.getVelocityY();
            onGround = false;
        }
    }

    @Override
    public void startSpecialAction1(CharacterAction action) {

        actionHandler.startAction(action);
    }

    @Override
    public void stopSpecialAction1(CharacterAction action) {

    }*/

    public void update(float gravity) {

        currentAction.update();

        velocityY += gravity;

        y += velocityY;
        x += velocityX;

        /*if(velocityX > 0) velocityX--;
        else if(velocityX < 0) velocityX++;*/

        if (y > 500) {
            y = 500;
            onGround = true;
            velocityY = 0;
        }

        //System.out.println(movement);
        //if (onGround && currentAction == jump)
        //  currentAction = idle;


    }

    @Override
    public void handleInput(CharacterAction action) {

        //System.out.println(""+currentAction);
        //System.out.println("VelocityX "+velocityX+"     VelocityY"+velocityY);
        if (action == null) {
            currentAction.stop(this);
            action = CharacterAction.DEFAULT;
        }
        currentAction.execute(this, action);
        /*if(currentAction.isStoppable()){

            switch (action){
                case MOVERIGHT:
                    currentAction = move;
                    move.updateVelocity(10, 0);
                    break;
                case MOVELEFT:
                    currentAction = move;
                    move.updateVelocity(-10, 0);
                    break;
                case JUMP:
                    currentAction = jump;
                    jump.updateVelocity(0, -10);
                    break;
                case JUMPLEFT:
                    currentAction = jump;
                    jump.updateVelocity(-(int)velocityX, -10);
                    break;
                case JUMPRIGHT:
                    currentAction = jump;
                    jump.updateVelocity((int)velocityX, -10);
                    break;
                case SPECIALACTION1:
            }
            currentAction.execute(this);
        }*/
    }

    public void handleCollision() {

        if (x < 0) x = 0;
        else if (x > 800) x = 800;
    }

    @Override
    public BufferedImage render() {

        //System.out.println("CharacterAction : "+action);
        /*if (actionHandler.actionPlaying()) {

            switch (movement) {
                case MOVINGRIGHT:
                case JUMPINGRIGHT:
                case JUMPING:
                case IDLE:
                    return actionHandler.getActionImage();
                case MOVINGLEFT:
                case JUMPINGLEFT:
                    return flipImage(actionHandler.getActionImage());
            }
        }

        switch (movement) {
            case MOVINGRIGHT:
                return getMoveImage();
            case MOVINGLEFT:
                return flipImage(getMoveImage());
            case JUMPING:
                return getJumpImage();
            case JUMPINGRIGHT:
                return getJumpImage();
            case JUMPINGLEFT:
                return flipImage(getJumpImage());
            case IDLE:
                return getIdleImage();
        }*/

        return currentAction.getActiveImage();
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



    /*public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }*/

}
