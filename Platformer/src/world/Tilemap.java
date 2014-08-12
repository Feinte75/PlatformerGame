package world;

import java.awt.*;

/**
 * Created by Glenn on 12/08/2014.
 */
public class Tilemap {

    private Tiles[][] tiles;
    private boolean collide;

    public Tilemap() {
        tiles = new Tiles[41][25];
        tiles[24][23] = Tiles.WALL;
        tiles[24][24] = Tiles.WALL;
    }

    public void renderTiles(Graphics2D graphics2D) {

        graphics2D.setColor(Color.BLACK);
        for (int i = 0; i <= 40; i++) {
            for (int j = 0; j <= 24; j++) {
                if (collide) graphics2D.setColor(Color.RED);
                else graphics2D.setColor(Color.BLACK);

                if (tiles[i][j] == Tiles.WALL) graphics2D.fillRect(i * 20, j * 20, 20, 20);
                else graphics2D.drawRect(i * 20, j * 20, 20, 20);
            }
        }
    }

    public boolean collisionDetection(Rectangle hitbox) {

        if (hitbox.intersects(new Rectangle(480, 460, 20, 40))) collide = true;
        else collide = false;

        return collide;
    }

}
