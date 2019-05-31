package fr.bomberman.game.entity;

import fr.bomberman.game.entity.tile.TileType;
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

    /**
     * Génère un nombre aléatoire en 0 et 3 qui permet de déplacer aléatoirement l'IA vers une direction définie
     */
    private void move() {
        int dir = getRandomNumberBetween(0, 4);

        // Move up
        if (dir == 0) {
            this.getSprite().setImage(getSpriteManager().get("player_up2"));

            if ((!collideY(50) && getSprite().getX() % 100 == 0) || collideY(50) || getBoard().getByCoords(getSprite().getX(), getSprite().getY() - 50).getType() == TileType.DESTRUCTIBLE) {
            } else moveUp();
        }

        // Move down
        if (dir == 1) {
            this.getSprite().setImage(getSpriteManager().get("player_down2"));
            if ((!collideY(650) && getSprite().getX() % 100 == 0) || collideY(650) || getBoard().getByCoords(getSprite().getX(), getSprite().getY() + 50).getType() == TileType.DESTRUCTIBLE) {
            } else moveDown();
        }

        // Move left
        if (dir == 2) {
            this.getSprite().setImage(getSpriteManager().get("player_left2"));

            if ((!collideX(0) && getSprite().getY() % 100 == 0) || collideX(50) || getBoard().getByCoords(getSprite().getX() - 50, getSprite().getY()).getType() == TileType.DESTRUCTIBLE) {
            } else moveLeft();
        }

        // Move right
        if (dir == 3) {
            this.getSprite().setImage(getSpriteManager().get("player_right2"));
            if ((!collideX(650) && getSprite().getY() % 100 == 0) || collideX(650) || getBoard().getByCoords(getSprite().getX() + 50, getSprite().getY()).getType() == TileType.DESTRUCTIBLE) {
            } else moveRight();
        }
    }

    /**
     * Permet à l'IA de placer une bombe toutes les X secondes
     */
    private void placeBomb() {
        // TODO : Place bomb
        explodeBomb();
    }

    /**
     * Gère l'explosion de la bombe ainsi que son animation et les collisions avec le joueur et elle-même
     */
    private void explodeBomb() {
        // TODO : Explode bomb
    }

    /**
     * Se déplace et pose une bombe toutes les X secondes
     */
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
