package fr.bomberman.game;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class MenuPause extends Scene {

    private Group group;
    private Stage stage;
    private Scene scene;

    public MenuPause(double v, double v1, Paint paint, Group group, Stage stage, Scene scene){
        super(group, v, v1, paint);
        this.stage = stage;
        VBox boxP = new VBox();
        Button reprendre = new Button("Reprendre");
        Button optionP = new Button("Option");
        Button aideP = new Button("Aide");
        Button quitterP = new Button("Quitter");
        boxP.getChildren().addAll(reprendre,quitterP,optionP,aideP);
        group.getChildren().add(boxP);

        reprendre.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setScene(scene);
            }
        });
    }
}