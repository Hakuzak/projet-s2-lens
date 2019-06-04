package fr.bomberman.game;

import fr.bomberman.game.Menu.Acceuil;
import fr.bomberman.game.Menu.MenuPause;
import fr.bomberman.game.entity.Bomb;
import fr.bomberman.game.entity.Entity;
import fr.bomberman.game.entity.IA;
import fr.bomberman.game.entity.Player;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    private Canvas canvas;
    private GraphicsContext gc;
    private Pane root;

    private SpriteManager spriteManager;

    private Group groupA;
    private Group groupB;

    private Acceuil acceuil;
    private MenuPause menuPause;

    private Player player;
    private IA ia;
    private Board board;


    /**
     * Crée la base de l'interface utilisateur
     */
    private void createUI() {
        canvas = new Canvas(750, 750);
        canvas.setFocusTraversable(true);
        gc = canvas.getGraphicsContext2D();

        root = new Pane(canvas);
        spriteManager = new SpriteManager();

        groupA = new Group();
        groupB = new Group();
    }


    /**
     * Charge toutes les images dans le gestionnaire de sprites
     */
    private void loadSprites() {
        // Player
        spriteManager.load("player_default", "assets/images/player/player_default.png");
        spriteManager.load("player_down1", "assets/images/player/player_down1.png");
        spriteManager.load("player_down2", "assets/images/player/player_down2.png");
        spriteManager.load("player_left1", "assets/images/player/player_left1.png");
        spriteManager.load("player_left2", "assets/images/player/player_left2.png");
        spriteManager.load("player_right1", "assets/images/player/player_right1.png");
        spriteManager.load("player_right2", "assets/images/player/player_right2.png");
        spriteManager.load("player_up1", "assets/images/player/player_up1.png");
        spriteManager.load("player_up2", "assets/images/player/player_up2.png");

        // IA
        spriteManager.load("ia_default", "assets/images/ia/ia_up.png");
        spriteManager.load("ia_down", "assets/images/ia/ia_down.png");
        spriteManager.load("ia_left", "assets/images/ia/ia_left.png");
        spriteManager.load("ia_right", "assets/images/ia/ia_right.png");
        spriteManager.load("ia_up", "assets/images/ia/ia_up.png");

        // Blocks
        spriteManager.load("block_rock", "assets/images/blocks/unbreak_block.png");
        spriteManager.load("block_destructible", "assets/images/blocks/break_block.png");
        spriteManager.load("grass", "assets/images/blocks/grass.png");

        // Bombs
        spriteManager.load("bomb1", "assets/images/bombs/bomb1.png");
        spriteManager.load("bomb2", "assets/images/bombs/bomb2.png");
        spriteManager.load("bomb3", "assets/images/bombs/bomb3.png");
        spriteManager.load("explosion", "assets/images/boom/fire.png");
    }


    /**
     * Permet de jouer de la musique
     * @param p Le chemin de la musique à jouer
     * @return MediaPlayer
     */
    private MediaPlayer playMusic(String p) {
        String path = new File("assets/musics/" + p).toURI().toString();
        Media media = new Media((path));
        MediaPlayer clip = new MediaPlayer(media);
        return clip;
    }


    /**
     * Crée le jeu en lui même en initialisant tous les objets essentiels
     */
    private void createGame() {
        Entity.setSpriteManager(spriteManager);
        Entity.setGraphicsContext(gc);

        player = new Player(spriteManager.get("player_default"), 50, 50, "Joueur 1");
        player.handleEvents(canvas);

        ia = new IA(spriteManager.get("ia_default"), 650, 650);

        Bomb[] bombs = new Bomb[3];
        bombs[0] = new Bomb(spriteManager.get("bomb1"), 50, 50);
        bombs[1] = new Bomb(spriteManager.get("bomb1"), 50, 50);
        bombs[2] = new Bomb(spriteManager.get("bomb1"), 50, 50);

        player.setBomb(bombs);

        Bomb[] iaBombs = new Bomb[3];
        iaBombs[0] = new Bomb(spriteManager.get("bomb1"), 650, 650);
        iaBombs[1] = new Bomb(spriteManager.get("bomb1"), 650, 650);
        iaBombs[2] = new Bomb(spriteManager.get("bomb1"), 650, 650);

        ia.setBombs(iaBombs);

        for (Bomb b : bombs) {
            root.getChildren().add(b.getSprite());
        }

        root.getChildren().add(player.getSprite());
        root.getChildren().add(ia.getSprite());
        ia.play();

        // Quitter le jeu si le joueur a perdu toutes ses vies
        if (player.getLifes() <= 0) {
            Alert alert = new Alert(Alert.AlertType.NONE, "", ButtonType.FINISH);
            alert.setHeaderText("Oh.. Vous avez perdu toutes vos vies :(");
            alert.setContentText("Réessayez une prochaine fois peut-être !");

            alert.showAndWait();
        }

        Board.setSpriteManager(spriteManager);
        board = new Board();
        board.draw(gc);
        Entity.setBoard(board);
    }


    /**
     * Crée les différentes scènes
     * @param stage
     */
    private void createMenus(Stage stage) {
        MediaPlayer music = playMusic("home_music.mp3");
        music.play();

        Scene gameScene = new Scene(root);

        // Scène Acceuil
        acceuil = new Acceuil(600, 600, groupA, stage, gameScene);
        Acceuil.setMusic(music);

        // Scène menu pause
        menuPause = new MenuPause(600, 600, groupB, stage, gameScene);
//        menuPause.setMusic(music);

        gameScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                stage.setScene(menuPause);
            }
        });

        stage.setScene(acceuil);
    }


    /**
     * Méthode appelée au démarrage du jeu qui crée la fenêtre et tous ses composants
     * @param stage Le stage principal crée par JavaFX
     */
    @Override
    public void start(Stage stage) {

        stage.setTitle("Bomberman");
        stage.setResizable(false);
        stage.setMaxWidth(1050);
        stage.setMaxHeight(750);

        createUI();
        loadSprites();
        createGame();

        createMenus(stage);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
