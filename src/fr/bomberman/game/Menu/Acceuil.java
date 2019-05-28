package fr.bomberman.game.Menu;


import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.File;

public class Acceuil extends Scene {

    private static AudioClip music;

    public Acceuil(double v, double v1, Paint paint, Group group, Stage stage, Scene scene) {
        super(group, v, v1, paint);
        VBox box = new VBox();
        box.setId("box");
        box.setSpacing(60);
        box.setPadding(new Insets(110, 200, 110, 200));
        Button jouer = new Button("JOUER");
        Button option = new Button("OPTION");
        Button aide = new Button("AIDE");
        Button quitter = new Button("QUITTER");
        box.getChildren().addAll(jouer, option, aide, quitter);
        this.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        group.getChildren().add(box);

        jouer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (music.isPlaying()) music.stop();

                String path = new File("assets/musics/game_music.wav").toURI().toString();
                music = new AudioClip(path);
                music.play();
                music.setCycleCount(Timeline.INDEFINITE);

                stage.setScene(scene);
                stage.setFullScreen(true);
            }
        });

        option.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                option();
            }
        });

        quitter.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Platform.exit();
            }
        });
    }

    public static void setMusic(AudioClip clip) {
        music = clip;
    }


    public void option() {
        Dialog volume = new Dialog();
        volume.getDialogPane().setMinHeight(200);
        volume.getDialogPane().setMinWidth(300);
        volume.setTitle("Option");
        volume.setHeaderText("Réglage du volume");

        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType annuler = new ButtonType("ANNULER", ButtonBar.ButtonData.CANCEL_CLOSE);

        Slider slider = new Slider(0, 100, 50);


        volume.getDialogPane().setContent(slider);
        volume.getDialogPane().getButtonTypes().addAll(ok, annuler);
        music.setVolume(slider.getValue());
        volume.showAndWait();


    }

}