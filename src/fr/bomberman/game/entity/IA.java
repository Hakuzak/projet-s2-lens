package fr.bomberman.game.entity;

import fr.bomberman.game.entity.tile.TileType;
import javafx.scene.image.Image;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class IA extends Player {

    Random random;
    private int nbPlacedBombs;

    /**
     * Créer une intelligence artificielle qui est contrôlée par l'ordinateur
     *
     * @param image Le sprite
     * @param x     La position en x
     * @param y     La position en y
     */
    public IA(Image image, int x, int y) {
        super(image, x, y, "Ordinateur");
        random = new Random();
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
    private void move() {
        int dir = (int) (Math.random() * 4);
        int moveCount = (int) (Math.random() * 3);
        int m = 0;

        if (dir == 0) {
            while (m <= moveCount) {
                moveDir("up");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                m++;
            }

        } else if (dir == 1) {
            while (m <= moveCount) {
                moveDir("down");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                m++;
            }

        } else if (dir == 2) {
            while (m <= moveCount) {
                moveDir("left");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                m++;
            }

        } else {
            while (m <= moveCount) {
                moveDir("right");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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

            if ((!collideY(650) && getSprite().getX() % 100 == 0) || collideY(650) || getBoard().getByCoords(getSprite().getX(), getSprite().getY() + 50).getType() == TileType.DESTRUCTIBLE) {
            } else moveDown();

        } else if (dir.equals("left")) {
            this.getSprite().setImage(getSpriteManager().get("ia_left"));

            if ((!collideX(0) && getSprite().getY() % 100 == 0) || collideX(50) || getBoard().getByCoords(getSprite().getX() - 50, getSprite().getY()).getType() == TileType.DESTRUCTIBLE) {
            } else moveLeft();

        } else if (dir.equals("right")) {
            this.getSprite().setImage(getSpriteManager().get("ia_right"));

            if ((!collideX(650) && getSprite().getY() % 100 == 0) || collideX(650) || getBoard().getByCoords(getSprite().getX() + 50, getSprite().getY()).getType() == TileType.DESTRUCTIBLE) {
            } else moveRight();
        }
    }


    /**
     * Retourne le nombre de vies de l'IA
     * @return int
     */
    @Override
    public int getLifes() {
        return super.getLifes();
    }


    /**
     * Enlève une vie à l'IA
     */
    @Override
    public void dead() {
        super.dead();
    }


    /**
     * Se déplace et pose une bombe toutes les X secondes
     */
    public void play() {
        int timeMove = (int) ((Math.random() * 5) + 1) * 1000;
        while (timeMove <= 0) {
            timeMove = (int) ((Math.random() * 5) + 1) * 1000;
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                move();

                if (nbPlacedBombs == 3) nbPlacedBombs = 0;
                placeBomb();
            }
        }, 0, timeMove);
    }

}
