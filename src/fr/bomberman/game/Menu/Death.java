package fr.bomberman.game.Menu;

import fr.bomberman.game.Main;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Death extends Scene {

    /**
     * Créer le menu de mort qui propose de recommencer une partie ou non
     *
     * @param group      Le groupe qui contient tous les élements de la scène
     * @param v          La largeur
     * @param v1         La hauteur
     * @param stage      Le stage principal pour recommencer une partie
     * @param deadEntity Le nom de la classe de l'entité qui est mort
     */
    public Death(Group group, double v, double v1, Stage stage, String deadEntity) {
        super(group, v, v1);

        HBox hbox = new HBox();
        hbox.setPrefSize(600, 300);
        Button yes = new Button("OUI");
        Button no = new Button("NON");
        hbox.getChildren().addAll(yes, no);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(60);

        String winner;
        if (deadEntity.equals("Player")) winner = "Vous avez perdu";
        else if (deadEntity.equals("IA")) winner = "Vous avez gagné";
        else winner = "Il y a égalité";

        Label quit = new Label(winner + " \n Voulez-vous rejouer ?");
        quit.setMinHeight(200);
        quit.setTranslateY(100);
        quit.setAlignment(Pos.CENTER);
        quit.setTextAlignment(TextAlignment.CENTER);
        quit.setStyle("-fx-font-size: 32px;");

        VBox vbox = new VBox();
        vbox.setPrefSize(600, 600);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(50);
        vbox.getChildren().addAll(quit, hbox);
        vbox.setId("box");
        vbox.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());

        group.getChildren().add(vbox);

        yes.setOnMouseClicked(e -> {
            Main.regenerate(stage);
        });
        no.setOnMouseClicked(e -> System.exit(0));
    }

}
