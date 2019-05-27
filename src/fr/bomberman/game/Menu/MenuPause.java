package fr.bomberman.game.Menu;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        box.setPadding(new Insets(55, 200, 55, 200));
        Button acceuil = new Button("Acceuil");
        Button reprendre = new Button("Reprendre");
        Button option = new Button("Option");
        Button aide = new Button("Aide");
        Button quitter = new Button("Quitter");
        box.getChildren().addAll(acceuil, reprendre, quitter, option, aide);
        this.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        group.getChildren().add(box);


        acceuil.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setScene(a);
//                stage.setFullScreen(true);
            }
        });

        reprendre.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setScene(j);
//                stage.setFullScreen(true);
            }
        });

        quitter.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Platform.exit();
            }
        });
    }
}