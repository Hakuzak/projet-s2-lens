package fr.bomberman.game.entity;

import javafx.scene.image.Image;

public class Bonus extends Entity {

    /**
     * Crée un bonus en fonction de son sprite et de sa position en x et y
     *
     * @param image Le sprite
     * @param x     La position en x
     * @param y     La position en y
     */
    public Bonus(Image image, int x, int y) {
        super(image, x, y);
    }


}
