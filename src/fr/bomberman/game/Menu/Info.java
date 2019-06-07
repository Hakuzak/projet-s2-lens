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
    protected int i = 0;

    public Info(Player player, int x, int y) {
        this.player = player;

        vie = new Label();
        vie.setMinHeight(50);
        vie.setMinWidth(150);
        vie.setCenterShape(true);

        vie.setTranslateX(x);
        vie.setTranslateY(y);

        this.getChildren().addAll(vie);

        modifLife();


    }

    public void modifLife() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(1),
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent actionEvent) {
                        coucou();
                    }
                }
        ));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    public void coucou() {
        nbVie = Integer.toString(player.getLifes());
        vie.setText("Vie : " + nbVie);


    }
}