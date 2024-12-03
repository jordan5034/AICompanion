// GameGUI.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameGUI extends JFrame implements KeyListener {
    private Timer gameTimer;
    private Grid grid;
    private AI ai;
    private GridPanel gridPanel;

    private boolean paused = false;

    public GameGUI(int rows, int cols) {
        grid = new Grid(rows, cols);
        gridPanel = new GridPanel(grid); // Initialize grid panel first
        ai = new AI(grid, 0, 0, gridPanel.getCellSize()); // Initialize AI after gridPanel

        grid.printMaze();
        this.setTitle("AI Movement Game");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setFocusable(true);
        this.addKeyListener(this);
        this.add(gridPanel); // Add the panel to the frame

        gameTimer = new Timer(100, e -> updateGame());
        gameTimer.start(); // Start game loop
        this.setVisible(true);
    }

    private void updateGame() {
        if (!paused) {
            gridPanel.repaint(); // Update the panel
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
        new GameGUI(grid.getRows(), grid.getCols());
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
        }

        ai.moveTo(newRow, newCol); // Move AI
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
