package games.AICompanion.src;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;
import java.util.HashMap;

public class Grid {

    private int rows, cols;
    private Cell[][] cells;
    private final Random random;

    private boolean pathUpdated = false;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new Cell[rows][cols];
        this.random = new Random();

        initializeGrid();
        generateMaze();
        ensureGoalReachable();
    }

    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setPosition(i, j);
                cells[i][j].setObstacle(true); // Initially, all cells are obstacles
            }
        }
    }

    private void generateMaze() {
        carvePath(0, 0);
    }

    private void carvePath(int row, int col) {
        cells[row][col].setObstacle(false); // Mark the current cell as part of the path

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        List<int[]> shuffledDirections = new ArrayList<>(List.of(directions));
        Collections.shuffle(shuffledDirections, random);

        for (int[] direction : shuffledDirections) {
            int newRow = row + direction[0] * 2;
            int newCol = col + direction[1] * 2;

            if (isInBounds(newRow, newCol) && cells[newRow][newCol].isObstacle()) {
                cells[row + direction[0]][col + direction[1]].setObstacle(false);
                carvePath(newRow, newCol);
            }
        }
    }

    private void ensureGoalReachable() {
        Cell goal = cells[rows - 1][cols - 1];
        goal.setGoal(true);
        goal.setObstacle(false);

        if (!hasAdjacentOpenCell(goal.getRow(), goal.getCol())) {
            if (goal.getRow() - 1 >= 0) cells[goal.getRow() - 1][goal.getCol()].setObstacle(false);
            else if (goal.getCol() - 1 >= 0) cells[goal.getRow()][goal.getCol() - 1].setObstacle(false);
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

    public boolean isValidPosition(int newRow, int newCol) {
        return isInBounds(newRow, newCol) &&
                !cells[newRow][newCol].isObstacle() &&
                !cells[newRow][newCol].isOccupied();
    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public boolean goalReached(int row, int col) {
        return cells[row][col].isGoal();
    }

    public Cell getCell(int row, int col) {
        if (isInBounds(row, col)) {
            return cells[row][col];
        } else {
            throw new IllegalArgumentException("Invalid cell position");
        }
    }

    public void printMaze() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (cells[i][j].isGoal()) {
                    System.out.print("G ");
                } else if (cells[i][j].isPath()) {
                    System.out.print("* ");
                } else {
                    System.out.print(cells[i][j].isObstacle() ? "X " : ". ");
                }
            }
            System.out.println();
        }
    }

    public void displayCorrectPath() {
        if (solveAndCheckMaze()) {
            pathUpdated = true;
        } else {
            System.out.println("No solution found for the maze.");
        }
    }

    public boolean isPathUpdated(){
        return pathUpdated;
    }
    public void resetPathUpdated(){
        pathUpdated = false;
    }

    private boolean solveAndCheckMaze() {
        Queue<Cell> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];
        Map<Cell, Cell> parentMap = new HashMap<>();

        Cell start = cells[0][0];
        Cell goal = cells[rows - 1][cols - 1];

        queue.add(start);
        visited[start.getRow()][start.getCol()] = true;

        while (!queue.isEmpty()) {
            Cell current = queue.poll();

            if (current == goal) {
                Cell pathCell = goal;
                while (pathCell != start) {
                    pathCell.setPath(true);
                    pathCell = parentMap.get(pathCell);
                }
                start.setPath(true);
                return true;
            }

            int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            for (int[] dir : directions) {
                int newRow = current.getRow() + dir[0];
                int newCol = current.getCol() + dir[1];

                if (isInBounds(newRow, newCol) && !visited[newRow][newCol] && !cells[newRow][newCol].isObstacle()) {
                    visited[newRow][newCol] = true;
                    Cell neighbor = cells[newRow][newCol];
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return false;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
