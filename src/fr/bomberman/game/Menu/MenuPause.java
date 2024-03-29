package fr.bomberman.game.Menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuPause extends Scene {

    private MediaPlayer music;


    /**
     * Créer la scène correspondant au menu de pause
     *
     * @param v         La largeur
     * @param v1        La hauteur
     * @param group     La boîte qui contient les éléments
     * @param stage     Le stage de la fenêtre
     * @param gameScene La scène de jeu qui sera affiché quand on clique sur le bouton jouer
     */
    public MenuPause(double v, double v1, Group group, Stage stage, Scene gameScene) {
        super(group, v, v1);
        VBox box = new VBox();
        box.setId("box");
        box.setSpacing(60);
        box.setPadding(new Insets(20, 200, 70, 200));

        Label pause = new Label("PAUSE");
        pause.setAlignment(Pos.CENTER);

        Button reprendre = new Button("REPRENDRE");
        Button reglage = new Button("REGLAGE");
        Button aide = new Button("AIDE");
        Button quitter = new Button("QUITTER");
        box.getChildren().addAll(pause, reprendre, reglage, aide, quitter);

        this.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        group.getChildren().add(box);

        reprendre.setOnMouseClicked(e -> {
            stage.setScene(gameScene);
            stage.setFullScreen(true);
            Timer.timerPlay();

        });

        reglage.setOnMouseClicked(e -> option());
        aide.setOnMouseClicked(e -> aide());
        quitter.setOnMouseClicked(e -> System.exit(0));

    }


    /**
     * Assigne la musique du menu
     * @param clip Le player qui contient la musique
     */
    public void setMusic(MediaPlayer clip) {
        music = clip;
    }


    /**
     * Crée la boîte de dialogue qui contient le slider de la musique
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
     * Crée la boîte de dialogue qui affiche l'aide et les touches du clavier pour joueur
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
                        "Vous disposez de 5 minutes pour détruire les blocs \n " +
                        "sur votre passage et réduire le nombre de vie \n " +
                        "de l’adversaire à 0. \n\n " +

                        "Comment gagner ? \n" +
                        "Vous gagnez si l’adversaire n’a plus de vie ou bien \n" +
                        "si lorsque le temps imparti est écoulé, vous disposez \n" +
                        "de plus de vie que l’adversaire \n\n" +


                        "Comment perdre ? \n " +
                        "Vous perdez si vous n’avez  plus de vie ou bien si \n" +
                        "lorsque le temps imparti est écoulé, vous disposez\n " +
                        "de moins de vie que l’adversaire \n\n" +

                        "Comment jouer ? \n" +
                        "Z ou haut: se diriger vers le haut \n" +
                        "Q ou gauche : se diriger vers la gauche \n" +
                        "S ou bas : se diriger vers le bas \n" +
                        "D ou droite: se diriger vers la droite \n" +
                        "Space : poser une bombe \n" +
                        "Echap : metttre le jeu en pause");

        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);

        aide.getDialogPane().setContent(texte);
        aide.getDialogPane().getButtonTypes().addAll(ok);

        aide.showAndWait();
    }

}
