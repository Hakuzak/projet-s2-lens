package fr.bomberman.game.Menu;


import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Acceuil extends Scene {


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
        box.getChildren().addAll(jouer, quitter, option, aide);
        this.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        group.getChildren().add(box);

        jouer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
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


    public void option() {
        Dialog volume = new Dialog();
        volume.getDialogPane().setMinHeight(200);
        volume.getDialogPane().setMinWidth(300);
        Slider slider = new Slider();
        volume.setTitle("Option");
        volume.setHeaderText("Réglage du volume");
        volume.getDialogPane().setContent(slider);
        volume.showAndWait();
    }

}