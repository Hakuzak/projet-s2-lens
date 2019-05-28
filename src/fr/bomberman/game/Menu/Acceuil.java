package fr.bomberman.game.Menu;


import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.File;

public class Acceuil extends Scene {

    private static MediaPlayer music;

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

                String path = new File("assets/musics/game_music.wav").toURI().toString();
                Media media = new Media(path);
                music = new MediaPlayer(media);
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

    public static void setMusic(MediaPlayer clip) {
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

        int vol = (int) music.getVolume() * 100;
        System.out.println(vol);
        Slider slider = new Slider(0, 100, vol);
        slider.setShowTickLabels(true);


        volume.getDialogPane().setContent(slider);
        volume.getDialogPane().getButtonTypes().addAll(ok, annuler);


        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                music.setVolume(((double) newValue) / 100);
                System.out.println(music.getVolume());

            }
        });

//        if(vol!=slider.getValue()) {
//            music.setVolume(slider.getValue());
//            System.out.println(slider.getValue());
//            System.out.println(music.getVolume());
//        }
        volume.showAndWait();


    }

}