package world;

import com.sun.javafx.scene.traversal.Direction;

/**
 * Created by Glenn on 13/08/2014.
 */
public class CollisionData {

    Direction collisionDirection;
    int xCorrection, yCorrection;

    public Direction getCollisionDirection() {
        return collisionDirection;
    }

    public void setCollisionDirection(Direction collisionDirection) {
        this.collisionDirection = collisionDirection;
    }

    public int getxCorrection() {
        return xCorrection;
    }

    public void setxCorrection(int xCorrection) {
        this.xCorrection = xCorrection;
    }

    public int getyCorrection() {
        return yCorrection;
    }

    public void setyCorrection(int yCorrection) {
        this.yCorrection = yCorrection;
    }
}
