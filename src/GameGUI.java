import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameGUI extends JFrame implements KeyListener {
    private Grid grid;
    private AI ai;
    private GridPanel gridPanel;

    public GameGUI(int rows, int cols) {
        grid = new Grid(rows, cols);
        ai = new AI(grid, 0, 0); // Start AI at position (0, 0)
        gridPanel = new GridPanel(grid); // Create custom panel for grid rendering

        this.setTitle("AI Movement Game");
        this.setSize(500,500 );
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setFocusable(true); // Make the JFrame focusable to receive key events
        this.addKeyListener(this);

        // Add the grid panel to the JFrame
        this.add(gridPanel);

        this.setVisible(true); // Make the window visible
    }

    public void start() {
        // Start the game, nothing new here since GUI initialization happens in the constructor
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

        // Repaint the panel to reflect the updated state
        gridPanel.repaint();
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

        public GridPanel(Grid grid) {
            this.grid = grid;
            setPreferredSize(new Dimension(500, 500)); // Set preferred size for the grid
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int cellSize = 50; // Size of each grid cell
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
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);

                    // Draw grid lines
                    g.setColor(Color.GRAY);
                    g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        }
    }
}
