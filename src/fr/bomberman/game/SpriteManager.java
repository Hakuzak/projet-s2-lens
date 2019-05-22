package fr.bomberman.game;

import fr.bomberman.game.exception.InvalidSpriteManager;
import javafx.scene.image.Image;

import java.io.File;
import java.util.HashMap;

public class SpriteManager {

    private HashMap<String, Image> sprites;

    public SpriteManager() {
        this.sprites = new HashMap<>();
    }

    public void load(String name, String path) {
        File file = new File(path);
        if(file.exists()) {
            sprites.put(name, new Image(file.toURI().toString()));
        }
    }

    public Image get(String name) {
        try {
            for(String s : sprites.keySet()) {
                if(s == name) return sprites.get(name);
            }
            throw new InvalidSpriteManager(name);

        } catch(InvalidSpriteManager e) {
            e.describe();
            System.exit(1);
        }

        return null;
    }

}
