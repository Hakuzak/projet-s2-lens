package fr.bomberman.game.Menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MenuPause extends Scene {

    private static MediaPlayer music;

    public MenuPause(double v, double v1, Group group, Stage stage, Scene gameScene) {
        super(group, v, v1);
        VBox box = new VBox();
        box.setId("box");
        box.setSpacing(60);
        Label pause = new Label("PAUSE");
        pause.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(20, 200, 70, 200));
        Button reprendre = new Button("REPRENDRE");
        Button reglage = new Button("REGLAGE");
        Button aide = new Button("AIDE");
        Button quitter = new Button("QUITTER");
        box.getChildren().addAll(pause, reprendre, reglage, aide, quitter);
        this.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        group.getChildren().add(box);

        reprendre.setOnMouseClicked(e -> stage.setScene(gameScene));

        reglage.setOnMouseClicked(e -> option());

        aide.setOnMouseClicked(e -> aide());

        quitter.setOnMouseClicked(e -> System.exit(0));

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

        slider.valueProperty().addListener((observable, oldValue, newValue) -> music.setVolume(((double) newValue) / 100));

        volume.showAndWait();

    }

    public void aide() {
        Dialog aide = new Dialog();
        aide.getDialogPane().setMinHeight(200);
        aide.getDialogPane().setMinWidth(300);
        aide.setTitle("Aide");
        aide.setHeaderText("Vous pouvez retrouvez ici toutes \nles informations utiles pour jouer");

        Label texte = new Label();
        texte.setAlignment(Pos.CENTER);
        texte.setText("Z : up \nQ : left \nS : down \nD : right \nSpace : bomb \nEchap : pause");

        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);

        aide.getDialogPane().setContent(texte);
        aide.getDialogPane().getButtonTypes().addAll(ok);

        aide.showAndWait();

    }
}

