public class Grid {

    private int rows, cols;
    private Cell[][] cells;

    public Grid(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.cells = new Cell[rows][cols];
        initializeGrid();
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
    }

    // Getter for a specific cell
    public Cell getCell(int row, int col) {
        if (isValidPosition(row, col)) {
            return cells[row][col];
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

    // Convenience method to set a cell as a goal
    public void setGoal(int row, int col, boolean isGoal) {
        if (isValidPosition(row, col)) {
            cells[row][col].setGoal(isGoal);
        } else {
            throw new IllegalArgumentException("Invalid cell position");
        }
    }

    // Check if a given row and column are within bounds
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public void printGrid(){

        // Print column headers
        System.out.print("  ");
        for(int j = 0; j < cols; j++){
            System.out.print(j + " ");
        }
        System.out.println();

        for(int i = 0; i < rows; i++){
            System.out.print(i + " "); // Row header
            for(int j = 0; j < cols; j++){

                if(cells[i][j].isOccupied()){
                    System.out.print("A"); // AI position
                }
                else if(cells[i][j].isObstacle()){
                    System.out.print("O"); // Obstacle
                }
                else if(cells[i][j].isGoal()){
                    System.out.print("G"); // Goal
                }
                else{
                    System.out.print(". "); // Empty space
                }
            }

            System.out.println(); // To print line by line of the grid

        }
    }

}
