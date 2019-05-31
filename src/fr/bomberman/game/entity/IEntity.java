package fr.bomberman.game.entity;

import fr.bomberman.game.Board;
import fr.bomberman.game.SpriteManager;
import javafx.scene.image.ImageView;

interface IEntity {

    SpriteManager getSpriteManager();

    Board getBoard();

    ImageView getSprite();

    int getX();

    int getY();

    double getWidth();

    double getHeight();

}
