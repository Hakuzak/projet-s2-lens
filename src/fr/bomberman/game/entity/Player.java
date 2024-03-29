package fr.bomberman.game.entity;

import fr.bomberman.game.entity.tile.TileType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class Player extends Entity {

    private String nom;
    private int lifes;
    protected Bomb[] bombs;
    private int nbPlacedBombs;
    protected Player ennemy;
    protected int nbBombsCanPlace = 3;

    private int playerSpeed = 50;


    /**
     * Créer un joueur contrôlable par un humain
     *
     * @param image Le sprite
     * @param x     La position en x
     * @param y     La position en y
     */
    public Player(String nom, Image image, int x, int y) {
        super(image, x, y);
        this.nom = nom;
        this.getSprite().setX(x);
        this.getSprite().setY(y);
        this.lifes = 3;
        this.nbPlacedBombs = 1;
    }


    /**
     * Gère le clavier et les actions à effectuer lorsqu'une touche est pressée ou relachée
     * @param c Le canvas qui permet de dessiner
     */
    public void handleEvents(Canvas c) {
        c.setOnKeyPressed(this::handlePressed);
    }


    /**
     * Méthode appelée lorqu'une touche est pressée
     * @param e L'évènement du clavier
     */
    private void handlePressed(KeyEvent e) {
        // Move up
        if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP) {
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, event -> getSprite().setImage(getSpriteManager().get("player_up1"))),
                    new KeyFrame(Duration.seconds(0.1), event -> getSprite().setImage(getSpriteManager().get("player_up2")))

            );
            timeline.play();

            if ((!collideY(50) && getSprite().getX() % 100 == 0) || collideY(50) || getBoard().getByCoords(getSprite().getX(), getSprite().getY() - 50).getType() == TileType.DESTRUCTIBLE) {
            } else moveUp();

            getBonus(getSprite().getX(), getSprite().getY());

        }

        // Move down
        if (e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN) {
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, event -> getSprite().setImage(getSpriteManager().get("player_down1"))),
                    new KeyFrame(Duration.seconds(0.1), event -> getSprite().setImage(getSpriteManager().get("player_down2")))

            );
            timeline.play();

            if ((!collideY(550) && getSprite().getX() % 100 == 0) || collideY(550) || getBoard().getByCoords(getSprite().getX(), getSprite().getY() + 50).getType() == TileType.DESTRUCTIBLE) {
            } else moveDown();

            getBonus(getSprite().getX(), getSprite().getY());
        }

        // Move left
        if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) {
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, event -> getSprite().setImage(getSpriteManager().get("player_left1"))),
                    new KeyFrame(Duration.seconds(0.1), event -> getSprite().setImage(getSpriteManager().get("player_left2")))

            );
            timeline.play();

            if ((!collideX(0) && getSprite().getY() % 100 == 0) || collideX(50) || getBoard().getByCoords(getSprite().getX() - 50, getSprite().getY()).getType() == TileType.DESTRUCTIBLE) {
            } else moveLeft();

            getBonus(getSprite().getX(), getSprite().getY());
        }

        // Move right
        if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT) {
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, event -> getSprite().setImage(getSpriteManager().get("player_right1"))),
                    new KeyFrame(Duration.seconds(0.1), event -> getSprite().setImage(getSpriteManager().get("player_right2")))

            );
            timeline.play();

            if ((!collideX(950) && getSprite().getY() % 100 == 0) || collideX(950) || getBoard().getByCoords(getSprite().getX() + 50, getSprite().getY()).getType() == TileType.DESTRUCTIBLE) {
            } else moveRight();

            getBonus(getSprite().getX(), getSprite().getY());
        }

        // Place bomb
        if (e.getCode() == KeyCode.SPACE) {
            placeBomb();
        }

    }


    /**
     * Retourne true si une collision sur l'axe x est détectée, false sinon
     *
     * @param nb La valeur de x a tester
     * @return boolean
     */
    protected boolean collideX(int nb) {
        return getSprite().getX() == nb;
    }


    /**
     * Retourne true si une collision sur l'axe y est détectée, false sinon
     *
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
        this.getSprite().setY(this.getSprite().getY() - playerSpeed);
    }


    /**
     * Déplace le joueur vers le bas
     */
    protected void moveDown() {
        this.getSprite().setY(this.getSprite().getY() + playerSpeed);
    }


    /**
     * Déplace le joueur vers la gauche
     */
    protected void moveLeft() {
        this.getSprite().setX(this.getSprite().getX() - playerSpeed);
    }


    /**
     * Déplace le joueur vers la droite
     */
    protected void moveRight() {
        this.getSprite().setX(this.getSprite().getX() + playerSpeed);
    }


    /**
     * Assigne l'ennemy que l'entité doit affronter
     *
     * @param p L'ennemi à tuer
     */
    public void setEnnemy(Player p) {
        ennemy = p;
    }


    /**
     * Retourne le nombre de vies restantes
     * @return int
     */
    public int getLifes() {
        return lifes;
    }


    /**
     * Enlève une vie au joueur lorqu'il meurt
     */
    public void dead() {
        this.lifes--;
    }

    /**
     * Retourne le nom du joueur
     * @return String
     */
    public String getNom() {
        return this.nom;
    }


    /**
     * Assigne le tableau de bombes au joueur
     * @param b Les bombes que le joueur possède
     */
    public void setBomb(Bomb[] b) {
        this.bombs = b;
    }


    /**
     * Permet de placer une bombe sur la position du joueur
     */
    protected void placeBomb() {
        if (nbPlacedBombs == nbBombsCanPlace) {
            nbPlacedBombs = 0;
        }

        bombs[nbPlacedBombs].getSprite().setX(this.getSprite().getX());
        bombs[nbPlacedBombs].getSprite().setY(this.getSprite().getY());
        bombs[nbPlacedBombs].getSprite().setOpacity(1);
        explodeBomb(bombs[nbPlacedBombs]);

        nbPlacedBombs++;
    }


    /**
     * Gère l'explosion d'une bombe
     */
    protected void explodeBomb(Bomb b) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, event -> b.getSprite().setImage(getSpriteManager().get("bomb1"))),
                new KeyFrame(Duration.seconds(1), event -> b.getSprite().setImage(getSpriteManager().get("bomb2"))),
                new KeyFrame(Duration.seconds(2), event -> b.getSprite().setImage(getSpriteManager().get("bomb3"))),
                new KeyFrame(Duration.seconds(3), event -> {
                    b.getSprite().setOpacity(0);
                    b.getSprite().setImage(getSpriteManager().get("bomb1"));
                    b.explosion(this, ennemy);
                })
        );
        timeline.play();
    }

    private void getBonus(double x, double y) {
        if (board.getByCoords(x, y).isHaveBonus()) {
            if (nbBombsCanPlace <= 3) nbBombsCanPlace = 4;
            getGraphicsContext().drawImage(getSpriteManager().get("grass"), x, y);
        }
    }

    public void setLifes(int n) {
        lifes = n;
    }

}
