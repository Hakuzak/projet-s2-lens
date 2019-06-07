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

    private Stage stage;

    public Death(Group group, double v, double v1, Stage stage, String deadEntity) {
        super(group, v, v1);


        HBox hbox = new HBox();
        hbox.setPrefSize(600, 300);
        Button yes = new Button("OUI");
        Button no = new Button("NON");
        hbox.getChildren().addAll(yes, no);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(60);

        String winner = deadEntity.equals("Player") ? "Vous avez perdu" : "Vous avez gagné";

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

        yes.setOnMouseClicked(e -> Main.resetGame(stage));
        no.setOnMouseClicked(e -> System.exit(0));
    }


}
