package games.AICompanion.src;

public class Main {
    public static void main(String[] args) {

        System.out.println("Started program");

        AICompanionGameGUI gameGUI = new AICompanionGameGUI(20, 30); // Create a nxm grid
        System.out.println("GameGUI generated");

    }
}

/*
* TODO: Implement some sort of sorting algorithm. Maybe a score tracker? Previous games played?
* */
