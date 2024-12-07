package games.AICompanion.src;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {
    private Grid grid;
    private int cellSize = 25;
    private static final int PADDING = 20; // Consistent padding

    public GridPanel(Grid grid) {
        this.grid = grid;
        setOpaque(false); // Allow for transparent background
        updatePreferredSize();
    }

    private void updatePreferredSize() {
        int panelWidth = (grid.getCols() * cellSize) + (2 * PADDING);
        int panelHeight = (grid.getRows() * cellSize) + (2 * PADDING);
        setPreferredSize(new Dimension(panelWidth, panelHeight));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Calculate the available space
        int availableWidth = getWidth() - (2 * PADDING);
        int availableHeight = getHeight() - (2 * PADDING);

        // Calculate the grid's total size
        int gridWidth = grid.getCols() * cellSize;
        int gridHeight = grid.getRows() * cellSize;

        // Calculate the starting coordinates to center the grid
        int startX = ((availableWidth - gridWidth) / 2) + PADDING;
        int startY = ((availableHeight - gridHeight) / 2) + PADDING;

        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                Cell cell = grid.getCell(row, col);

                // Set cell color
                if (cell.isGoal()) {
                    g2d.setColor(Color.GREEN);
                } else if (cell.isOccupied()) {
                    g2d.setColor(Color.BLUE);
                } else if (cell.isPath()) {
                    g2d.setColor(Color.ORANGE);
                } else if (cell.isObstacle()) {
                    g2d.setColor(Color.BLACK);
                } else {
                    g2d.setColor(Color.WHITE);
                }

                // Calculate cell position
                int x = startX + (col * cellSize);
                int y = startY + (row * cellSize);

                // Draw filled rectangle
                g2d.fillRect(x, y, cellSize, cellSize);

                // Draw grid lines
                g2d.setColor(Color.GRAY);
                g2d.drawRect(x, y, cellSize, cellSize);
            }
        }

        g2d.dispose();
    }

    public int getCellSize() {
        return cellSize;
    }

    public int getPadding() {
        return PADDING;
    }

    public void setCellSize(int newCellSize) {
        this.cellSize = newCellSize;
        updatePreferredSize();
        revalidate();
        repaint();
    }
}