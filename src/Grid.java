public class Grid {

    private int rows, cols;
    private Cell[][] cells;

    public Grid(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.cells = new Cell[rows][cols];
        initializeGrid();
    }

    // method to set obstacles and goal positions
    public void setObstaclesAndGoal(){
        // set which cell is obstacle in grid
        setObstacle(2, 2, true);

        // set which cell is goal in grid
        setGoal(4, 4, true);

    }

    public int getRows(){
        return rows;
    }

    public int getCols(){
        return cols;
    }

    private void initializeGrid(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                cells[i][j] = new Cell();
            }
        }

        setObstaclesAndGoal();
    }

    // Getter for a specific cell
    public Cell getCell(int row, int col) {
        if (isValidPosition(row, col)) {
            return cells[row][col];
        } else {
            throw new IllegalArgumentException("Invalid cell position");
        }
    }

    // method to set a cell as the goal
    public void setGoal(int row, int col, boolean isGoal){
        if(isValidPosition(row, col)) {
            cells[row][col].setGoal(isGoal);
        } else {
            throw new IllegalArgumentException("Invalid cell position");
        }
    }

    // Convenience method to set a cell as an obstacle
    public void setObstacle(int row, int col, boolean isObstacle) {
        if (isValidPosition(row, col)) {
            cells[row][col].setObstacle(isObstacle);
        } else {
            throw new IllegalArgumentException("Invalid cell position");
        }
    }

    // Check if a given row and column are within bounds
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

}
