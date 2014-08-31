package world;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Glenn on 18/08/2014.
 */
public class CollisionHandler {

    public CollisionHandler() {

    }

    public void tilemapCollisionCheck(MainCharacter character, Tilemap tilemap) {

        Rectangle characterHitbox = character.getActiveHitbox();

        boolean contactX = true, contactYTop = true, contactYBottom = true;
        Tiles tiles[][] = tilemap.getTiles();
        LinkedList<Point> collidedTiles = new LinkedList<Point>();

        // Stop solving collisions after 3 times or when no collision occurs
        for (int iteration = 0; iteration < 3 && (contactX || contactYBottom || contactYTop); iteration++) {

            // Compute next move expected position
            int nextMoveX = character.getVelocityX();
            int nextMoveY = character.getVelocityY();

            Rectangle futurCharacterHitbox = (Rectangle) characterHitbox.clone();
            futurCharacterHitbox.translate(nextMoveX, nextMoveY);
            // Translate hitbox to fit futur move
            //characterHitbox.translate(nextMoveX, nextMoveY);

            // Check collision with bounding boxes
            for (int i = 0; i <= 40; i++) {
                for (int j = 0; j <= 24; j++) {

                    if (tiles[i][j] == Tiles.WALL || tiles[i][j] == Tiles.WALLCOLLIDE) {
                        Rectangle temp = new Rectangle(i * 20, j * 20, 20, 20);


                        if (futurCharacterHitbox.intersects(temp)) {

                            collidedTiles.add(new Point(i, j));
                            tiles[i][j] = Tiles.WALLCOLLIDE;
                            System.out.println("Collision between character :" + characterHitbox + " and : " + temp
                                            + " Tile : " + i + " " + j + " Collide : " + tiles[i][j]
                                    //+" BotRight : "+botRight+" BotLeft : "+botLeft
                                    //+" \n Rect : "+rect
                            );
                        } else tiles[i][j] = Tiles.WALL;
                    }
                }
            }

            contactX = contactYBottom = contactYTop = false;

            int projectedMoveX, projectedMoveY, originalMoveX, originalMoveY;
            int vectorLength;
            int segments;

            // Store the original final expected movement of the player so we can
            // see if it has been modified due to a collision or potential collision later
            originalMoveX = nextMoveX;
            originalMoveY = nextMoveY;

            Iterator<Point> iter = collidedTiles.listIterator();

            while (iter.hasNext() && !contactX && !contactYBottom && !contactYTop) {

                Point collidedTile = iter.next();
                Rectangle rect = new Rectangle((int) collidedTile.getX() * 20, (int) collidedTile.getY() * 20, 20, 20);

                Point upLeft = characterHitbox.getLocation();
                //upLeft.translate(projectedMoveX, projectedMoveY);

                Point upRight = characterHitbox.getLocation();
                upRight.translate((int) characterHitbox.getWidth(), 0);

                Point botLeft = characterHitbox.getLocation();
                botLeft.translate(0, (int) characterHitbox.getHeight());
                //if(dir>1)botLeft.translate(0, -15);

                Point botRight = characterHitbox.getLocation();
                botRight.translate((int) characterHitbox.getWidth(), (int) characterHitbox.getHeight());
                //if(dir > 1)botRight.translate(0, -15);

                // We will test the four possible directions of player movement individually
                // dir: 0 = top, 1 = bottom, 2 = left, 3 = right
                for (int dir = 0; dir < 4; dir++) {
                    if (dir == 0 && nextMoveY > 0) continue;
                    if (dir == 1 && nextMoveY < 0) continue;
                    if (dir == 2 && nextMoveX >= 0) continue;
                    if (dir == 3 && nextMoveX <= 0) continue;

                    projectedMoveX = (dir >= 2 ? nextMoveX : 0);
                    projectedMoveY = (dir < 2 ? nextMoveY : 0);

                    if (dir == 0) {
                        while (rect.contains(upLeft.getX() + projectedMoveX, upLeft.getY() + projectedMoveY) || rect.contains(upRight.getX() + projectedMoveX, upRight.getY() + projectedMoveY)) {
                            projectedMoveY++;
                        }
                    }

                    if (dir == 1) {
                        while (rect.contains(botLeft.getX() + projectedMoveX, botLeft.getY() + projectedMoveY) || rect.contains(botRight.getX() + projectedMoveX, botRight.getY() + projectedMoveY)) {
                            projectedMoveY--;
                        }
                    }

                    if (dir == 2) {
                        while (rect.contains(botLeft.getX() + projectedMoveX, botLeft.getY() + projectedMoveY - 5) || rect.contains(upLeft.getX() + projectedMoveX, upLeft.getY() + projectedMoveY)) {
                            projectedMoveX++;
                            /*System.out.println("UpRight :"+upRight+" UpLeft : "+upLeft
                                    +" BotRight : "+botRight+" BotLeft : "+botLeft
                                    +" \n Rect : "+rect);*/
                        }
                    }

                    if (dir == 3) {
                        while (rect.contains(upRight.getX() + projectedMoveX, upRight.getY() + projectedMoveY) || rect.contains(botRight.getX() + projectedMoveX, botRight.getY() + projectedMoveY - 5)) {
                            projectedMoveX--;
                            /*System.out.println("UpRight :"+upRight+" UpLeft : "+upLeft
                                    +" BotRight : "+botRight+" BotLeft : "+botLeft
                                    +" \n Rect : "+rect);*/
                        }
                    }

                    if (dir >= 2 && dir <= 3)
                        nextMoveX = projectedMoveX;
                    if (dir >= 0 && dir <= 1)
                        nextMoveY = projectedMoveY;
                }

                // Detect what type of contact has occurred based on a comparison of
                // the original expected movement vector and the new one

                if (nextMoveY > originalMoveY && originalMoveY < 0) {
                    contactYTop = true;
                    System.out.println("Contact top");
                }

                if (nextMoveY < originalMoveY && originalMoveY > 0) {
                    contactYBottom = true;
                    System.out.println("Contact bottom");
                }

                if (Math.abs(nextMoveX - originalMoveX) > 0.01f) {
                    contactX = true;
                    System.out.println("Contact X");
                }

                if (contactX && contactYBottom && character.getVelocityY() < 0) {
                    character.setVelocityY(0);
                    nextMoveY = 0;
                }

                if (contactX || contactYBottom || contactYTop)
                    System.out.println("Bilan : "
                                    + " OriginalMoveX: " + originalMoveX + " OriginalMoveY: " + originalMoveY
                                    + " NextMoveX: " + nextMoveX + " NextMoveY: " + nextMoveY
                                    + " Rect : " + rect

                    );
            }
            // If a contact has been detected, apply the re-calculated movement vector
            // and disable any further movement this frame (in either X or Y as appropriate)
            if (contactYBottom || contactYTop) {
                character.setY(character.getY() + nextMoveY);
                character.setVelocityY(0);

                if (contactYBottom)
                    character.setOnGround(true);
            }

            if (contactX) {
                character.setX(character.getX() + nextMoveX);
                character.setVelocityX(0);
            }
        }


    }
}
