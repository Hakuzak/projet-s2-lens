package fr.bomberman.game.Menu;

import fr.bomberman.game.entity.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Info extends VBox {

    protected Player player;
    protected String nbVie;
    protected Label vie;
    protected Label joueur;

    /**
     * Crée un rectangle qui affiche le nombre de vies restantes du joueur
     *
     * @param player L'entité sur laquelle on souhaite récupérer les informations
     * @param x      La position en x sur le rectangle
     * @param y      La position en y sur le rectangle
     */
    public Info(Player player, int x, int y) {
        this.player = player;

        joueur = new Label();
        joueur.setText(player.getNom());
        joueur.setId("info");
        joueur.setTranslateX(x);
        joueur.setTranslateY(y);

        vie = new Label();
        vie.setId("info");
        vie.setTranslateX(x);
        vie.setTranslateY(y + 25);

        this.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());


        this.getChildren().addAll(joueur, vie);
        modifLife();
    }


    /**
     * Modifie si besoin le nombre de vies restantes toutes les secondes
     */
    public void modifLife() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(500),
                e -> {
                    nbVie = Integer.toString(player.getLifes());
                    vie.setText("Vie : " + nbVie);
                }
        ));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


}