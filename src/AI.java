public class AI {
    private int row, col;
    private Grid grid;

    public AI(Grid grid, int row, int col) {
        this.grid = grid;
        this.row = row;
        this.col = col;
        this.grid.getCell(row, col).setOccupied(true);
    }

    public void moveUp() {
        if (grid.isValidPosition(row - 1, col) && !grid.getCell(row - 1, col).isObstacle()) {
            grid.getCell(row, col).setOccupied(false);
            row--;
            grid.getCell(row, col).setOccupied(true);
        }
    }

    public void moveDown() {
        if (grid.isValidPosition(row + 1, col) && !grid.getCell(row + 1, col).isObstacle()) {
            grid.getCell(row, col).setOccupied(false);
            row++;
            grid.getCell(row, col).setOccupied(true);
        }
    }

    public void moveLeft() {
        if (grid.isValidPosition(row, col - 1) && !grid.getCell(row, col - 1).isObstacle()) {
            grid.getCell(row, col).setOccupied(false);
            col--;
            grid.getCell(row, col).setOccupied(true);
        }
    }

    public void moveRight() {
        if (grid.isValidPosition(row, col + 1) && !grid.getCell(row, col + 1).isObstacle()) {
            grid.getCell(row, col).setOccupied(false);
            col++;
            grid.getCell(row, col).setOccupied(true);
        }
    }

    public boolean goalReached(){
        return grid.getCell(row, col).isOccupied() && grid.getCell(row, col).isGoal();
    }

    // Getters for row and col
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
