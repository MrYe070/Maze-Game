import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class App {
    private static int[][] map;
    private static String[] tiles = {" ", "▮", "X"};
    private static String playerSprite = "@";
    private static int playerRow;
    private static int playerCol;

    private static JFrame frame;
    private static JTextArea mainGameDisplay;
    private static JTextArea infoPanel;

    public static void main(String[] args) {
        // Initialize game data.
        map = Maze.loadMap();
        playerRow = 3;
        playerCol = 0;

        // Set up GUI.

        // Initialize GUI window.
        frame = new JFrame("Maze Game");
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Initialize area for main game display.
        mainGameDisplay = new JTextArea();
        mainGameDisplay.setEditable(false);
        mainGameDisplay.setFont(new Font("Courier New", mainGameDisplay.getFont().getStyle(), 12));

        // Info area.
        infoPanel = new JTextArea();
        infoPanel.setEditable(false);
        infoPanel.setText("Use w, a, s, d to move.");

        // Add to the window.
        frame.add(mainGameDisplay);
        frame.add(infoPanel);

        // Set up key input handling. (See method below.)
        setUpKeyHandling(frame);

        // Display the window.
        frame.setVisible(true);

        // Display the initial state of the game. (See method below.)
        updateDisplay();
    }

    private static void updateDisplay() {
        String[][] gameGrid = Maze.render(map, tiles, playerSprite, playerRow, playerCol);

        // Clear text.
        mainGameDisplay.setText("");

        // Display each grid element.
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[i].length; j++) {
                mainGameDisplay.append(gameGrid[i][j]);
            }

            if (i != gameGrid.length - 1)   // Only move to next line if not last row.
                mainGameDisplay.append("\n");
        }

        // Auto-resize window to fit content.
        frame.pack();
    }

    private static void setUpKeyHandling(Component component) {
        component.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int rowMove = 0;
                int colMove = 0;

                char keyChar = e.getKeyChar();

                if (keyChar == 'w') {
                    rowMove--;
                }
                else if (keyChar == 's') {
                    rowMove++;
                }
                else if (keyChar == 'a') {
                    colMove--;
                }
                else if (keyChar == 'd') {
                    colMove++;
                }
    
                // Update player position, if valid move.
                if (Maze.canMove(map, playerRow + rowMove, playerCol + colMove)) {
                    playerRow += rowMove;
                    playerCol += colMove;
                }

                // Update game display.
                updateDisplay();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });
    }
}
