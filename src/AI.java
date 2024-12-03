import javax.swing.*;

public class AI {
    private int row, col;
    private Grid grid;
    private GridPanel gridPanel;

    private int pixelX, pixelY;
    private int targetX, targetY;
    private static final int MOVE_STEPS = 20; // Increase the number of steps for smoother animation
    private int moveStepCount = 0;
    private Timer moveTimer;

    public AI(Grid grid, int row, int col, int cellSize) {
        this.grid = grid;
        this.row = row;
        this.col = col;
        grid.getCell(row, col).setOccupied(true);

        this.pixelX = col * cellSize;
        this.pixelY = row * cellSize;
        this.targetX = pixelX;
        this.targetY = pixelY;
    }

    public void moveTo(int newRow, int newCol) {
        // Validate the move
        if (!grid.isValidPosition(newRow, newCol) || grid.getCell(newRow, newCol).isObstacle()) {
            return; // Invalid move, do nothing
        }

        // Update the grid's cell occupation
        grid.getCell(row, col).setOccupied(false);
        row = newRow;
        col = newCol;
        grid.getCell(row, col).setOccupied(true);

        // Calculate the target pixel positions using the current cell size
        int cellSize = gridPanel.getCellSize(); // Assuming GridPanel provides a method to access current cell size
        targetX = col * cellSize;
        targetY = row * cellSize;

        // Reset animation step count and start animation
        moveStepCount = 0;
        if (moveTimer == null) {
            moveTimer = new Timer(15, e -> animateMove()); // Decrease delay for smoother animation
        }
        moveTimer.start();
    }

    private void animateMove() {
        if (moveStepCount < MOVE_STEPS) {
            pixelX += (targetX - pixelX) / (MOVE_STEPS - moveStepCount);
            pixelY += (targetY - pixelY) / (MOVE_STEPS - moveStepCount);
            moveStepCount++;
            gridPanel.repaint(); // Repaint the grid panel to update the AI's position
        } else {
            pixelX = targetX;
            pixelY = targetY;
            moveTimer.stop();
        }
    }

    public boolean goalReached() {
        return grid.getCell(row, col).isGoal();
    }

    public int getPixelX() {
        return pixelX;
    }

    public int getPixelY() {
        return pixelY;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}