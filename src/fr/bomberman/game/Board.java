package fr.bomberman.game;

import fr.bomberman.game.entity.tile.Tile;
import fr.bomberman.game.entity.tile.TileType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Vector;

public class Board extends Pane {

    public static final int NB_TILES = 15;
    private Vector<Tile> tiles;
    private static SpriteManager spriteManager;

    public Board() {
        tiles = new Vector<>();
        generate();
    }

    public static void setSpriteManager(SpriteManager sm) {
        spriteManager = sm;
    }

    public void add(Tile t) {
        tiles.add(t);
    }

    public void draw(GraphicsContext gc) {
        for(Tile t : tiles) {
            gc.drawImage(t.getSprite().getImage(), t.getX(), t.getY(), t.getWidth(), t.getHeight());
        }
    }

    public void generate() {
        Image br = spriteManager.get("block_rock");
        Image br2 = spriteManager.get("block_rock2");
        Image grass = spriteManager.get("grass");

        // First and last column
        for (int i = 0; i < Board.NB_TILES; i++) {
            add(new Tile(br2, 0, i * 50, TileType.EXTERN_WALL));
            add(new Tile(br2, 700, i * 50, TileType.EXTERN_WALL));
        }

        // First and last line
        for (int i = 0; i < Board.NB_TILES; i++) {
            add(new Tile(br, i * 50, 0, TileType.INTERN_WALL));
            add(new Tile(br, i * 50, 700, TileType.INTERN_WALL));
        }

        // Dirt column
        for (int i = 0; i < Board.NB_TILES; i++) {
            if (i != 0 && i != Board.NB_TILES - 1) {
                add(new Tile(grass, 50, i * 50, TileType.GRASS));
                add(new Tile(grass, 150, i * 50, TileType.GRASS));
                add(new Tile(grass, 250, i * 50, TileType.GRASS));
                add(new Tile(grass, 350, i * 50, TileType.GRASS));
                add(new Tile(grass, 450, i * 50, TileType.GRASS));
                add(new Tile(grass, 550, i * 50, TileType.GRASS));
                add(new Tile(grass, 650, i * 50, TileType.GRASS));
            }
        }

        // Dirt line
        for (int i = 0; i < NB_TILES; i++) {
            if (i != 0 && i != NB_TILES - 1) {
                add(new Tile(grass, i * 50, 50, TileType.GRASS));
                add(new Tile(grass, i * 50, 150, TileType.GRASS));
                add(new Tile(grass, i * 50, 250, TileType.GRASS));
                add(new Tile(grass, i * 50, 350, TileType.GRASS));
                add(new Tile(grass, i * 50, 450, TileType.GRASS));
                add(new Tile(grass, i * 50, 550, TileType.GRASS));
                add(new Tile(grass, i * 50, 650, TileType.GRASS));
            }
        }

        // Wall block
        for (int i = 0; i < NB_TILES; i++) {
            if (i != 0 && i != NB_TILES - 1 && i % 2 == 0) {
                add(new Tile(br, 100, i * 50, TileType.INTERN_WALL));
                add(new Tile(br, 200, i * 50, TileType.INTERN_WALL));
                add(new Tile(br, 300, i * 50, TileType.INTERN_WALL));
                add(new Tile(br, 400, i * 50, TileType.INTERN_WALL));
                add(new Tile(br, 500, i * 50, TileType.INTERN_WALL));
                add(new Tile(br, 600, i * 50, TileType.INTERN_WALL));
            }
        }

    }

    public Vector<Tile> getTiles() {
        return this.tiles;
    }

}
