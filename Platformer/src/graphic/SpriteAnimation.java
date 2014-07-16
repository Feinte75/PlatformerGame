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
import java.util.LinkedList;

/**
 * Created by Glenn on 24/06/2014.
 * Sprite animation class
 * Used to store multiple images
 */
public class SpriteAnimation {

    protected LinkedList<BufferedImage> images;
    protected LinkedList<Rectangle> boundingBoxes;
    protected int index = 0;
    protected int counter = 0;
    protected int animationSpeed;
    boolean lock;

    public SpriteAnimation(String name, String identifier, int animationSpeed) {

        images = new LinkedList<BufferedImage>();
        //images = ss.getAnimation(identifier);
        loadImages(name, identifier);
        this.animationSpeed = animationSpeed;
    }

    public void loadImages(String character, String action) {

        FileReader fileReader = null;

        try {
            fileReader = new FileReader("Platformer/conf/characters/" + character + ".json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(new JSONTokener(fileReader));
        BufferedImage spriteSheet = null;

        try {
            spriteSheet = ImageIO.read(new File("Platformer/res/" + jsonObject.getString("spritesheet") + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject actionLoaded = jsonObject.getJSONObject("animations").getJSONObject(action);
        JSONObject loadedImages = actionLoaded.getJSONObject("images");

        int x, y, width, height;
        int i = 1;
        JSONArray imageDelimitors = null;
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

}
