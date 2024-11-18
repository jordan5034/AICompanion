/*
import java.util.Scanner;

public class Game {
    private Grid grid;
    private AI ai;
    private Scanner scanner;

    public Game(int rows, int cols) {
        grid = new Grid(rows, cols);
        ai = new AI(grid, 0, 0); // Start AI at position (0, 0)
        scanner = new Scanner(System.in);
    }



    public void start() {
        grid.printGrid();
        String command = "";
        while (!command.equalsIgnoreCase("quit")) {
            System.out.print("Enter command (up, down, left, right, quit): ");
            command = scanner.nextLine().toLowerCase();

            switch (command) {
                case "up":
                    ai.moveUp();
                    break;
                case "down":
                    ai.moveDown();
                    break;
                case "left":
                    ai.moveLeft();
                    break;
                case "right":
                    ai.moveRight();
                    break;
                case "quit":
                    System.out.println("Exiting the game. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid command. Try again.");
            }

            grid.printGrid(); // Redraw grid after each move
        }
        scanner.close();
    }
}
*/
