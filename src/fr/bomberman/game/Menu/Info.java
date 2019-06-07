package fr.bomberman.game.Menu;

import fr.bomberman.game.entity.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Info extends VBox {

    protected Player player;
    protected Label vie;


    /**
     * Crée un rectangle qui affiche le nombre de vies restantes du joueur
     *
     * @param player L'entité sur laquelle on souhaite récupérer les informations
     * @param x      La position en x sur le rectangle
     * @param y      La position en y sur le rectangle
     */
    public Info(Player player, int x, int y) {
        this.player = player;

        vie = new Label();
        vie.setMinHeight(50);
        vie.setMinWidth(150);
        vie.setCenterShape(true);

        vie.setTranslateX(x);
        vie.setTranslateY(y);

        vie.setText(Integer.toString(player.getLifes()));

        this.getChildren().addAll(vie);
        modifLife();
    }


    /**
     * Modifie si besoin le nombre de vies restantes toutes les secondes
     */
    public void modifLife() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(1),
                e -> vie.setText("Vie : " + player.getLifes())
        ));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

}
