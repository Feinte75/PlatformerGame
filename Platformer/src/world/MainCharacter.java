package world;

public class MainCharacter {
	
	private int x, y;
	private float velocityX, velocityY;
	private boolean onGround;
	private Movement movement;
	private Movement lastMovement;
	private int nbKeyPressed;
	
	public MainCharacter(int x, int y, float velocityX, float velocityY){
		this.x = x;
		this.y = y;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.movement = Movement.IDLE;
		this.lastMovement = Movement.IDLE;
		this.nbKeyPressed = 0;
	}
	
	public void startMoving(Movement movement){
        this.movement = movement;

	}
	
	public void stopMoving(Movement movement){

        this.movement = Movement.IDLE;
	}
	
	public void jump(float velocityY){
		if(onGround){
			this.velocityY = velocityY;
			onGround = false;
            movement = Movement.JUMPING;
		}			
	}
	
	public void stopJump(){
		if(velocityY < -6.0f)velocityY = -6.0f;
		System.out.println("Stop jump !");
	}

    public void update(float gravity){

        velocityY += gravity;
        y += velocityY;
        x += movement.getVelocityX();

        if(y > 500){
            y = 500;
            onGround = true;
            velocityY = 0;
        }

        if(onGround && movement == Movement.JUMPING) movement = Movement.IDLE;
    }

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}
	
	public int getY(){
		return y;
	}

	public Movement getMovement() {
		return movement;
	}

	public void setMovement(Movement movement) {
		this.movement = movement;
	}
	
}
