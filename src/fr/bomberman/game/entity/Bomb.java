package fr.bomberman.game.entity;

import fr.bomberman.game.Menu.Death;
import fr.bomberman.game.entity.tile.Tile;
import fr.bomberman.game.entity.tile.TileType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
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
    public void explosion(Player e) {
        Tile tile = getBoard().getByCoords(getSprite().getX(), getSprite().getY());
        if (tile != null) {
            int xup = tile.getX();
            int yup = tile.getY() - 50;

            int yup2 = tile.getY() - 100;

            int xdown = tile.getX();
            int ydown = tile.getY() + 50;

            int ydown2 = tile.getY() + 100;

            int xleft = tile.getX() - 50;
            int yleft = tile.getY();

            int xleft2 = tile.getX() - 100;

            int xright = tile.getX() + 50;
            int yright = tile.getY();

            int xright2 = tile.getX() + 100;


            if (getBoard().getByCoords(xup, yup) != null && getBoard().getByCoords(xup, yup).getType() != TileType.INTERN_WALL) {
                if (getBoard().getByCoords(xup, yup2) != null && getBoard().getByCoords(xup, yup2).getType() != TileType.INTERN_WALL && getBoard().getByCoords(xup, yup).getType() == TileType.GRASS) {
                    draw(xup, yup2);
                    if(e.getSprite().getX() == xup && e.getSprite().getY() == yup2) e.dead();
                }
                draw(tile.getX(), tile.getY());
                draw(xup, yup);
                if(e.getSprite().getX() == xup && e.getSprite().getY() == yup) e.dead();
            }


            if (getBoard().getByCoords(xdown, ydown) != null && getBoard().getByCoords(xdown, ydown).getType() != TileType.INTERN_WALL) {
                if (getBoard().getByCoords(xdown, ydown2) != null && getBoard().getByCoords(xdown, ydown2).getType() != TileType.INTERN_WALL && getBoard().getByCoords(xdown, ydown).getType() == TileType.GRASS) {
                    draw(xdown, ydown2);
                    if(e.getSprite().getX() == xdown && e.getSprite().getY() == ydown2) e.dead();
                }
                draw(tile.getX(), tile.getY());
                draw(xdown, ydown);
                if(e.getSprite().getX() == xdown && e.getSprite().getY() == ydown) e.dead();
            }


            if (getBoard().getByCoords(xleft, yleft) != null && getBoard().getByCoords(xleft, yleft).getType() != TileType.INTERN_WALL) {
                if (getBoard().getByCoords(xleft2, yleft) != null && getBoard().getByCoords(xleft2, yleft).getType() != TileType.INTERN_WALL && getBoard().getByCoords(xleft, yleft).getType() == TileType.GRASS) {
                    draw(xleft2, yleft);
                    if(e.getSprite().getX() == xleft2 && e.getSprite().getY() == yleft) e.dead();
                }
                draw(tile.getX(), tile.getY());
                draw(xleft, yleft);
                if(e.getSprite().getX() == xleft && e.getSprite().getY() == yleft) e.dead();
            }


            if (getBoard().getByCoords(xright, yright) != null && getBoard().getByCoords(xright, yright).getType() != TileType.INTERN_WALL) {
                if (getBoard().getByCoords(xright2, yright) != null && getBoard().getByCoords(xright2, yright).getType() != TileType.INTERN_WALL && getBoard().getByCoords(xright, yright).getType() == TileType.GRASS) {
                    draw(xright2, yright);
                    if(e.getSprite().getX() == xright2 && e.getSprite().getY() == yright) e.dead();
                }
                draw(tile.getX(), tile.getY());
                draw(xright, yright);
                if(e.getSprite().getX() == xright && e.getSprite().getY() == yright) e.dead();
            }

            if(e.getSprite().getX() == tile.getX() && e.getSprite().getY() == tile.getY()) e.dead();


            if(e.getLifes() <= 0 && e.getClass().getName().equals("fr.bomberman.game.entity.Player")) stage.setScene(new Death(new Group(), 600, 600, stage));

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
