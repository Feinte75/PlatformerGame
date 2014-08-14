package graphic;

import json.JSONArray;
import json.JSONException;
import json.JSONObject;
import json.JSONTokener;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Glenn on 24/06/2014.
 * Sprite animation class
 * Used to store multiple images
 */
public class SpriteAnimation {

    protected LinkedList<BufferedImage> images;
    protected HashMap<Integer, Rectangle> hitBox;

    protected int index = 0;
    protected int counter = 0;
    protected int animationSpeed;

    public SpriteAnimation(String entityName, String actionIdentifier, String category) {

        images = new LinkedList<BufferedImage>();
        hitBox = new HashMap<Integer, Rectangle>();
        //images = ss.getAnimation(identifier);
        loadImages(entityName, actionIdentifier, category);
    }

    /**
     * Load every images of the animation
     *
     * @param entityName       The name of the entity to load, it can be a character name, a powerball etc
     * @param actionIdentifier The name of the action to load for the specified entityName, for example, the special action of the character kabuto
     * @param category         The entity's category. characters, effect, power
     */
    public void loadImages(String entityName, String actionIdentifier, String category) {

        FileReader fileReader = null;

        try {
            fileReader = new FileReader("Platformer/conf/" + category + "/" + entityName + ".json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(new JSONTokener(fileReader));
        BufferedImage spriteSheet = null;

        // Get images sprite sheet
        try {
            spriteSheet = ImageIO.read(new File("Platformer/res/" + category + "/" + jsonObject.getString("spritesheet") + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Extract animation speed
        JSONObject actionLoaded = jsonObject.getJSONObject("animations").getJSONObject(actionIdentifier);
        animationSpeed = actionLoaded.getInt("animationspeed");

        JSONObject loadedImages = actionLoaded.getJSONObject("images");

        // Load each images and its bounding boxes
        int x, y, width, height;
        int i = 1;
        JSONArray imageDelimitors = null;
        Rectangle box;
        while (true) {

            try {
                imageDelimitors = loadedImages.getJSONArray(Integer.toString(i));
            } catch (JSONException e) {
                break;
            }


            x = imageDelimitors.getInt(0);
            y = imageDelimitors.getInt(1);
            width = imageDelimitors.getInt(2);
            height = imageDelimitors.getInt(3);

            // TODO : add bounding boxes in json files
            hitBox.put(i - 1, new Rectangle(x, y, width, height));
            images.add(spriteSheet.getSubimage(x, y, width, height));
            i++;
        }
    }

    public void update() {

        counter++;
        if (counter % animationSpeed == 0) index++;
        if (index >= images.size()) index = 0;
    }

    public void resetIndex() {
        index = 0;
    }

    public BufferedImage getActiveImage() {
        return images.get(index);
    }

    public Rectangle getActiveHitBox() {
        return hitBox.get(index);
    }
}
