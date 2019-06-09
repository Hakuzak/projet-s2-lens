package fr.bomberman.game.entity;

import fr.bomberman.game.Menu.Death;
import fr.bomberman.game.entity.tile.Tile;
import fr.bomberman.game.entity.tile.TileType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.concurrent.ThreadLocalRandom;

public class Bomb extends Entity {

    /**
     * Crée une bombe avec une image et une position x et y
     *
     * @param image L'image de la bombe
     * @param x     La position en x
     * @param y     La position en y
     */
    public Bomb(Image image, int x, int y) {
        super(image, x, y);
        this.getSprite().setOpacity(0);
    }


    /**
     * Affiche l'explosion sur les tuiles qui ne sont pas un mur
     */
    protected void explosion(Player e, Player ennemy) {
        Tile tile = getBoard().getByCoords(getSprite().getX(), getSprite().getY());
        if (tile != null) {
            int xup = tile.getX();
            int yup = tile.getY() - 50;

            int yup2 = tile.getY() - 100;

            int xdown = tile.getX();
            int ydown = tile.getY() + 50;

            int ydown2 = tile.getY() + 100;

            int xleft = tile.getX() - 50;
            int yleft = tile.getY();

            int xleft2 = tile.getX() - 100;

            int xright = tile.getX() + 50;
            int yright = tile.getY();

            int xright2 = tile.getX() + 100;

            if (getBoard().getByCoords(xup, yup) != null && getBoard().getByCoords(xup, yup).getType() != TileType.WALL) {
                if (getBoard().getByCoords(xup, yup2) != null && getBoard().getByCoords(xup, yup2).getType() != TileType.WALL && getBoard().getByCoords(xup, yup).getType() == TileType.GRASS) {
                    draw(xup, yup2, e);
//                    dropBonus(xup, yup2);

                    // Si c'est le joueur qui pose une bmmbe
                    if (e.getClass().getSimpleName().equals("Player") && e.getSprite().getX() == xup && e.getSprite().getY() == yup2) {
                        e.dead();
                    }
                    if (ennemy.getClass().getSimpleName().equals("IA") && ennemy.getSprite().getX() == xup && ennemy.getSprite().getY() == yup2) {
                        ennemy.dead();
                    }

                    // Si c'est l'IA qui pose une bombe – elle est invincible à ses propres bombes
                    if (e.getClass().getSimpleName().equals("IA") && ennemy.getClass().getSimpleName().equals("Player") && ennemy.getSprite().getX() == xup && ennemy.getSprite().getY() == yup2) {
                        ennemy.dead();
                    }
                }

                draw(tile.getX(), tile.getY(), e);
                draw(xup, yup, e);
//                dropBonus(xup, yup);

                // Si c'est le joueur qui pose une bmmbe
                if (e.getClass().getSimpleName().equals("Player") && e.getSprite().getX() == xup && e.getSprite().getY() == yup) {
                    e.dead();
                }
                if (ennemy.getClass().getSimpleName().equals("IA") && ennemy.getSprite().getX() == xup && ennemy.getSprite().getY() == yup) {
                    ennemy.dead();
                }

                // Si c'est l'IA qui pose une bombe – elle est invincible à ses propres bombes
                if (e.getClass().getSimpleName().equals("IA") && ennemy.getClass().getSimpleName().equals("Player") && ennemy.getSprite().getX() == xup && ennemy.getSprite().getY() == yup) {
                    ennemy.dead();
                }
            }


            if (getBoard().getByCoords(xdown, ydown) != null && getBoard().getByCoords(xdown, ydown).getType() != TileType.WALL) {
                if (getBoard().getByCoords(xdown, ydown2) != null && getBoard().getByCoords(xdown, ydown2).getType() != TileType.WALL && getBoard().getByCoords(xdown, ydown).getType() == TileType.GRASS) {
                    draw(xdown, ydown2, e);
//                    dropBonus(xdown, ydown2);

                    // Si c'est le joueur qui pose une bmmbe
                    if (e.getClass().getSimpleName().equals("Player") && e.getSprite().getX() == xdown && e.getSprite().getY() == ydown2) {
                        e.dead();
                    }
                    if (ennemy.getClass().getSimpleName().equals("IA") && ennemy.getSprite().getX() == xdown && ennemy.getSprite().getY() == ydown2) {
                        ennemy.dead();
                    }

                    // Si c'est l'IA qui pose une bombe – elle est invincible à ses propres bombes
                    if (e.getClass().getSimpleName().equals("IA") && ennemy.getClass().getSimpleName().equals("Player") && ennemy.getSprite().getX() == xdown && ennemy.getSprite().getY() == ydown2) {
                        ennemy.dead();
                    }
                }

                draw(tile.getX(), tile.getY(), e);
                draw(xdown, ydown, e);
//                dropBonus(xdown, ydown);

                // Si c'est le joueur qui pose une bmmbe
                if (e.getClass().getSimpleName().equals("Player") && e.getSprite().getX() == xdown && e.getSprite().getY() == ydown) {
                    e.dead();
                }
                if (ennemy.getClass().getSimpleName().equals("IA") && ennemy.getSprite().getX() == xdown && ennemy.getSprite().getY() == ydown) {
                    ennemy.dead();
                }

                // Si c'est l'IA qui pose une bombe – elle est invincible à ses propres bombes
                if (e.getClass().getSimpleName().equals("IA") && ennemy.getClass().getSimpleName().equals("Player") && ennemy.getSprite().getX() == xdown && ennemy.getSprite().getY() == ydown) {
                    ennemy.dead();
                }
            }


            if (getBoard().getByCoords(xleft, yleft) != null && getBoard().getByCoords(xleft, yleft).getType() != TileType.WALL) {
                if (getBoard().getByCoords(xleft2, yleft) != null && getBoard().getByCoords(xleft2, yleft).getType() != TileType.WALL && getBoard().getByCoords(xleft, yleft).getType() == TileType.GRASS) {
                    draw(xleft2, yleft, e);
//                    dropBonus(xleft2, yleft);

                    // Si c'est le joueur qui pose une bmmbe
                    if (e.getClass().getSimpleName().equals("Player") && e.getSprite().getX() == xleft2 && e.getSprite().getY() == yleft) {
                        e.dead();
                    }
                    if (ennemy.getClass().getSimpleName().equals("IA") && ennemy.getSprite().getX() == xleft2 && ennemy.getSprite().getY() == yleft) {
                        ennemy.dead();
                    }

                    // Si c'est l'IA qui pose une bombe – elle est invincible à ses propres bombes
                    if (e.getClass().getSimpleName().equals("IA") && ennemy.getClass().getSimpleName().equals("Player") && ennemy.getSprite().getX() == xleft2 && ennemy.getSprite().getY() == yleft) {
                        ennemy.dead();
                    }
                }

                draw(tile.getX(), tile.getY(), e);
                draw(xleft, yleft, e);
//                dropBonus(xleft, yleft);

                // Si c'est le joueur qui pose une bmmbe
                if (e.getClass().getSimpleName().equals("Player") && e.getSprite().getX() == xleft && e.getSprite().getY() == yleft) {
                    e.dead();
                }
                if (ennemy.getClass().getSimpleName().equals("IA") && ennemy.getSprite().getX() == xleft && ennemy.getSprite().getY() == yleft) {
                    ennemy.dead();
                }

                // Si c'est l'IA qui pose une bombe – elle est invincible à ses propres bombes
                if (e.getClass().getSimpleName().equals("IA") && ennemy.getClass().getSimpleName().equals("Player") && ennemy.getSprite().getX() == xleft && ennemy.getSprite().getY() == yleft) {
                    ennemy.dead();
                }
            }


            if (getBoard().getByCoords(xright, yright) != null && getBoard().getByCoords(xright, yright).getType() != TileType.WALL) {
                if (getBoard().getByCoords(xright2, yright) != null && getBoard().getByCoords(xright2, yright).getType() != TileType.WALL && getBoard().getByCoords(xright, yright).getType() == TileType.GRASS) {
                    draw(xright2, yright, e);
//                    dropBonus(xright2, yright);

                    // Si c'est le joueur qui pose une bmmbe
                    if (e.getClass().getSimpleName().equals("Player") && e.getSprite().getX() == xright2 && e.getSprite().getY() == yright) {
                        e.dead();
                    }
                    if (ennemy.getClass().getSimpleName().equals("IA") && ennemy.getSprite().getX() == xright2 && ennemy.getSprite().getY() == yright) {
                        ennemy.dead();
                    }

                    // Si c'est l'IA qui pose une bombe – elle est invincible à ses propres bombes
                    if (e.getClass().getSimpleName().equals("IA") && ennemy.getClass().getSimpleName().equals("Player") && ennemy.getSprite().getX() == xright2 && ennemy.getSprite().getY() == yright) {
                        ennemy.dead();
                    }
                }

                draw(tile.getX(), tile.getY(), e);
                draw(xright, yright, e);
//                dropBonus(xright, yright);

                // Si c'est le joueur qui pose une bmmbe
                if (e.getClass().getSimpleName().equals("Player") && e.getSprite().getX() == xright && e.getSprite().getY() == yright) {
                    e.dead();
                }
                if (ennemy.getClass().getSimpleName().equals("IA") && ennemy.getSprite().getX() == xright && ennemy.getSprite().getY() == yright) {
                    ennemy.dead();
                }

                // Si c'est l'IA qui pose une bombe – elle est invincible à ses propres bombes
                if (e.getClass().getSimpleName().equals("IA") && ennemy.getClass().getSimpleName().equals("Player") && ennemy.getSprite().getX() == xright && ennemy.getSprite().getY() == yright) {
                    ennemy.dead();
                }
            }

            if (e.getClass().getSimpleName().equals("Player") && e.getSprite().getX() == tile.getX() && e.getSprite().getY() == tile.getY())
                e.dead();


            if (e.getLifes() <= 0)
                stage.setScene(new Death(new Group(), 600, 600, stage, e.getClass().getSimpleName()));
            else if (ennemy.getLifes() <= 0)
                stage.setScene(new Death(new Group(), 600, 600, stage, ennemy.getClass().getSimpleName()));

        }
    }


