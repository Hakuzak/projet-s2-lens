package fr.bomberman.game.Menu;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Timer extends VBox {

    protected static int min;
    protected static int sec;
    protected static Timeline timeline;
    protected Label timer;


    /**
     * Créer un compteur de temps qui arrête le jeu lorsque le temps imparti est écoulé
     *
     * @param x La position en x pour l'affichage
     * @param y La position en y pour l'affichage
     */
    public Timer(int x, int y) {
        min = 5;
        sec = 0;

        timer = new Label();
        timer.setId("timer");


        timer.setTranslateX(x);
        timer.setTranslateY(y);

        timer.setText(min + ":" + sec);

        this.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());


        this.getChildren().add(timer);

        timeline = new Timeline(new KeyFrame(
                Duration.seconds(1),
                event -> {
                    if (sec == 0) {
                        min--;
                        sec = 60;
                    }

                    sec--;
                    timer.setText(min + ":" + sec);
                }
        ));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }


    /**
     * Met le timer en pause
     */
    public static void timerPause() {
        timeline.pause();
    }


    /**
     * Redémarre le timer
     */
    public static void timerPlay() {
        timeline.play();
    }

}
