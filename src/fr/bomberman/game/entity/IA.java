package fr.bomberman.game.entity;

import fr.bomberman.game.entity.tile.TileType;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.Random;

public class IA extends Player {

    private Random random;
    private int nbBombs;
    private int lifes;


    /**
     * Créer une intelligence artificielle qui est contrôlée par l'ordinateur
     *
     * @param image Le sprite
     * @param x     La position en x
     * @param y     La position en y
     */
    public IA(String nom, Image image, int x, int y) {
        super(nom, image, x, y);
        this.random = new Random();
        this.lifes = 3;
    }


    /**
     * Assigne les bombes
     * @param b Le tableau de bombes de l'IA
     */
    public void setBombs(Bomb[] b) {
        bombs = b;
    }



    /**
     * Génère un nombre aléatoire en 0 et 3 qui permet de déplacer aléatoirement l'IA vers une direction définie
     */
    private void move(int moveCount) {
        int dir = random.nextInt(3) + 1;
        int m = 0;

        if (dir == 0) {
            while (m <= moveCount) {
                moveDir("up");
                m++;
            }

        } else if (dir == 1) {
            while (m <= moveCount) {
                moveDir("down");
                m++;
            }

        } else if (dir == 2) {
            while (m <= moveCount) {
                moveDir("left");
                m++;
            }

        } else {
            while (m <= moveCount) {
                moveDir("right");
                m++;
            }
        }
    }


    /**
     * Déplacement l'IA en fonction de la direction choisie
     *
     * @param dir La direction vers laquelle l'IA doit se diriger
     */
    private void moveDir(String dir) {
        if (dir.equals("up")) {
            this.getSprite().setImage(getSpriteManager().get("ia_up"));

            if ((!collideY(50) && getSprite().getX() % 100 == 0) || collideY(50) || getBoard().getByCoords(getSprite().getX(), getSprite().getY() - 50).getType() == TileType.DESTRUCTIBLE) {
            } else moveUp();

        } else if (dir.equals("down")) {
            this.getSprite().setImage(getSpriteManager().get("ia_down"));

            if ((!collideY(550) && getSprite().getX() % 100 == 0) || collideY(550) || getBoard().getByCoords(getSprite().getX(), getSprite().getY() + 50).getType() == TileType.DESTRUCTIBLE) {
            } else moveDown();

        } else if (dir.equals("left")) {
            this.getSprite().setImage(getSpriteManager().get("ia_left"));

            if ((!collideX(0) && getSprite().getY() % 100 == 0) || collideX(50) || getBoard().getByCoords(getSprite().getX() - 50, getSprite().getY()).getType() == TileType.DESTRUCTIBLE) {
            } else moveLeft();

        } else if (dir.equals("right")) {
            this.getSprite().setImage(getSpriteManager().get("ia_right"));

            if ((!collideX(950) && getSprite().getY() % 100 == 0) || collideX(950) || getBoard().getByCoords(getSprite().getX() + 50, getSprite().getY()).getType() == TileType.DESTRUCTIBLE) {
            } else moveRight();
        }
    }


    /**
     * Retourne le nombre de vies de l'IA
     * @return int
     */
    public int getLifes() {
        return lifes;
    }


    /**
     * Enlève une vie à l'IA
     */
    public void dead() {
        this.lifes--;
    }


    /**
     * Se déplace et pose une bombe toutes les X secondes
     */
    public void play() {
//        int moveCount = random.nextInt(2) + 1;
        int actualisation = (int) (Math.random() * 5) + 1;
//
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//
//            }
//        }, 0, actualisation);

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(actualisation),
                e -> {
                    move((int) (Math.random() * 5) + 1);

                    if (nbBombs == 3) nbBombs = 0;
                    placeBomb();

                    move((int) (Math.random() * 5) + 1);
                }
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
