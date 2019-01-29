package mbortnic.unitfactory.swingy.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mbortnic on 1/25/19.
 */
public class GUI extends JFrame {

    public GUI () {
    }

//    private final String[] heroList = ReadFromFile.ReadStr();
//    private final JList listOfHeros = new JList<>(heroList);

    private final JFrame createHeroFrame = new JFrame("Create your Hero");
    private final JFrame selectHeroFrame = new JFrame("Select your Hero");
    private final JFrame heroStatistics = new JFrame("Hero Statistics");
    private final JFrame swingyFrame = new JFrame("SWINGY");
    private final JFrame playerCreationFrame = new JFrame("Player creation");
    private final JFrame gameFrame = new JFrame("Swingy Game");
    private static JFrame gameOverFrame = new JFrame("Game Over");

    private JLabel createPlayerLabel = new JLabel("Create your hero");
    private JLabel enterPlayerNameLabel = new JLabel("Name your hero");

    private JTextField playerNameField = new JTextField();
    private JTextArea textArea = new JTextArea();

    private final JRadioButton undeadButton = new JRadioButton("UNDEAD");
    private final JRadioButton humanButton = new JRadioButton("HUMAN");

    private JButton welcomeButton = new JButton("Enter");
    private JButton createPlayerButton = new JButton("CREATE");
    private JButton selectPlayerButton = new JButton("SELECT");

    private String[] proverka = null;
    private int type;
    private String hero;
    private String present;
    private String playerInfo;

//    private MapForGUI guiMap;
//    private Player player;


    public void displayFrame() {
        createPlayerLabel.setBackground(Color.blue);
        createPlayerLabel.setBounds(110, 100, 100, 40);
        enterPlayerNameLabel.setBackground(Color.blue);
        enterPlayerNameLabel.setBounds(110, 140, 100, 40);
        playerNameField.setCaretColor(Color.cyan);
        playerNameField.setBounds(100, 180, 100, 40);
        welcomeButton.setBackground(Color.red);
        welcomeButton.setBounds(100, 220, 100, 40);
        playerCreationFrame.add(enterPlayerNameLabel);
        playerCreationFrame.add(createPlayerLabel);
        playerCreationFrame.setBackground(Color.yellow);
        playerCreationFrame.add(playerNameField);
        playerCreationFrame.add(welcomeButton);
        playerCreationFrame.setSize(500, 500);
        playerCreationFrame.setVisible(true);
        playerCreationFrame.setLayout(null);
        playerCreationFrame.setLocationRelativeTo(null);
        playerCreationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        welcomeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hero = playerNameField.getText();
                hero = hero.trim();

                if (hero.length() > 0) {
                    proverka = hero.split("\\s");

                    if (proverka != null) {
                        hero = String.join("_", proverka);
                    }

                    if (hero.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter your hero name!");
                    } else {
//                        createHero();
                        playerCreationFrame.setVisible(false);
                        playerCreationFrame.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Blanks are not allowed.");
                }
            }
        });

    }

    public void displayGUI() {
        createPlayerButton.setBounds(200, 140, 100, 40);
        createPlayerButton.setBackground(Color.yellow);
        selectPlayerButton.setBounds(110, 180, 100, 40);
        selectPlayerButton.setBackground(Color.blue);
        swingyFrame.add(createPlayerButton);
        swingyFrame.add(selectPlayerButton);
        swingyFrame.setSize(500, 500);
        swingyFrame.setVisible(true);
        swingyFrame.setLayout(null);
        swingyFrame.setLocationRelativeTo(null);
        swingyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createPlayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayFrame();
                swingyFrame.dispose();
            }
        });

        selectPlayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                selectHero();
//                displayFrame();
                swingyFrame.setVisible(false);
                swingyFrame.dispose();
            }
        });
    }

//    public void createHero() {
//
//    }

//    public void selectHero() {
//
//    }

//    public void playerStatistics() {
//
//    }

//    public void play() {
//
//    }

//    public void endOfGame() {
//
//    }

}
