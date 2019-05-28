package fr.bomberman.game.Menu;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class MenuPause extends Scene {


    public MenuPause(double v, double v1, Paint paint, Group group, Stage stage, Scene a, Scene j) {
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



        reprendre.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setScene(j);
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

    public void option() {
        Alert volume = new Alert(Alert.AlertType.NONE);
        Slider slider = new Slider();
        volume.getDialogPane().getChildren().add(slider);
    }
}