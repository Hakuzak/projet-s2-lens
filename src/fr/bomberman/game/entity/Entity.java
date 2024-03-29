package fr.bomberman.game.entity;

import fr.bomberman.game.Board;
import fr.bomberman.game.SpriteManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Entity extends Shape {

    private ImageView sprite;
    private int x, y;
    private double width, height;
    private static SpriteManager spriteManager;
    protected static Board board;
    protected static GraphicsContext graphicsContext;
    protected static Stage stage;


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
     * Retourne le contexte graphique
     *
     * @return GraphicsContext
     */
    protected GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }


    /**
     * Assigne le contexte graphique
     *
     * @param gc Le contexte graphique
     */
    public static void setGraphicsContext(GraphicsContext gc) {
        graphicsContext = gc;
    }


    /**
     * Assigne le stage
     *
     * @param stg Le stage
     */
    public static void setStage(Stage stg) {
        stage = stg;
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
