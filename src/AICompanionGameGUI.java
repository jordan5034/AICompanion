package games.AICompanion.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class AICompanionGameGUI extends JFrame implements KeyListener {
    private Timer gameTimer;
    private Grid grid;
    private AI ai;
    private GridPanel gridPanel;

    private boolean paused = false;

    public AICompanionGameGUI(int rows, int cols) {
        grid = new Grid(rows, cols);
        gridPanel = new GridPanel(grid);
        ai = new AI(grid, gridPanel, 0, 0, gridPanel.getCellSize());

        setupFrame();
        setupGameLoop();
    }

    private void setupFrame() {
        this.setTitle("AI Movement Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFocusable(true);
        this.addKeyListener(this);

        // Use BorderLayout with some padding
        this.setLayout(new BorderLayout(10, 10));

        // Add the grid panel to the center with some padding
        JPanel containerPanel = new JPanel(new GridBagLayout());
        containerPanel.setBackground(Color.LIGHT_GRAY);
        containerPanel.add(gridPanel);
        this.add(containerPanel, BorderLayout.CENTER);

        // Resize listener for dynamic scaling
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustGridScaling();
            }
        });

        // Initial window setup
        this.pack(); // Use pack to respect preferred sizes
        this.setLocationRelativeTo(null); // Center the window
        this.setVisible(true);
    }

    private void setupGameLoop() {
        // Start the game timer
        gameTimer = new Timer(100, e -> updateGame());
        gameTimer.start();
    }

    private void adjustGridScaling() {
        // Get the new dimensions of the content pane
        int availableWidth = this.getContentPane().getWidth();
        int availableHeight = this.getContentPane().getHeight();

        // Calculate new cell size while maintaining the aspect ratio
        int cellSize = Math.min(
                (availableWidth - (2 * gridPanel.getPadding())) / grid.getCols(),
                (availableHeight - (2 * gridPanel.getPadding())) / grid.getRows()
        );

        // Ensure minimum cell size
        cellSize = Math.max(cellSize, 10);

        gridPanel.setCellSize(cellSize);
    }

    private void updateGame() {
        if (!paused) {
            gridPanel.repaint();
            if (ai.goalReached()) gameWin();
        }
    }

    private void gameWin() {
        paused = true;
        WinScreen winScreen = new WinScreen();
        winScreen.addRestartAction(this::restartGame);
        this.setContentPane(winScreen);
        this.revalidate();
        this.repaint();
    }

    private void restartGame() {
        this.dispose();
        new AICompanionGameGUI(grid.getRows(), grid.getCols());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int newRow = ai.getRow();
        int newCol = ai.getCol();

        switch (keyCode) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> newRow--;
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> newRow++;
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> newCol--;
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> newCol++;

            case KeyEvent.VK_1 -> {
                grid.displayCorrectPath();
                if (grid.isPathUpdated()) {
                    gridPanel.repaint();
                    grid.resetPathUpdated();
                }
            }
        }

        ai.moveTo(newRow, newCol);
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}