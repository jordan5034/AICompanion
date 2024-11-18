import javax.swing.*;
import java.awt.*;

public class WinScreen extends JPanel{

    private JButton restartButton;

    public WinScreen(){
        this.setLayout(new BorderLayout());

        // Win Label
        JLabel winLabel = new JLabel("You Win!", SwingConstants.CENTER);
        winLabel.setFont(new Font("Arial", Font.BOLD, 30));
        winLabel.setForeground(Color.GREEN);
        this.add(winLabel, BorderLayout.CENTER);

        // Button to quit the game
        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        quitButton.addActionListener(e -> System.exit(0));

        // Button to restart the game
        restartButton = new JButton("Restart Game");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 20));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(restartButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    // Add a method to allow GameGUI to set the restart action
    public void addRestartAction(Runnable restartAction) {
        restartButton.addActionListener(e -> restartAction.run());
    }

}


/*
public class WinScreen extends JPanel {
    public WinScreen() {
        setLayout(new BorderLayout());

        // Label to display win message
        JLabel winLabel = new JLabel("You Win!", SwingConstants.CENTER);
        winLabel.setFont(new Font("Arial", Font.BOLD, 30));
        winLabel.setForeground(Color.GREEN);

        // Button to restart the game
        JButton restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 20));
        restartButton.addActionListener(e -> restartGame());

        // Button to quit the game
        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        quitButton.addActionListener(e -> System.exit(0));

        // Add components to the panel
        add(winLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(restartButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Method to restart the game (you can add game reset logic here)
    private void restartGame() {
        // Reset the game state or reinitialize the game components here
        // For example, reset the grid and position of the AI

        GameGUI gameGUI = new GameGUI(gameFrame);
        System.out.println("Game Restarted!"); // Placeholder for game reset logic
    }
}
*/
