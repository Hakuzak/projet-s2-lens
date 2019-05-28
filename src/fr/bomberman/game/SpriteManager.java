package fr.bomberman.game;

import fr.bomberman.game.exception.InvalidSpriteManager;
import javafx.scene.image.Image;

import java.io.File;
import java.util.HashMap;

public class SpriteManager {

    private HashMap<String, Image> sprites;

    /**
     * Initialise le gestionnaire de sprites
     */
    public SpriteManager() {
        this.sprites = new HashMap<>();
    }

    /**
     * Charge une image en lui assignant un nom
     *
     * @param name Le nom de l'image qui servira à la récupérer
     * @param path Le chemin de l'image
     */
    public void load(String name, String path) {
        File file = new File(path);
        if(file.exists()) {
            sprites.put(name, new Image(file.toURI().toString()));
        }
    }

    /**
     * Retourne l'image si elle existe, null sinon
     * @param name Le nom de l'image
     * @return Image
     */
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
