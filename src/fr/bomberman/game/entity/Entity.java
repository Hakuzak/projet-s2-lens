package fr.bomberman.game.entity;

import fr.bomberman.game.SpriteManager;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;

public class Entity extends Shape {

    private ImageView sprite;
    private Image image;
    private int x, y;
    private double width, height;
    private static SpriteManager spriteManager;

    public Entity(Image image, int x, int y) {
        this.sprite = new ImageView(image);
        this.sprite.setSmooth(true);

        this.x = x;
        this.y = y;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public static void setSpriteManager(SpriteManager sm) {
        spriteManager = sm;
    }

    public SpriteManager getSpriteManager() {
        return spriteManager;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
