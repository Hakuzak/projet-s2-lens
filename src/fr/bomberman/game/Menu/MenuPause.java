package fr.bomberman.game.Menu;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MenuPause extends Scene {

    private MediaPlayer music;

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

        reprendre.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                stage.setScene(gameScene);
                stage.setFullScreen(true);
                Timer.timerPlay();

            }
        });

        reglage.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                option();
            }
        });

        aide.setOnMouseClicked(e -> aide());

        quitter.setOnMouseClicked(e -> System.exit(0));

    }

    public void setMusic(MediaPlayer clip) {
        music = clip;
    }

    public void option() {
        // Création de la boite de dialogue
        Dialog volume = new Dialog();
        volume.getDialogPane().setMinHeight(200);
        volume.getDialogPane().setMinWidth(300);
        volume.setTitle("Réglages");
        volume.setHeaderText("Réglage du volume");

        // Création des boutons
        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);

        // Création du slider pour le volume
        int vol = (int) music.getVolume() * 100;
        Slider slider = new Slider(0, 100, vol);
        slider.setShowTickLabels(true);

        // Ajout des différents composants dans la boite de dialogue
        volume.getDialogPane().setContent(slider);
        volume.getDialogPane().getButtonTypes().addAll(ok);

        // Modifier volume
        slider.valueProperty().addListener((observable, oldValue, newValue) -> music.setVolume(((double) newValue) / 100));

        // Affichage de la boite de dialogue
        volume.showAndWait();

    }

    public void aide() {
        // TODO : ajouter texte sur le gameplay
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

