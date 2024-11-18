import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameGUI extends JFrame implements KeyListener {
    private JFrame gameFrame;

    private Timer gameTimer;

    private Grid grid;
    private final AI ai; // currently, only one AI object
    private GridPanel gridPanel;

    private boolean paused = false;

    public GameGUI(int rows, int cols) {
        grid = new Grid(rows, cols);
        ai = new AI(grid, 0, 0); // Start AI at position (0, 0)
        gridPanel = new GridPanel(grid); // Create custom panel for grid rendering

        grid.setObstaclesAndGoal(); // initializes obstacles and goal from Grid

        this.setTitle("AI Movement Game");
        this.setSize(500,500 ); // Initial window size
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setFocusable(true); // Make the JFrame focusable to receive key events
        this.addKeyListener(this);

        // Add the grid panel to the JFrame
        this.add(gridPanel);

        gameTimer = new Timer(100, e -> updateGame());
        gameTimer.start(); // start the game loop

        this.setVisible(true); // Make the window visible
    }

    public void gameWin(){
        if(ai.goalReached()){
            paused = true;

            // create the win screen
            WinScreen winScreen = new WinScreen(); // win screen

            // Set up the restart functionality
            winScreen.addRestartAction(() -> {
                // Restart the game when the button is clicked
                restartGame();
            });

            // Show win screen
            this.setContentPane(winScreen); // Switch to win screen
            this.revalidate(); // Revalidate to update the frame
            this.repaint(); // Repaint the frame to show the new content

        }
    }

    public void restartGame(){

        System.out.println("restartGame method called");

        // Clear the existing game state and reinitialize
        this.dispose(); // close the current frame

        // Create a new instance of the game
        GameGUI newGame = new GameGUI(grid.getRows(), grid.getCols());
        newGame.setVisible(true); // show the new game window
    }

    public void start() {
        // Start the game, nothing new here since GUI initialization happens in the constructor
    }

    public void updateGame(){
        if(!paused){
            // Repaint the panel to reflect the updated state
            gridPanel.repaint();

            // check if AI reached Goal
            gameWin();

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Check for arrow key presses and move the AI accordingly
        switch (keyCode) {
            case KeyEvent.VK_UP:
                ai.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                ai.moveDown();
                break;
            case KeyEvent.VK_LEFT:
                ai.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                ai.moveRight();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used, but required to implement KeyListener
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used, but required to implement KeyListener
    }

    // Custom JPanel to draw the grid
    private static class GridPanel extends JPanel {
        private Grid grid;
        private int cellSize; // dynamic cell size based on window size

        public GridPanel(Grid grid) {
            this.grid = grid;
            setPreferredSize(new Dimension(500, 500)); // Set preferred size for the grid
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // get the current size of the window
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            // dynamically calculate the number of cells and cell size based on window size
            int rows = grid.getRows();
            int cols = grid.getCols();

            // adjust cell size based on window size
            cellSize = Math.min(panelWidth / cols, panelHeight / rows); // adjusts to the smallest value

            // center the grid in the window
            int xOffset = (panelWidth - (cols * cellSize)) / 2;
            int yOffset = (panelHeight - (rows * cellSize)) / 2;

            for (int i = 0; i < grid.getRows(); i++) {
                for (int j = 0; j < grid.getCols(); j++) {
                    Cell cell = grid.getCell(i, j);

                    // Draw the cell
                    if (cell.isObstacle()) {
                        g.setColor(Color.BLACK); // Obstacle color
                    } else if (cell.isGoal()) {
                        g.setColor(Color.GREEN); // Goal color
                    } else if (cell.isOccupied()) {
                        g.setColor(Color.BLUE); // AI color
                    } else {
                        g.setColor(Color.WHITE); // Empty space color
                    }

                    g.fillRect(xOffset + j * cellSize, yOffset + i * cellSize, cellSize, cellSize);

                    // Draw grid lines
                    g.setColor(Color.LIGHT_GRAY);
                    g.drawRect(xOffset + j * cellSize, yOffset + i * cellSize, cellSize, cellSize);
                }
            }

            // display message if goal reached

        }
    }
}
