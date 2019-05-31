package fr.bomberman.game.entity;

import javafx.scene.image.Image;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class IA extends Player {

    Random random;
    private Bomb[] bombs;

    /**
     * Créer une intelligence artificielle qui est contrôlée par l'ordinateur
     *
     * @param image Le sprite
     * @param x     La position en x
     * @param y     La position en y
     * @param name  Le nom du de l'intelligence artificielle
     */
    public IA(Image image, int x, int y, String name) {
        super(image, x, y, name);
        random = new Random();
    }

    /**
     * Génération aléatoire entre un nombre minimal et maximal
     *
     * @param min Nombre minimal
     * @param max Nombre maximal
     * @return int
     */
    private int getRandomNumberBetween(int min, int max) {
        Random foo = new Random();
        int randomNumber = foo.nextInt(max - min) + min;
        if (randomNumber == min) {
            return min + 1;
        } else {
            return randomNumber;
        }
    }

    private void move() {
        int dir = getRandomNumberBetween(0, 4);
        if (dir == 0) {
            // Move up
            getSprite().setImage(getSpriteManager().get("player_up2"));
            if ((!collideY(50) && getSprite().getX() % 100 == 0) || collideY(50)) {
                move();
            } else moveUp();

        } else if (dir == 1) {
            // Move down
            getSprite().setImage(getSpriteManager().get("player_down2"));
            if ((!collideY(650) && getSprite().getX() % 100 == 0) || collideY(650)) {
                move();
            } else moveDown();

        } else if (dir == 2) {
            // Move left
//            getSprite().setImage(getSpriteManager().get("player_left2"));
            if ((!collideX(0) && getSprite().getY() % 100 == 0) || collideX(50)) {
                move();
            } else moveLeft();

        } else {
            // Move right
            getSprite().setImage(getSpriteManager().get("player_right2"));
            if ((!collideX(650) && getSprite().getY() % 100 == 0) || collideX(650)) {
                move();
            } else moveRight();
        }
    }

    private void placeBomb() {
        explodeBomb();
    }

    private void explodeBomb() {

    }

    public void play() {
        int timeMove = getRandomNumberBetween(0, 5) * 1000;
        int timeBomb = getRandomNumberBetween(0, 10) * 1000;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                move();
            }
        }, 0, timeMove);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                placeBomb();
            }
        }, 0, timeBomb);
    }

}
