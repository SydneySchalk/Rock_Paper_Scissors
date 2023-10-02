import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissors extends JFrame {

    private JButton rockButton;
    private JButton paperButton;
    private JButton scissorsButton;
    private JButton quitButton;
    private JTextArea resultsTextArea;
    private JLabel playerWinsLabel;
    private JLabel computerWinsLabel;
    private JLabel tiesLabel;
    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;

    public RockPaperScissors() {
        setTitle("Rock Paper Scissors Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Choose Your Move"));
        rockButton = new JButton(new ImageIcon("C:\\Users\\tadpo\\IdeaProjects\\Rock_Paper_Scissors\\src\\rock.png"));
        paperButton = new JButton(new ImageIcon("C:\\Users\\tadpo\\IdeaProjects\\Rock_Paper_Scissors\\src\\paper.png"));
        scissorsButton = new JButton(new ImageIcon("C:\\Users\\tadpo\\IdeaProjects\\Rock_Paper_Scissors\\src\\scissors.png"));
        quitButton = new JButton("Quit");
        buttonsPanel.add(rockButton);
        buttonsPanel.add(paperButton);
        buttonsPanel.add(scissorsButton);
        buttonsPanel.add(quitButton);

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(3, 2));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Statistics"));
        playerWinsLabel = new JLabel("Player Wins: 0");
        computerWinsLabel = new JLabel("Computer Wins: 0");
        tiesLabel = new JLabel("Ties: 0");
        statsPanel.add(playerWinsLabel);
        statsPanel.add(computerWinsLabel);
        statsPanel.add(tiesLabel);

        resultsTextArea = new JTextArea(10, 30);
        resultsTextArea.setWrapStyleWord(true);
        resultsTextArea.setLineWrap(true);
        resultsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);

        add(buttonsPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(statsPanel, BorderLayout.SOUTH);

        rockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playGame("Rock");
            }
        });

        paperButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playGame("Paper");
            }
        });

        scissorsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playGame("Scissors");
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    private void playGame(String playerChoice) {
        String[] choices = {"Rock", "Paper", "Scissors"};
        Random rand = new Random();
        int computerChoiceIndex = rand.nextInt(3);
        String computerChoice = choices[computerChoiceIndex];

        String result = determineWinner(playerChoice, computerChoice);
        updateStats(result);

        resultsTextArea.append(result + "\n");
    }

    private String determineWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            ties++;
            return playerChoice + " ties with " + computerChoice + " (Tie)";
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            playerWins++;
            return playerChoice + " beats " + computerChoice + " (Player wins)";
        } else {
            computerWins++;
            return computerChoice + " beats " + playerChoice + " (Computer wins)";
        }
    }

    private void updateStats(String result) {
        playerWinsLabel.setText("Player Wins: " + playerWins);
        computerWinsLabel.setText("Computer Wins: " + computerWins);
        tiesLabel.setText("Ties: " + ties);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                RockPaperScissors frame = new RockPaperScissors();
                frame.setVisible(true);
            }
        });
    }
}
