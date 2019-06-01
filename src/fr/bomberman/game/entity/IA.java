package fr.bomberman.game.entity;

import fr.bomberman.game.entity.tile.TileType;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class IA extends Player {

    Random random;
    private Bomb[] bombs;
    private int nbPlacedBombs;

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
            this.getSprite().setImage(getSpriteManager().get("ia_up"));

            if ((!collideY(50) && getSprite().getX() % 100 == 0) || collideY(50) || getBoard().getByCoords(getSprite().getX(), getSprite().getY() - 50).getType() == TileType.DESTRUCTIBLE) {
            } else moveUp();
        }

        // Move down
        if (dir == 1) {
            this.getSprite().setImage(getSpriteManager().get("ia_down"));
            if ((!collideY(650) && getSprite().getX() % 100 == 0) || collideY(650) || getBoard().getByCoords(getSprite().getX(), getSprite().getY() + 50).getType() == TileType.DESTRUCTIBLE) {
            } else moveDown();
        }

        // Move left
        if (dir == 2) {
            this.getSprite().setImage(getSpriteManager().get("ia_left"));

            if ((!collideX(0) && getSprite().getY() % 100 == 0) || collideX(50) || getBoard().getByCoords(getSprite().getX() - 50, getSprite().getY()).getType() == TileType.DESTRUCTIBLE) {
            } else moveLeft();
        }

        // Move right
        if (dir == 3) {
            this.getSprite().setImage(getSpriteManager().get("ia_right"));
            if ((!collideX(650) && getSprite().getY() % 100 == 0) || collideX(650) || getBoard().getByCoords(getSprite().getX() + 50, getSprite().getY()).getType() == TileType.DESTRUCTIBLE) {
            } else moveRight();
        }
    }

    /**
     * Permet à l'IA de placer une bombe toutes les X secondes
     */
    private void placeBomb() {
        // TODO : Place bomb
        if (nbPlacedBombs == 3) nbPlacedBombs = 0;

        bombs[nbPlacedBombs].getSprite().setX(this.getSprite().getX());
        bombs[nbPlacedBombs].getSprite().setY(this.getSprite().getY());
        bombs[nbPlacedBombs].getSprite().setOpacity(1);
        explodeBomb(bombs[nbPlacedBombs]);

        nbPlacedBombs++;
    }

    protected void explodeBomb(Bomb b) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                b.getSprite().setImage(getSpriteManager().get("bomb2"));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                b.getSprite().setImage(getSpriteManager().get("bomb3"));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String path = new File("assets/musics/boom.mp3").toURI().toString();
                AudioClip music = new AudioClip(path);
                music.play();

                b.getSprite().setOpacity(0);

                b.getSprite().setImage(getSpriteManager().get("bomb1"));
                // TODO : Create explosion animation !


            }
        }, 1000);
    }

    /**
     * Se déplace et pose une bombe toutes les X secondes
     */
    public void play() {
        int timeMove = getRandomNumberBetween(0, 5) * 1000;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                move();

                if (nbPlacedBombs == 3) nbPlacedBombs = 0;

                bombs[nbPlacedBombs].getSprite().setX(getSprite().getX());
                bombs[nbPlacedBombs].getSprite().setY(getSprite().getY());
                bombs[nbPlacedBombs].getSprite().setOpacity(1);
                explodeBomb(bombs[nbPlacedBombs]);

                nbPlacedBombs++;
            }
        }, 0, timeMove);
    }

    public void setBombs(Bomb[] b) {
        this.bombs = b;
    }

}
