package fr.bomberman.game.Menu;

import fr.bomberman.game.entity.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Timer extends VBox {

    protected static int minutes;
    protected static int secondes;
    protected static Timeline timeline;
    protected Label timer;
    protected Stage stage;


    /**
     * Créer un compteur de temps qui arrête le jeu lorsque le temps imparti est écoulé
     *
     * @param x La position en x pour l'affichage
     * @param y La position en y pour l'affichage
     */
    public Timer(int x, int y, Stage stage, Player player, Player enemy) {
        minutes = 5;
        secondes = 0;

        timer = new Label();
        timer.setId("timer");


        timer.setTranslateX(x);
        timer.setTranslateY(y);

        timer.setText(minutes + ":" + secondes);

        this.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());


        this.getChildren().add(timer);

        timeline = new Timeline(new KeyFrame(
                Duration.seconds(1),
                event -> {
                    if (secondes == 0) {
                        minutes--;
                        secondes = 60;
                    }

                    secondes--;
                    timer.setText(minutes + ":" + secondes);
                }
        ));
        timeline.setCycleCount(Timeline.INDEFINITE);

        if (minutes == 0 && secondes == 0) {
            if (player.getLifes() > enemy.getLifes()) {
                stage.setScene(new Death(new Group(), 600, 600, stage, player.getClass().getSimpleName()));
            } else {
                stage.setScene(new Death(new Group(), 600, 600, stage, enemy.getClass().getSimpleName()));
            }
        }

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

    /**
     * Retourne le nombre de minutes
     *
     * @return int
     */
    public static int getMinutes() {
        return minutes;
    }

    /**
     * Retourne le nombre de secondes
     *
     * @return int
     */
    public static int getSecondes() {
        return secondes;
    }
}
