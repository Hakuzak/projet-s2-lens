package fr.bomberman.game.entity.tile;

import fr.bomberman.game.entity.Entity;
import javafx.scene.image.Image;

public class Tile extends Entity {

    private TileType type;
    private boolean haveBonus;


    /**
     * Crée une tuile qui correspond à un bloc solide, d'herbe ou d'une caisse dans le jeu
     *
     * @param image L'image de la tuile
     * @param x     La positon en x
     * @param y     La position en y
     * @param type  Le type
     */
    public Tile(Image image, int x, int y, TileType type) {
        super(image, x, y);
        this.type = type;
        this.haveBonus = false;
    }


    /**
     * Retourne le type de la tuile
     * @return TileType
     */
    public TileType getType() {
        return type;
    }


    /**
     * Modifie le type de la tuile
     * @param type TileType
     */
    public void setType(TileType type) {
        this.type = type;
    }

    /**
     * Retourne le bonus s'il y en a un
     *
     * @return Bonus
     */
    public boolean isHaveBonus() {
        return haveBonus;
    }


    /**
     * Assigne un bonus à la tuile
     */
    public void setBonus() {
        haveBonus = true;
    }


    /**
     * Supprime le bonus une fois qu'il a été pris
     */
    public void killBonus() {
        this.haveBonus = false;
    }


    public void dropBonus() {
        getGraphicsContext().drawImage(getSpriteManager().get("bombBonus"), getX(), getY());
    }

}
