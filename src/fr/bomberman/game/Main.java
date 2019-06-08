package fr.bomberman.game;

import fr.bomberman.game.Menu.*;
import fr.bomberman.game.entity.Bomb;
import fr.bomberman.game.entity.Entity;
import fr.bomberman.game.entity.IA;
import fr.bomberman.game.entity.Player;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;

import static javafx.scene.paint.Color.rgb;

public class Main extends Application {

    private static Canvas canvas;
    private static GraphicsContext gc;
    private static Pane root;

    private static SpriteManager spriteManager;

    private static Group groupA;
    private static Group groupB;

    private static Acceuil acceuil;
    private static MenuPause menuPause;
    private static Scene gameScene;

    private static Player player;
    private static IA ia;
    private static Board board;


    /**
     * Crée la base de l'interface utilisateur
     */
    private static void createUI() {
        canvas = new Canvas(1050, 650);
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
    private static void loadSprites() {
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
        spriteManager.load("explosion", "assets/images/explosion/fire.png");

        // Bonus
        spriteManager.load("bombBonus", "assets/images/powerups/bomb.png");
        spriteManager.load("speedBonus", "assets/images/powerups/speed.png");
    }


    /**
     * Permet de jouer de la musique
     * @param p Le chemin de la musique à jouer
     * @return MediaPlayer
     */
    private static MediaPlayer playMusic(String p) {
        String path = new File("assets/musics/" + p).toURI().toString();
        Media media = new Media((path));
        return new MediaPlayer(media);
    }


    /**
     * Crée le jeu en lui même en initialisant tous les objets essentiels
     */
    private static void createGame(Stage stage) {
        Entity.setSpriteManager(spriteManager);
        Entity.setGraphicsContext(gc);
        Entity.setStage(stage);

        player = new Player("Joueur", spriteManager.get("player_default"), 50, 50);
        player.handleEvents(canvas);

        ia = new IA("IA", spriteManager.get("ia_default"), 950, 550);

        Bomb[] bombs = new Bomb[4];
        bombs[0] = new Bomb(spriteManager.get("bomb1"), 50, 50);
        bombs[1] = new Bomb(spriteManager.get("bomb1"), 50, 50);
        bombs[2] = new Bomb(spriteManager.get("bomb1"), 50, 50);

        player.setBomb(bombs);

        Bomb[] iaBombs = new Bomb[3];
        iaBombs[0] = new Bomb(spriteManager.get("bomb1"), 650, 650);
        iaBombs[1] = new Bomb(spriteManager.get("bomb1"), 650, 650);
        iaBombs[2] = new Bomb(spriteManager.get("bomb1"), 650, 650);

        ia.setBombs(iaBombs);

        player.setEnnemy(ia);
        ia.setEnnemy(player);

        for (Bomb b : bombs) {
            root.getChildren().add(b.getSprite());
        }
        for (Bomb b : iaBombs) {
            root.getChildren().add(b.getSprite());
        }

        root.getChildren().add(player.getSprite());
        root.getChildren().add(ia.getSprite());
        ia.play();

        Board.setSpriteManager(spriteManager);
        board = new Board();
        board.draw(gc);
        Entity.setBoard(board);
    }


    /**
     * Crée les différentes scènes
     * @param stage Le stage principal
     */
    private static void createScenes(Stage stage) {
        MediaPlayer music = playMusic("home_music.mp3");
        music.play();

        gameScene = new Scene(root);


        // Scène Acceuil
        acceuil = new Acceuil(600, 600, groupA, stage, gameScene);
        Acceuil.setMusic(music);

        // Scène menu pause
        menuPause = new MenuPause(600, 600, groupB, stage, gameScene);
        menuPause.setMusic(playMusic("game_music.wav"));

        gameScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                stage.setScene(menuPause);
                Timer.timerPause();
            }
        });

        Rectangle rectangle = new Rectangle(250, 650, rgb(19, 20, 38));
        rectangle.setX(1050);
        Info infoPl = new Info(player, 1075, 225);
        Info infoIa = new Info(ia, 1075, 475);
        Timer time = new Timer(1075, 75);
        ButtonPause buttonPause = new ButtonPause(stage, menuPause);
        root.getChildren().addAll(rectangle, time, infoPl, infoIa, buttonPause);

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
        stage.setMaxWidth(1300);
        stage.setMaxHeight(650);


        createUI();
        loadSprites();
        createGame(stage);

        createScenes(stage);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
