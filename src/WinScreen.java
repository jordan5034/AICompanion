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