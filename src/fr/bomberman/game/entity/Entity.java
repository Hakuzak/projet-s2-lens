package fr.bomberman.game.entity;

import fr.bomberman.game.Board;
import fr.bomberman.game.SpriteManager;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;

public class Entity extends Shape implements IEntity {

    private ImageView sprite;
    private int x, y;
    private double width, height;
    private static SpriteManager spriteManager;
    private static Board board;

    /**
     * Crée une entité en fonction de son sprite et de sa position en x et y
     *
     * @param image Le sprite
     * @param x     La position en x
     * @param y     La position en y
     */
    public Entity(Image image, int x, int y) {
        this.sprite = new ImageView(image);
        this.sprite.setSmooth(true);

        this.x = x;
        this.y = y;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    /**
     * Retourne le gestionnaire de sprites
     * @return SpriteManager
     */
    public SpriteManager getSpriteManager() {
        return spriteManager;
    }

    /**
     * Assigne le gestionnaire de sprites
     *
     * @param sm Le gestionnaire de sprites
     */
    public static void setSpriteManager(SpriteManager sm) {
        spriteManager = sm;
    }

    /**
     * Retourne le plateau de jeu
     *
     * @return Board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Assigne le plateau de jeu
     *
     * @param b Le gestionnaire de sprites
     */
    public static void setBoard(Board b) {
        board = b;
    }

    /**
     * Retourne une ImageView correspondant au sprite
     * @return ImageView
     */
    public ImageView getSprite() {
        return sprite;
    }

    /**
     * Retourne la position en x de l'entité
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * Retourne la position en y de l'entité
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     * Retourne la largeur de l'entité
     * @return double
     */
    public double getWidth() {
        return width;
    }

    /**
     * Retourne la hauteur de l'entité
     * @return double
     */
    public double getHeight() {
        return height;
    }

}
