package world;

public enum Movement {
	
	IDLE(0,0),
	MOVINGLEFT(-10, 0),
	MOVINGRIGHT(10, 0),
	JUMPINGLEFT(-10, -12),
	JUMPINGRIGHT(10, -12),
	JUMPING(0,-12);

	private int velocityX, velocityY;
	
	Movement(int velocityX, int velocityY){
		this.velocityX = velocityX;
		this.velocityY = velocityY;
	}
	
	public int getVelocityX(){
		return velocityX;
	}
	
	public int getVelocityY(){
		return velocityY;
	}
}
