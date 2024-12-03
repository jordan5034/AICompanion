import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;

public class Grid {

    private int rows, cols;
    private Cell[][] cells;
    private final Random random;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new Cell[rows][cols];
        this.random = new Random();

        initializeGrid();
        setObstaclesAndGoal();
    }

    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public void setObstaclesAndGoal() {
        generateMaze();
        cells[rows-1][cols-1].setGoal(true); // Our goal will be in the bottom right corner
    }

    private void generateMaze() {
        int retryLimit = 100; // max attempts to generate a valid maze
        int attempts = 0;
        boolean validMaze = false;

        while (!validMaze && attempts < retryLimit) {
            System.out.println("Attempt: " + attempts);
            attempts++;

            // Step 1: Initialize all cells as obstacles
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    cells[i][j].setObstacle(true);
                    cells[i][j].setGoal(false);
                }
            }

            // Step 2: Generate the maze using a randomized algorithm
            carvePath(0, 0);

            // Step 3: Ensure the goal is reachable
            ensureGoalReachable();

            // Step 4: Validate the maze using BFS
            validMaze = isMazeSolvable();
        }

        if (!validMaze) {
            throw new RuntimeException("Failed to generate a valid maze after " + retryLimit + " attempts.");
        }
    }

    private boolean isMazeSolvable() {
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;

        int[] rowOffsets = {-1, 1, 0, 0};
        int[] colOffsets = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currRow = current[0];
            int currCol = current[1];

            if (currRow == rows - 1 && currCol == cols - 1) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int newRow = currRow + rowOffsets[i];
                int newCol = currCol + colOffsets[i];

                if (isInBounds(newRow, newCol) && !visited[newRow][newCol] && !cells[newRow][newCol].isObstacle()) {
                    visited[newRow][newCol] = true;
                    queue.add(new int[]{newRow, newCol});
                }
            }
        }

        return false;
    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    private void carvePath(int row, int col) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        List<int[]> shuffledDirections = new ArrayList<>(List.of(directions));
        Collections.shuffle(shuffledDirections, random);

        for (int[] direction : shuffledDirections) {
            int newRow = row + direction[0] * 2;
            int newCol = col + direction[1] * 2;

            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && cells[newRow][newCol].isObstacle()) {

                if ((row + direction[0] >= rows - 1 && direction[0] == 1) ||
                        (col + direction[1] >= cols - 1 && direction[1] == 1)) {
                    continue;
                }

                cells[row + direction[0]][col + direction[1]].setObstacle(false);
                cells[newRow][newCol].setObstacle(false);

                carvePath(newRow, newCol);
            }
        }
    }

    private void ensureGoalReachable() {
        int goalRow = rows - 1;
        int goalCol = cols - 1;

        cells[goalRow][goalCol].setObstacle(false);

        if (!hasAdjacentOpenCell(goalRow, goalCol)) {
            if (goalRow - 1 >= 0) cells[goalRow - 1][goalCol].setObstacle(false);
            else if (goalCol - 1 >= 0) cells[goalRow][goalCol - 1].setObstacle(false);
        }
    }

    private boolean hasAdjacentOpenCell(int row, int col) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (isInBounds(newRow, newCol) && !cells[newRow][newCol].isObstacle()) {
                return true;
            }
        }
        return false;
    }

    public Cell getCell(int row, int col) {
        if (isValidPosition(row, col)) {
            return cells[row][col];
        } else {
            throw new IllegalArgumentException("Invalid cell position");
        }
    }

    public int getRows(){
        return rows;
    }

    public int getCols(){
        return cols;
    }

    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    // To print in console
    public void printMaze() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(cells[i][j].isObstacle() ? "X " : ". ");
            }
            System.out.println();
        }
    }
}
