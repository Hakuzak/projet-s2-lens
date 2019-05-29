package fr.bomberman.game.Menu;

import javafx.animation.Timeline;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class Acceuil extends Scene {

    private static MediaPlayer music;

    /**
     * Créer une scene d'acceuil
     *
     * @param v     Largeur
     * @param v1    Hauteur
     * @param group Groupe
     * @param stage Stage
     * @param scene Scene du jeu
     */

    public Acceuil(double v, double v1, Group group, Stage stage, Scene scene) {
        super(group, v, v1);

        // Création de la box
        VBox box = new VBox();
        box.setId("box");
        box.setSpacing(60);
        box.setPadding(new Insets(110, 200, 110, 200));


        // Ajout des boutons
        Button jouer = new Button("JOUER");
        Button reglage = new Button("REGLAGES");
        Button aide = new Button("AIDE");
        Button quitter = new Button("QUITTER");
        box.getChildren().addAll(jouer, reglage, aide, quitter);

        // Ajout du style css
        this.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        group.getChildren().add(box);


        // Lorsque l'on clique sur le bouton jouer
        jouer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                String path = new File("assets/musics/game_music.wav").toURI().toString();
                Media media = new Media(path);
                music = new MediaPlayer(media);
                music.play();
                music.setCycleCount(Timeline.INDEFINITE);

                stage.setScene(scene);
//                stage.setFullScreen(true);
            }
        });

        // Lorsque l'on clique sur le bouton reglage
        reglage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                option();
            }
        });

        aide.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                aide();
            }
        });

        // Lorsque l'on clique sur le bouton quitter
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

    /**
     * Fonction qui va créer une boite de dialogue permettant de gérer les reglages
     */
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
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                music.setVolume(((double) newValue) / 100);
            }
        });

        // Affichage de la boite de dialogue
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