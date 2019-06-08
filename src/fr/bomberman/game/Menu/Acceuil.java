package fr.bomberman.game.Menu;

import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

public class Acceuil extends Scene {

    private static MediaPlayer music;


    /**
     * Créer une scene d'acceuil
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
        jouer.setOnMouseClicked(e -> {
            String path = new File("assets/musics/game_music.wav").toURI().toString();
            Media media = new Media(path);
            music = new MediaPlayer(media);
            music.play();
            music.setCycleCount(Timeline.INDEFINITE);

            stage.setScene(scene);
            stage.setFullScreen(true);
            Timer.timerPlay();
        });

        reglage.setOnMouseClicked(e -> option());
        aide.setOnMouseClicked(e -> aide());
        quitter.setOnMouseClicked(e -> System.exit(0));
    }


    /**
     * Assigne la musique du menu
     *
     * @param clip Le player de la musique
     */
    public static void setMusic(MediaPlayer clip) {
        music = clip;
    }



    /**
     * Méthode qui va créer une boite de dialogue permettant de gérer les reglages
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
        slider.valueProperty().addListener((observable, oldValue, newValue) -> music.setVolume(((double) newValue) / 100));

        // Affichage de la boite de dialogue
        volume.showAndWait();
    }


    /**
     * Méthode qui va créer une boîte de dialogue qui affiche l'aide et les touches pour jouer
     */
    public void aide() {
        Dialog aide = new Dialog();
        aide.initModality(Modality.APPLICATION_MODAL);
        aide.getDialogPane().setMinHeight(200);
        aide.getDialogPane().setMinWidth(300);
        aide.setTitle("Aide");
        aide.setHeaderText("Vous pouvez retrouvez ici toutes \nles informations utiles pour jouer");

        Label texte = new Label();
        texte.setText(

                "But du jeu : \n" +
                        "Vous disposez de 5 minutes pour détruire les blocs \n sur votre passage et réduire le nombre de vie \n de l’adversaire à 0. \n\n " +

                        "Comment gagner ? \n" +
                        "Vous gagnez si l’adversaire n’a plus de vie ou bien \nsi lorsque le temps imparti est écoulé, \nvous disposez de plus de vie que l’adversaire \n\n" +


                        "Comment perdre ? \n " +
                        "Vous perdez si vous n’avez  plus de vie ou bien si \n lorsque le temps imparti est écoulé, \nvous disposez de moins de vie que l’adversaire \n\n" +

                        "Comment jouer ? \n" +
                        "Z : up \n" +
                        "Q : left \n" +
                        "S : down \n" +
                        "D : right \n" +
                        "Space : bomb \n" +
                        "Echap : pause");

        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);

        aide.getDialogPane().setContent(texte);
        aide.getDialogPane().getButtonTypes().addAll(ok);


        aide.showAndWait();
    }

}
