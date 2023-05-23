import java.util.Scanner;

public class Maze {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] tiles = {" ", "▮", "X"};
        int[][] map = loadMap();

        String playerSprite = "@";
        int playerRow = 3;
        int playerCol = 0;

        boolean done = false;
        while (!done) {
            System.out.print("\033[H\033[2J");
            display(map, tiles, playerSprite, playerRow, playerCol);

            // Get input.
            String input = in.nextLine();
            int rowMove = 0;
            int colMove = 0;
            if (input.equals("w")) {
                rowMove--;
            }
            else if (input.equals("s")) {
                rowMove++;
            }
            else if (input.equals("a")) {
                colMove--;
            }
            else if (input.equals("d")) {
                colMove++;
            }

            // Update player position, if valid move.
            if (canMove(map, playerRow + rowMove, playerCol + colMove)) {
                playerRow += rowMove;
                playerCol += colMove;
            }

            // TODO: Check whether landed at finish!
        }
    }

    /**
     * Loads and returns game map.
     * @return 2D array of map tile codes.
     */
    public static int[][] loadMap() {
        // TODO: Load from a file.
        int[][] map = {
            {0, 0, 0, 2},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 0}
        };

        return map;
    }

    /**
     * Displays map and player.
     * @param map Game map with tile codes.
     * @param tiles Map tiles. Each tile corresponds to a numeric code, equal to its position in this array.
     * @param playerSprite Player sprite.
     * @param playerRow Player row position.
     * @param playerCol Player column position.
     */
    public static void display(int[][] map, String[] tiles, String playerSprite, int playerRow, int playerCol) {

        // TODO: Display borders around map.

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i == playerRow && j == playerCol) { // Player
                    System.out.print(playerSprite);
                }
                else {  // Display correct tile according to tile code in game map.
                    int tileCode = map[i][j];
                    System.out.print(tiles[tileCode]);
                }
            }
            System.out.println();
        }
    }

    /**
     * Generate 2D grid of tiles/sprites to display.
     * @param map Game map with tile codes.
     * @param tiles Map tiles. Each tile corresponds to a numeric code, equal to its position in this array.
     * @param playerSprite Player sprite.
     * @param playerRow Player row position.
     * @param playerCol Player column position.
     * @return 2D grid of tiles/sprites to display.
     */
    public static String[][] render(int[][] map, String[] tiles, String playerSprite, int playerRow, int playerCol) {
        String[][] gameGrid = new String[map.length][];

        for (int i = 0; i < map.length; i++) {
            gameGrid[i] = new String[map[i].length];
            for (int j = 0; j < map[i].length; j++) {
                if (i == playerRow && j == playerCol) { // Player
                    gameGrid[i][j] = playerSprite;
                }
                else {  // Display correct tile according to tile code in game map.
                    int tileCode = map[i][j];
                    gameGrid[i][j] = tiles[tileCode];
                }
            }
        }

        return gameGrid;
    }

    /**
     * Convenience method to check whether player can move to specified location.
     * @param map Game map.
     * @param row Location row index.
     * @param col Location column index.
     * @return True if can move there.
     */
    public static boolean canMove(int[][] map, int row, int col) {
        // Check whether within bounds.
        if (row < 0 || row >= map.length)
            return false;
        if (col < 0 || col >= map[row].length)
            return false;
        
        return map[row][col] == 0;
    }
}