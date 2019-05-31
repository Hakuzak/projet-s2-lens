package fr.bomberman.game.entity;

import fr.bomberman.game.entity.tile.TileType;
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
    private Bomb[] bombs;
    private int nbPlacedBombs = 0;

    /**
     * Créer un joueur contrôlable par un humain
     *
     * @param image Le sprite
     * @param x     La position en x
     * @param y     La position en y
     * @param name  Le nom du joueur
     */
    public Player(Image image, int x, int y, String name) {
        super(image, x, y);
        this.getSprite().setX(x);
        this.getSprite().setY(y);
        this.name = name;
        this.lifes = 3;
        this.score = 0;
    }

    /**
     * Gère le clavier et les actions à effectuer lorsqu'une touche est pressée ou relachée
     * @param c Le canvas qui permet de dessiner
     */
    public void handleEvents(Canvas c) {
        c.setOnKeyPressed(this::handlePressed);
        c.setOnKeyReleased(this::handleReleased);
    }

    /**
     * Méthode appelée lorqu'une touche est pressée
     * @param e L'évènement du clavier
     */
    private void handlePressed(KeyEvent e) {

        // Move up
        if(e.getCode() == KeyCode.W) {
            this.getSprite().setImage(getSpriteManager().get("player_up2"));

            if ((!collideY(50) && getSprite().getX() % 100 == 0) || collideY(50) || getBoard().getByCoords(getSprite().getX(), getSprite().getY() - 50).getType() == TileType.DESTRUCTIBLE) {
            } else moveUp();
        }

        // Move down
        if(e.getCode() == KeyCode.S) {
            this.getSprite().setImage(getSpriteManager().get("player_down2"));
            if ((!collideY(650) && getSprite().getX() % 100 == 0) || collideY(650) || getBoard().getByCoords(getSprite().getX(), getSprite().getY() + 50).getType() == TileType.DESTRUCTIBLE) {
            } else moveDown();
        }

        // Move left
        if(e.getCode() == KeyCode.A) {
            this.getSprite().setImage(getSpriteManager().get("player_left2"));
            if ((!collideX(0) && getSprite().getY() % 100 == 0) || collideX(50) || getBoard().getByCoords(getSprite().getX() - 50, getSprite().getY()).getType() == TileType.DESTRUCTIBLE) {
            } else moveLeft();
        }

        // Move right
        if(e.getCode() == KeyCode.D) {
            this.getSprite().setImage(getSpriteManager().get("player_right2"));
            if ((!collideX(650) && getSprite().getY() % 100 == 0) || collideX(650) || getBoard().getByCoords(getSprite().getX() + 50, getSprite().getY()).getType() == TileType.DESTRUCTIBLE) {
            } else moveRight();
        }

        // Place bomb
        if (e.getCode() == KeyCode.SPACE) {
            if (nbPlacedBombs == 3) nbPlacedBombs = 0;

            bombs[nbPlacedBombs].getSprite().setX(this.getSprite().getX());
            bombs[nbPlacedBombs].getSprite().setY(this.getSprite().getY());
            bombs[nbPlacedBombs].getSprite().setOpacity(1);
            explodeBomb(bombs[nbPlacedBombs]);

            nbPlacedBombs++;
        }

    }

    /**
     * Méthode appelée lorqu'une touche est relachée
     * @param e L'évènement du clavier
     */
    private void handleReleased(KeyEvent e) {
        if(e.getCode() == KeyCode.W) {
            this.getSprite().setImage(getSpriteManager().get("player_up1"));
        }
        if(e.getCode() == KeyCode.S) {
            this.getSprite().setImage(getSpriteManager().get("player_down1"));
        }
        if(e.getCode() == KeyCode.A) {
            this.getSprite().setImage(getSpriteManager().get("player_left1"));
        }
        if(e.getCode() == KeyCode.D) {
            this.getSprite().setImage(getSpriteManager().get("player_right1"));
        }
    }

    /**
     * Retourne le nom du joueur
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne le nombre de vies restantes
     * @return int
     */
    public int getLifes() {
        return lifes;
    }

    /**
     * Retourne le score
     * @return float
     */
    public float getScore() {
        return score;
    }

    /**
     * Modifie le score du joueur
     * @param score La valeur du score ajouté
     */
    public void setScore(float score) {
        this.score += score;
    }

    /**
     * Enlève une vie au joueur lorqu'il meurt
     */
    public void dead() {
        this.lifes--;
    }

    /**
     * Retourne true si une collision sur l'axe x est détectée, false sinon
     * @param nb La valeur de x a tester
     * @return boolean
     */
    protected boolean collideX(int nb) {
        return getSprite().getX() == nb;
    }

    /**
     * Retourne true si une collision sur l'axe y est détectée, false sinon
     * @param nb La valeur de y a tester
     * @return boolean
     */
    protected boolean collideY(int nb) {
        return getSprite().getY() == nb;
    }

    /**
     * Déplace le joueur vers le haut
     */
    protected void moveUp() {
        this.getSprite().setY(this.getSprite().getY() - 50);
    }

    /**
     * Déplace le joueur vers le bas
     */
    protected void moveDown() {
        this.getSprite().setY(this.getSprite().getY() + 50);
    }

    /**
     * Déplace le joueur vers la gauche
     */
    protected void moveLeft() {
        this.getSprite().setX(this.getSprite().getX() - 50);
    }

    /**
     * Déplace le joueur vers la droite
     */
    protected void moveRight() {
        this.getSprite().setX(this.getSprite().getX() + 50);
    }

    /**
     * Assigne le tableau de bombes au joueur
     * @param b Les bombes que le joueur possède
     */
    public void setBomb(Bomb[] b) {
        this.bombs = b;
    }

    /**
     * Gère l'explosion d'une bombe
     */
    public void explodeBomb(Bomb b) {
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

                b.getSprite().setOpacity(0);

                b.getSprite().setImage(getSpriteManager().get("bomb1"));
                // TODO : Create explosion animation !

            }
        }, 1000);
    }

}
