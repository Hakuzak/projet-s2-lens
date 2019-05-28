package fr.bomberman.game.Menu;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class MenuPause extends Scene {

    private static MediaPlayer music;

    public MenuPause(double v, double v1, Paint paint, Group group, Stage stage, Scene gameScene) {
        super(group, v, v1, paint);
        VBox box = new VBox();
        box.setId("box");
        box.setSpacing(60);
        Label pause = new Label("PAUSE");
        pause.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(20, 200, 70, 200));
        Button reprendre = new Button("Reprendre");
        Button option = new Button("Option");
        Button aide = new Button("Aide");
        Button quitter = new Button("Quitter");
        box.getChildren().addAll(pause, reprendre, quitter, option, aide);
        this.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        group.getChildren().add(box);


        reprendre.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setScene(gameScene);
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

            }
        });

        volume.showAndWait();

    }
    }

