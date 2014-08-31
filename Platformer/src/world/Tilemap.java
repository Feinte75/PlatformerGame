package world;

import java.awt.*;
import java.util.LinkedList;

/**
 * Tilemap used to diplay and compute collision with environment
 */
public class Tilemap {

    private Tiles[][] tiles;
    private boolean collide;
    private LinkedList<CollisionData> collisionData;

    public Tilemap() {

        collisionData = new LinkedList<CollisionData>();

        tiles = new Tiles[41][25];
        tiles[24][23] = Tiles.WALL;
        tiles[24][24] = Tiles.WALL;
        tiles[10][20] = Tiles.WALL;
        tiles[11][20] = Tiles.WALL;
        tiles[12][20] = Tiles.WALL;
        tiles[10][19] = Tiles.WALL;
        tiles[11][19] = Tiles.WALL;
        tiles[12][19] = Tiles.WALL;
        tiles[24][24] = Tiles.WALL;

        for (int i = 0; i <= 40; i++) {
            tiles[i][0] = Tiles.WALL;
            tiles[i][24] = Tiles.WALL;

            if (i < 25) {
                tiles[0][i] = Tiles.WALL;
                tiles[40][i] = Tiles.WALL;
            }
        }

    }

    public void renderTiles(Graphics2D graphics2D) {

        graphics2D.setColor(Color.BLACK);
        for (int i = 0; i <= 40; i++) {
            for (int j = 0; j <= 24; j++) {

                if (tiles[i][j] == Tiles.WALL) {
                    graphics2D.setColor(Color.BLACK);
                    graphics2D.fillRect(i * 20, j * 20, 20, 20);
                } else if (tiles[i][j] == Tiles.WALLCOLLIDE) {
                    graphics2D.setColor(Color.RED);
                    graphics2D.fillRect(i * 20, j * 20, 20, 20);
                } else graphics2D.drawRect(i * 20, j * 20, 20, 20);
            }
        }
    }

    //LinkedList<CollisionData>
    public Point collisionDetection(Rectangle hitbox) {

        collisionData.clear();
        Rectangle copy = (Rectangle) hitbox.clone();
        for (int i = 0; i <= 40; i++) {
            for (int j = 0; j <= 24; j++) {

                if (tiles[i][j] == Tiles.WALL) {

                    Rectangle temp = new Rectangle(i * 20, j * 20, 20, 20);
                    if (copy.intersects(temp)) {

                    }
                }
            }
        }
        return copy.getLocation();
    }

    public void setCollide(boolean collide) {
        this.collide = collide;
    }

    public Tiles[][] getTiles() {
        return tiles;
    }
}
