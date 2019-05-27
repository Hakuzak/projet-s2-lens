package fr.bomberman.game;


import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Acceuil extends Scene {
    private Group group;
    private Stage stage;
    private Scene scene;


    public Acceuil(double v, double v1, Paint paint, Group group, Stage stage, Scene scene) {
        super(group, v, v1, paint);
        this.stage = stage;
        VBox box = new VBox();
        Button jouer = new Button("Jouer");
        Button option = new Button("Option");
        Button aide = new Button("Aide");
        Button quitter = new Button("Quitter");
        box.getChildren().addAll(jouer, quitter, option, aide);
        group.getChildren().add(box);

        jouer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setScene(scene);
            }
        });
    }

}