    /**
     * Affiche les flammes sur le plateau
     * @param x La position en x
     * @param y La position en y
     */
    private void drawExplosion(int x, int y) {
        getGraphicsContext().drawImage(getSpriteManager().get("explosion"), x, y);
    }


    /**
     * Affiche l'herbe avec l'explosion
     * @param x La position en x
     * @param y La position en y
     */
    private void drawGrass(int x, int y) {
        getGraphicsContext().drawImage(getSpriteManager().get("grass"), x, y);
    }


    /**
     * Dessine l'explosion et remplace les flammes par de l'herbe avec 1 seconde
     * @param x La position en x
     * @param y La position en y
     */
    private void draw(int x, int y, Player e) {
        Tile newTile = getBoard().getByCoords(x, y);
        newTile.getSprite().setImage(getSpriteManager().get("grass"));
        newTile.setType(TileType.GRASS);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, event -> drawExplosion(x, y)),
                new KeyFrame(Duration.seconds(1), event -> drawGrass(x, y)),
                new KeyFrame(Duration.seconds(1), event -> dropBonus(x, y, e))
        );
        timeline.play();
    }

    private void dropBonus(int x, int y, Player e) {
        int haveBonus = ThreadLocalRandom.current().nextInt(1, 11);
        if (haveBonus == 1) {
            getGraphicsContext().drawImage(getSpriteManager().get("bombBonus"), x, y);
            getBoard().getByCoords(x, y).setBonus();
        }

    }

}
