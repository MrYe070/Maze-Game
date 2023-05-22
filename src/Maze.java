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
            String input = in.nextLine();
            if (input.equals("w") && map[playerRow - 1][playerCol] == 0) {
                playerRow--;
            }
            else if (input.equals("s") && map[playerRow + 1][playerCol] == 0) {
                playerRow++;
            }
            else if (input.equals("a") && map[playerRow][playerCol - 1] == 0) {
                playerCol--;
            }
            else if (input.equals("d") && map[playerRow][playerCol + 1] == 0) {
                playerCol++;
            }
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
     * @param map Game map.
     * @param tiles Map tiles. Each tile corresponds to a numeric code, equal to its position in this array.
     * @param playerSprite Player sprite.
     * @param playerRow Player row position.
     * @param playerCol Player column position.
     */
    public static void display(int[][] map, String[] tiles, String playerSprite, int playerRow, int playerCol) {
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
}