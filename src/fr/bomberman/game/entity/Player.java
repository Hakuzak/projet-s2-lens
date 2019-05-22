package fr.bomberman.game.entity;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Player extends Entity {

    private String name;
    private int lifes, nbBombs;
    private float score;

    public Player(Image image, int x, int y, String name) {
        super(image, x, y);
        this.name = name;
        this.lifes = 3;
        this.nbBombs = 10;
        this.score = 0;
    }

    public void handleEvents(Canvas c) {
        c.setOnKeyPressed(this::handlePressed);
        c.setOnKeyReleased(this::handleReleased);
    }

    private void handlePressed(KeyEvent e) {

        // Move up
        if(e.getCode() == KeyCode.W) {
            this.getSprite().setImage(getSpriteManager().get("player_up2"));
            this.getSprite().setY(this.getSprite().getY() - 12.5);
        }

        // Move down
        if(e.getCode() == KeyCode.S) {
            this.getSprite().setImage(getSpriteManager().get("player_down2"));
            this.getSprite().setY(this.getSprite().getY() + 12.5);
        }

        // Move left
        if(e.getCode() == KeyCode.A) {
            // TODO : Add left sprite !

            this.getSprite().setX(this.getSprite().getX() - 12.5);
        }

        // Move right
        if(e.getCode() == KeyCode.D) {
            this.getSprite().setImage(getSpriteManager().get("player_right2"));
            this.getSprite().setX(this.getSprite().getX() + 12.5);
        }

    }

    private void handleReleased(KeyEvent e) {
        if(e.getCode() == KeyCode.W) {
            this.getSprite().setImage(getSpriteManager().get("player_up1"));
        }
        if(e.getCode() == KeyCode.S) {
            this.getSprite().setImage(getSpriteManager().get("player_down1"));
        }
        if(e.getCode() == KeyCode.A) {
            // TODO : Add left sprite !
        }
        if(e.getCode() == KeyCode.D) {
            this.getSprite().setImage(getSpriteManager().get("player_right1"));
        }
    }

    public String getName() {
        return name;
    }

    public int getLifes() {
        return lifes;
    }

    public int getNbBombs() {
        return nbBombs;
    }

    public float getScore() {
        return score;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public void setScore(float score) {
        this.score = score;
    }

}
