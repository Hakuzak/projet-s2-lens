package fr.bomberman.game.Menu;

import fr.bomberman.game.entity.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Info extends VBox {

    protected Player player;
    protected int x;
    protected int y;
    protected String nbVie;

    public Info(Player player, int x, int y) {

        nbVie = Integer.toString(player.getLifes());

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.INDEFINITE,
                e -> coucou()
        ));
        timeline.play();

        // Création du label vie
        Label vie = new Label("Vie : " + nbVie);

        vie.setTranslateX(x);
        vie.setTranslateY(y);

        this.getChildren().addAll(vie);


    }

    public void coucou() {
        nbVie = Integer.toString(player.getLifes());
    }
}