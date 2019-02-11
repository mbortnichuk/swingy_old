package mbortnic.unitfactory.swingy.view.gui;

import mbortnic.unitfactory.swingy.controller.MapForGUI;
import mbortnic.unitfactory.swingy.model.Hero.Player;
import mbortnic.unitfactory.swingy.reader.ReadFromFile;
import mbortnic.unitfactory.swingy.writer.WriteToFile;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mbortnic on 1/25/19.
 */
public class GUI extends JFrame {

    public GUI () {
    }

    private final String[] heroList = ReadFromFile.readLineFromFile();
    private final JList listOfHeros = new JList(heroList);

    private final JFrame createHeroFrame = new JFrame("Create your Hero");
    private final JFrame selectHeroFrame = new JFrame("Select your Hero");
    private final JFrame heroStatisticsFrame = new JFrame("Hero Statistics");
    private final JFrame swingyFrame = new JFrame("SWINGY");
    private final JFrame playerCreationFrame = new JFrame("Player creation");
    private final JFrame gameFrame = new JFrame("Swingy Game");
    private static JFrame gameOverFrame = new JFrame("Game Over");

    private JLabel createPlayerLabel = new JLabel("Create your hero");
    private JLabel enterPlayerNameLabel = new JLabel("Name your hero");
    private JLabel selectExistingPlayer = new JLabel("Select existing hero");

    private JTextField playerNameField = new JTextField();
    private JTextArea textArea = new JTextArea();

    private final JRadioButton undeadButton = new JRadioButton("UNDEAD");
    private final JRadioButton humanButton = new JRadioButton("HUMAN");

    private JButton welcomeButton = new JButton("Enter");
    private JButton createPlayerButton = new JButton("CREATE");
    private JButton selectPlayerButton = new JButton("SELECT");
    private JButton enterButton = new JButton("ENTER");

    private String[] proverka = null;
    private int type;
    private String hero;
    private String artif;
    private String playerInfo;

    private MapForGUI guiMap;
    private Player player;


    public void displayFrame() {
//        createPlayerLabel.setBackground(Color.blue);
        createPlayerLabel.setBounds(200, 100, 200, 40);
//        enterPlayerNameLabel.setBackground(Color.blue);
        enterPlayerNameLabel.setBounds(200, 140, 200, 40);
//        playerNameField.setCaretColor(Color.cyan);
        playerNameField.setBounds(200, 180, 100, 40);
//        welcomeButton.setBackground(Color.red);
        welcomeButton.setBounds(200, 320, 200, 40);
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
                        createHero();
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
//        createPlayerButton.setBackground(Color.yellow);
        selectPlayerButton.setBounds(110, 180, 100, 40);
//        selectPlayerButton.setBackground(Color.blue);
        swingyFrame.add(createPlayerButton);
        swingyFrame.add(selectPlayerButton);
        swingyFrame.setSize(500, 500);
        swingyFrame.setLocationRelativeTo(null);
        swingyFrame.setLayout(null);
        swingyFrame.setVisible(true);
        swingyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createPlayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayFrame();
                swingyFrame.dispose();
            }
        });

        selectPlayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectHero();
//                displayFrame();
                swingyFrame.setVisible(false);
                swingyFrame.dispose();
            }
        });
    }

    public void createHero() {
        ButtonGroup bGroup = new ButtonGroup();
        humanButton.setBounds(200, 180, 100, 40);
        undeadButton.setBounds(200, 210, 100, 40);
        enterButton.setBounds(200, 260, 100, 40);
        bGroup.add(humanButton);
        bGroup.add(undeadButton);

        createHeroFrame.add(humanButton);
        createHeroFrame.add(undeadButton);
        createHeroFrame.add(enterButton);
        createHeroFrame.setSize(500, 500);
        createHeroFrame.setLayout(null);
        createHeroFrame.setLocationRelativeTo(null);
        createHeroFrame.setVisible(true);
        createHeroFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (humanButton.isSelected()) {
                    type = 1;
                } else if (undeadButton.isSelected()) {
                    type = 2;
                }
                playerStatistics();
                createHeroFrame.setVisible(false);
                createHeroFrame.dispose();
            }
        });
    }

    public void selectHero() {
        JButton enterButton = new JButton("CONTINUE");
        JButton exitButton = new JButton("EXIT SWINGY");

        selectExistingPlayer.setBounds(200, 20, 200, 40);
        listOfHeros.setBounds(20, 50, 250, 420);
        enterButton.setBounds(200, 150, 100, 40);
        exitButton.setBounds(300, 100, 100, 40);

        listOfHeros.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent arg0) {
                playerInfo = listOfHeros.getSelectedValue().toString();
                player = SwingyView.DBPlayer(playerInfo);

            }
        });

        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (playerInfo == null) {
                    JOptionPane.showMessageDialog(null, "Select your hero!");
                } else {
                    play();
                    selectHeroFrame.setVisible(false);
                    selectHeroFrame.dispose();
                }
            }
        });

