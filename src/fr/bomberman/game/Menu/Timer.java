package fr.bomberman.game.Menu;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


public class Timer extends VBox {
    protected static int min;
    protected static int sec;
    protected static Timeline timeline;
    protected Label timer;

    public Timer(int x, int y) {
        min = 5;
        sec = 0;

        timer = new Label();

        timer.setTranslateX(x);
        timer.setTranslateY(y);

        this.getChildren().add(timer);

        timeline = new Timeline(new KeyFrame(
                Duration.seconds(1),
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (sec == 0) {
                            min--;
                            sec = 60;
                        }
                        sec--;
                        String m = Integer.toString(min);
                        String s = Integer.toString(sec);
                        timer.setText(m + ":" + s);
                    }
                }
        ));
        timeline.setCycleCount(Timeline.INDEFINITE);


    }

    public static void timerPause() {
        timeline.pause();
    }

    public static void timerPlay() {
        timeline.play();
    }


}
