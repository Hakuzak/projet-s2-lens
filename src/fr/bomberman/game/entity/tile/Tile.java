package fr.bomberman.game.entity.tile;

import fr.bomberman.game.entity.Entity;
import javafx.scene.image.Image;

public class Tile extends Entity {

    TileType type;

    public Tile(Image image, int x, int y, TileType type) {
        super(image, x, y);
        this.type = type;
    }

    public TileType getType() {
        return type;
    }

}
