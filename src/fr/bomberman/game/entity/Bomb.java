package fr.bomberman.game.entity;

import fr.bomberman.game.entity.tile.Tile;
import javafx.scene.image.Image;

public class Bomb extends Entity {

    public Bomb(Image image, int x, int y) {
        super(image, x, y);
        this.getSprite().setOpacity(0);
    }

    public void explosion() {
        Tile tile = getBoard().getByCoords(getSprite().getX(), getSprite().getY());
        if (tile != null) {
            getGraphicsContext().drawImage(getSpriteManager().get("explosion"), getSprite().getX() - getSprite().getImage().getWidth() / 4, getSprite().getY() - getSprite().getImage().getHeight() / 4);
        }
    }

}