//        exitButton.addActionListener(e -> selectHeroFrame.dispose());

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectHeroFrame.dispose();
            }
        });

        selectHeroFrame.add(selectExistingPlayer);
        selectHeroFrame.add(enterButton);
        selectHeroFrame.add(exitButton);
        selectHeroFrame.add(listOfHeros);
        selectHeroFrame.setSize(500, 500);
        selectHeroFrame.setVisible(true);
        selectHeroFrame.setLayout(null);
        selectHeroFrame.setLocationRelativeTo(null);
        selectHeroFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void playerStatistics() {
        player = SwingyView.determinePlayer(hero, type);

        JLabel label1;
        JLabel label2;
        JLabel label3;
        JLabel label4;
        JLabel label5;
        JLabel label6;
        JLabel label7;
        JLabel label8;

        JButton enterButton = new JButton("Continue");

        label1 = new JLabel("Your statistics: ");
        label1.setBounds(100, 100, 100, 30);

        label2 = new JLabel("Hero: " + hero);
        label2.setBounds(100, 110, 100, 30);

        String playerType;
        label3 = new JLabel("Hero: " + (playerType = player.getHeroStatistics().getPlayerType()));
        label3.setBounds(100, 120, 100, 30);

        int lvl;
        label4 = new JLabel("Lvl: " + (lvl = player.getHeroStatistics().getLvl()));
        label4.setBounds(100, 130, 100, 30);

        int attack;
        label5 = new JLabel("Attack: " + (attack = player.getHeroStatistics().getAttack()));
        label5.setBounds(100, 140, 100, 30);

        int protection;
        label6 = new JLabel("Protection: " + (protection = player.getHeroStatistics().getProtection()));
        label6.setBounds(100, 150, 100, 30);

        int hitp;
        label7 = new JLabel("Hit Points: " + (hitp = player.getHeroStatistics().getHitp()));
        label7.setBounds(100, 160, 100, 30);

        label8 = new JLabel("Artifact: " + (artif = player.getArt().getArtType()));
        label8.setBounds(100, 170, 100, 30);

        enterButton.setBounds(100, 130, 100, 30);

        heroStatisticsFrame.add(label1);
        heroStatisticsFrame.add(label2);
        heroStatisticsFrame.add(label3);
        heroStatisticsFrame.add(enterButton);

        heroStatisticsFrame.setSize(500, 500);
        heroStatisticsFrame.setLocationRelativeTo(null);
        heroStatisticsFrame.setLayout(null);
        heroStatisticsFrame.setVisible(true);
        heroStatisticsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                playerInfo = player.getHeroStatistics().getPlayerType() + " " + hero + " ";
                playerInfo = player.getHeroStatistics().getPlayerType() + " " + hero + " " + player.getHeroStatistics().getLvl() + " " +
                            player.getHeroStatistics().getAttack() + " " + player.getHeroStatistics().getHitp() + " " + player.getHeroStatistics().getExp() + " " +
                            artif.toUpperCase();
                WriteToFile.write(playerInfo);
                WriteToFile.close();
                play();
                heroStatisticsFrame.setVisible(false);
                heroStatisticsFrame.dispose();
            }
        });

    }

    public void play() {
        JButton northButton = new JButton("North");
        JButton southButton = new JButton("South");
        JButton westButton = new JButton("West");
        JButton eastButton = new JButton("East");

        guiMap = new MapForGUI(player, gameFrame);
        textArea = guiMap.showGamefield();

        northButton.setBounds(20, 480, 100, 30);
        southButton.setBounds(140, 480, 100, 30);
        westButton.setBounds(280, 480, 100, 30);
        eastButton.setBounds(400, 480, 100, 30);

        northButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiMap.updatePlayerPos(0, -1);
            }
        });
        southButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiMap.updatePlayerPos(0, 1);
            }
        });
        westButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiMap.updatePlayerPos(1, 0);
            }
        });
        eastButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiMap.updatePlayerPos(-1, 0);
            }
        });

        textArea.setBounds(20, 100, 500, 500);
        gameFrame.add(textArea);
        gameFrame.add(northButton);
        gameFrame.add(southButton);
        gameFrame.add(westButton);
        gameFrame.add(eastButton);

        gameFrame.setSize(800, 800);
        gameFrame.setVisible(true);
        gameFrame.setLayout(null);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

//    public void endOfGame() {
//
//    }

}
