// GridPanel.java
import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {
    private Grid grid;
    private int cellSize;

    public GridPanel(Grid grid) {
        this.grid = grid;
        setPreferredSize(new Dimension(500, 500));
    }

    public int getCellSize() {
        return cellSize;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int rows = grid.getRows();
        int cols = grid.getCols();

        cellSize = Math.min(panelWidth / cols, panelHeight / rows);
        int xOffset = (panelWidth - (cols * cellSize)) / 2;
        int yOffset = (panelHeight - (rows * cellSize)) / 2;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell cell = grid.getCell(i, j);
                if (cell.isObstacle()) g.setColor(Color.BLACK);
                else if (cell.isGoal()) g.setColor(Color.GREEN);
                else if (cell.isOccupied()) g.setColor(Color.BLUE);
                else g.setColor(Color.WHITE);

                g.fillRect(xOffset + j * cellSize, yOffset + i * cellSize, cellSize, cellSize);
                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(xOffset + j * cellSize, yOffset + i * cellSize, cellSize, cellSize);
            }
        }
    }
}
