package fr.bomberman.game.entity;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Timer;
import java.util.TimerTask;

public class Player extends Entity {

    private String name;
    private int lifes;
    private float score;
    private Bomb bomb;

    public Player(Image image, int x, int y, String name) {
        super(image, x, y);
        this.name = name;
        this.lifes = 3;
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
            if (collideY(50) || getSprite().getX() % 100 == 0) moveDown();
            else moveUp();
        }

        // Move down
        if(e.getCode() == KeyCode.S) {
            this.getSprite().setImage(getSpriteManager().get("player_down2"));
            if (collideY(650) || getSprite().getX() % 100 == 0) moveUp();
            else moveDown();
        }

        // Move left
        if(e.getCode() == KeyCode.A) {
            // TODO : Add left sprite !

            if (collideX(0) || getSprite().getY() % 100 == 0) {
                moveRight();
            } else moveLeft();
        }

        // Move right
        if(e.getCode() == KeyCode.D) {
            this.getSprite().setImage(getSpriteManager().get("player_right2"));
            if (collideX(650) || getSprite().getY() % 100 == 0) moveLeft();
            else moveRight();
        }

        // Place bomb
        if (e.getCode() == KeyCode.SPACE) {
            // TODO : Place bomb and start timer for explode
            this.bomb.getSprite().setX(this.getSprite().getX());
            this.bomb.getSprite().setY(this.getSprite().getY());
            this.bomb.getSprite().setOpacity(1);
            explodeBomb();
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

    public float getScore() {
        return score;
    }

    public void dead() {
        this.lifes--;
    }

    public void setScore(float score) {
        this.score += score;
    }

    protected boolean collideX(int nb) {
        return getSprite().getX() == nb;
    }

    protected boolean collideY(int nb) {
        return getSprite().getY() == nb;
    }

    private void moveUp() {
        this.getSprite().setY(this.getSprite().getY() - 50);
    }

    private void moveDown() {
        this.getSprite().setY(this.getSprite().getY() + 50);
    }

    private void moveLeft() {
        this.getSprite().setX(this.getSprite().getX() - 50);
    }

    private void moveRight() {
        this.getSprite().setX(this.getSprite().getX() + 50);
    }

    public void setBomb(Bomb b) {
        this.bomb = b;
    }

    public void explodeBomb() {
        final Image bomb2 = getSpriteManager().get("bomb2");
        final Image bomb3 = getSpriteManager().get("bomb3");

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                bomb.getSprite().setImage(bomb2);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                bomb.getSprite().setImage(bomb3);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                bomb.getSprite().setOpacity(0);
                // TODO : Create explosion animation !
            }
        }, 1000);
    }

}
