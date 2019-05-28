package fr.bomberman.game;

import fr.bomberman.game.Menu.Acceuil;
import fr.bomberman.game.Menu.MenuPause;
import fr.bomberman.game.entity.Bomb;
import fr.bomberman.game.entity.Entity;
import fr.bomberman.game.entity.Player;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    private Canvas canvas;
    private GraphicsContext gc;
    private Pane root;
    private SpriteManager spriteManager;

    public void createUI() {
        canvas = new Canvas(750, 750);
        canvas.setFocusTraversable(true);
        gc = canvas.getGraphicsContext2D();

        root = new Pane(canvas);
        spriteManager = new SpriteManager();
    }

    public void loadSprites() {
        // Player
        spriteManager.load("player_default", "assets/images/player/default.png");
        spriteManager.load("player_down1", "assets/images/player/down1.png");
        spriteManager.load("player_down2", "assets/images/player/down2.png");
        spriteManager.load("player_right1", "assets/images/player/right1.png");
        spriteManager.load("player_right2", "assets/images/player/right2.png");
        spriteManager.load("player_up1", "assets/images/player/up1.png");
        spriteManager.load("player_up2", "assets/images/player/up2.png");


        // Blocks
        spriteManager.load("block_rock", "assets/images/blocks/block_pierre.png");
        spriteManager.load("block_rock2", "assets/images/blocks/block_pierre2.png");
        spriteManager.load("block_destructible", "assets/images/blocks/caisse.png");
        spriteManager.load("grass", "assets/images/blocks/herbe.png");

        // Bombs
        spriteManager.load("bomb1", "assets/images/bombs/bomb1.png");
        spriteManager.load("bomb2", "assets/images/bombs/bomb2.png");
        spriteManager.load("bomb3", "assets/images/bombs/bomb3.png");
    }

    public AudioClip playMusic(String p) {
        String path = new File("assets/musics/" + p).toURI().toString();
        AudioClip clip = new AudioClip(path);
        return clip;
    }

    @Override
    public void start(Stage stage) {
        AudioClip music = playMusic("home_music.mp3");
        music.play();

        stage.setTitle("Bomberman");
        stage.setResizable(false);
        stage.setMaxWidth(1050);
        stage.setMaxHeight(800);

        createUI();
        loadSprites();

        Entity.setSpriteManager(spriteManager);

        Player player = new Player(spriteManager.get("player_default"), 50, 50, "Joueur 1");
        player.handleEvents(canvas);

        Bomb bomb = new Bomb(spriteManager.get("bomb1"), 50, 50);

        player.setBomb(bomb);

        root.getChildren().add(bomb.getSprite());
        root.getChildren().add(player.getSprite());

        Board.setSpriteManager(spriteManager);
        Board board = new Board();
        board.draw(gc);

        Scene gameScene = new Scene(root);

        // Scène Acceuil
        Group groupA = new Group();
        Acceuil acceuil = new Acceuil(600,600, Color.WHITE,groupA,stage,gameScene);
        Acceuil.setMusic(music);

        // Scène menu pause
        Group groupB = new Group();
        MenuPause menuPause = new MenuPause(600, 600, Color.WHITE, groupB, stage, acceuil, gameScene);

        gameScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                stage.setScene(menuPause);
            }
        });
        stage.setScene(acceuil);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
