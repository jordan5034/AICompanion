public class Main {
    public static void main(String[] args) {

        System.out.println("Started program");

        GameGUI gameGUI = new GameGUI(20, 20); // Create a nxm grid
        System.out.println("GameGUI generated");

        gameGUI.start();
        System.out.println("Game started");
    }
}
