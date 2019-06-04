package fr.bomberman.game.entity;

import fr.bomberman.game.entity.tile.Tile;
import fr.bomberman.game.entity.tile.TileType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Bomb extends Entity {


    /**
     * Crée une bombe avec une image et une position x et y
     *
     * @param image L'image de la bombe
     * @param x     La position en x
     * @param y     La position en y
     */
    public Bomb(Image image, int x, int y) {
        super(image, x, y);
        this.getSprite().setOpacity(0);
    }


    /**
     * Affiche l'explosion sur les tuiles qui ne sont pas un mur
     */
    public void explosion() {
        Tile tile = getBoard().getByCoords(getSprite().getX(), getSprite().getY());
        if (tile != null) {
            int xup = tile.getX();
            int yup = tile.getY() - 50;

            int xdown = tile.getX();
            int ydown = tile.getY() + 50;

            int xleft = tile.getX() - 50;
            int yleft = tile.getY();

            int xright = tile.getX() + 50;
            int yright = tile.getY();

            if (getBoard().getByCoords(xup, yup).getType() != TileType.INTERN_WALL) {
                draw(xup, yup);
            }
            if (getBoard().getByCoords(xdown, ydown).getType() != TileType.INTERN_WALL) {
                draw(xdown, ydown);
            }
            if (getBoard().getByCoords(xleft, yleft).getType() != TileType.INTERN_WALL) {
                draw(xleft, yleft);
            }
            if (getBoard().getByCoords(xright, yright).getType() != TileType.INTERN_WALL) {
                draw(xright, yright);
            }
        }
    }


    /**
     * Affiche les flammes sur le plateau
     * @param x La position en x
     * @param y La position en y
     */
    private void drawExplosion(int x, int y) {
        getGraphicsContext().drawImage(getSpriteManager().get("explosion"), x, y);
    }


    /**
     * Affiche l'herbe avec l'explosion
     * @param x La position en x
     * @param y La position en y
     */
    private void drawGrass(int x, int y) {
        getGraphicsContext().drawImage(getSpriteManager().get("grass"), x, y);
    }


    /**
     * Dessine l'explosion et remplace les flammes par de l'herbe avec 1 seconde
     * @param x La position en x
     * @param y La position en y
     */
    private void draw(int x, int y) {
        Tile newTile = getBoard().getByCoords(x, y);
        newTile.getSprite().setImage(getSpriteManager().get("grass"));
        newTile.setType(TileType.GRASS);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, event -> drawExplosion(x, y)),
                new KeyFrame(Duration.seconds(1), event -> drawGrass(x, y))
        );
        timeline.play();
    }

}
