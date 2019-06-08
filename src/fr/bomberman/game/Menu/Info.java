package fr.bomberman.game.Menu;

import fr.bomberman.game.entity.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Info extends VBox {

    protected Player player;
    protected int x;
    protected int y;
    protected String nbVie;
    protected Label vie;

    public Info(Player player, int x, int y) {
        this.player = player;

        vie = new Label();
        vie.setId("info");
        vie.setTranslateX(x);
        vie.setTranslateY(y);

        this.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());


        this.getChildren().addAll(vie);

        modifLife();


    }

    public void modifLife() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(500),
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent actionEvent) {
                        nbVie = Integer.toString(player.getLifes());
                        vie.setText("Vie : " + nbVie);
                    }
                }
        ));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

}