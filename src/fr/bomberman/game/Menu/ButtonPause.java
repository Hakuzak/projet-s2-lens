package fr.bomberman.game.Menu;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ButtonPause extends Label {

    public ButtonPause(Stage stage, Scene menuPause) {
        this.setText("Pause");
        this.setId("buttonPause");
        this.setTranslateX(1225);
        this.setTranslateY(10);
        this.setOnMouseClicked(e -> {
            stage.setScene(menuPause);
        });

        this.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
    }

}
