package fr.bomberman.game.entity;

import javafx.scene.image.Image;

public class Bomb extends Entity {

    public Bomb(Image image, int x, int y) {
        super(image, x, y);
        this.getSprite().setOpacity(0);
    }

}
