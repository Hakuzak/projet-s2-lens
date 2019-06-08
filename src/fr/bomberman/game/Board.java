package fr.bomberman.game;

import fr.bomberman.game.entity.tile.Tile;
import fr.bomberman.game.entity.tile.TileType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Vector;

public class Board extends Pane {

    private static final int NB_COLUMN = 21;
    private static final int NB_LINE = 13;
    private Vector<Tile> tiles;
    private static SpriteManager spriteManager;


    /**
     * Crée un plateau de tuiles et le génère
     */
    public Board() {
        tiles = new Vector<>();
        generate();
    }


    /**
     * Assigne le gestionnaire de sprites au plateau
     *
     * @param sm Le gestionnaire de sprites
     */
    public static void setSpriteManager(SpriteManager sm) {
        spriteManager = sm;
    }


    /**
     * Ajoute une tuile
     * @param t La tuile a ajouter
     */
    public void add(Tile t) {
        tiles.add(t);
    }


    /**
     * Dessine le plateau à partir du context graphique
     * @param gc Le context graphique du canvas
     */
    public void draw(GraphicsContext gc) {
        for(Tile t : tiles) {
            gc.drawImage(t.getSprite().getImage(), t.getX(), t.getY(), t.getWidth(), t.getHeight());
        }
    }


    /**
     * Génère le plateau et le remplit de tuiles
     */
    public void generate() {
        Image br = spriteManager.get("block_rock");
        Image grass = spriteManager.get("grass");
        Image caisse = spriteManager.get("block_destructible");

        // First and last column
        for (int i = 0; i < Board.NB_LINE; i++) {
            add(new Tile(br, 0, i * 50, TileType.WALL));
            add(new Tile(br, 1000, i * 50, TileType.WALL));
        }

        // First and last line
        for (int i = 0; i < Board.NB_COLUMN; i++) {
            add(new Tile(br, i * 50, 0, TileType.WALL));
            add(new Tile(br, i * 50, 600, TileType.WALL));
        }

        // Dirt column
        for (int i = 0; i < Board.NB_COLUMN; i++) {
            if (i != 0 && i != Board.NB_LINE - 1) {
                add(new Tile(grass, 50, i * 50, TileType.GRASS));
                add(new Tile(grass, 150, i * 50, TileType.GRASS));
                add(new Tile(grass, 250, i * 50, TileType.GRASS));
                add(new Tile(grass, 350, i * 50, TileType.GRASS));
                add(new Tile(grass, 450, i * 50, TileType.GRASS));
                add(new Tile(grass, 550, i * 50, TileType.GRASS));
                add(new Tile(grass, 650, i * 50, TileType.GRASS));
                add(new Tile(grass, 750, i * 50, TileType.GRASS));
                add(new Tile(grass, 850, i * 50, TileType.GRASS));
                add(new Tile(grass, 950, i * 50, TileType.GRASS));
            }
        }

        // Dirt line
        for (int i = 0; i < NB_COLUMN; i++) {
            if (i != 0 && i != NB_COLUMN - 1) {
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
        for (int i = 0; i < NB_COLUMN; i++) {
            if (i != 0 && i != NB_COLUMN - 1 && i % 2 == 0) {
                add(new Tile(br, 100, i * 50, TileType.WALL));
                add(new Tile(br, 200, i * 50, TileType.WALL));
                add(new Tile(br, 300, i * 50, TileType.WALL));
                add(new Tile(br, 400, i * 50, TileType.WALL));
                add(new Tile(br, 500, i * 50, TileType.WALL));
                add(new Tile(br, 600, i * 50, TileType.WALL));
                add(new Tile(br, 700, i * 50, TileType.WALL));
                add(new Tile(br, 800, i * 50, TileType.WALL));
                add(new Tile(br, 900, i * 50, TileType.WALL));
            }
        }

        // Desctrutible block
        // Nombre de bloc destructible
        int nb = 10;

        while (nb != 0) {
            int i = (int) (Math.random() * 20);
            int j = (int) (Math.random() * 12);

            // Pour laisser les cases vides autour du joueur1
            if (i == 1) {
                if (j == 1) {
                    j += 2;
                }
                if (j == 2) {
                    j += 1;
                }
            }
            if (i == 2 && j == 1) j += 2;

            // Pour laisser les cases vides autour du joueur2
            if (i == 19) {
                if (j == 10) {
                    j -= 1;
                }
                if (j == 11) {
                    j -= 2;
                }
            }
            if (i == 18 && j == 11) j -= 2;

            int x = i * 50;
            int y = j * 50;
            boolean bool = false;
            for (Tile t : tiles) {
                if ((t.getX() == x && t.getY() == y) && t.getType() == TileType.GRASS) {
                    bool = true;
                    t.setType(TileType.DESTRUCTIBLE);
                }
            }
            if (bool) {
                add(new Tile(caisse, x, y, TileType.DESTRUCTIBLE));
                nb--;
            }
        }
    }


    /**
     * Retourne la tuile correspondante aux coordonnées x et y passés en paramètres, null sinon
     *
     * @param x La position en x
     * @param y La postion en y
     * @return Tile
     */
    public Tile getByCoords(double x, double y) {
        for (Tile t : tiles) {
            if (t.getX() == x && t.getY() == y) return t;
        }
        return null;
    }

}
