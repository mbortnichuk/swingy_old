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

    private JLabel createPlayerLabel = new JLabel("CREATE YOUR HERO");
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
    private JButton backButton = new JButton("Back");

    private String[] proverka = null;
    private int type;
    private String hero;
    private String artif;
    private String playerInfo;

    private MapForGUI guiMap;
    private Player player;


    public void displayFrame() {
//        createPlayerLabel.setBackground(Color.blue);
        createPlayerLabel.setBounds(190, 100, 200, 40);
//        enterPlayerNameLabel.setBackground(Color.blue);
        enterPlayerNameLabel.setBounds(200, 170, 200, 30);
//        playerNameField.setCaretColor(Color.cyan);
        playerNameField.setBounds(130, 200, 250, 35);
//        welcomeButton.setBackground(Color.red);
        welcomeButton.setBounds(155, 250, 200, 30);
        backButton.setBounds(200, 340, 100, 40);
        playerCreationFrame.add(enterPlayerNameLabel);
        playerCreationFrame.add(createPlayerLabel);
        playerCreationFrame.add(backButton);
        playerCreationFrame.setBackground(Color.yellow);
        playerCreationFrame.add(playerNameField);
        playerCreationFrame.add(welcomeButton);
        playerCreationFrame.setSize(500, 500);
        playerCreationFrame.setLocationRelativeTo(null);
        playerCreationFrame.setLayout(null);
        playerCreationFrame.setVisible(true);
        playerCreationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayGUI();
            }
        });

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
        createPlayerButton.setBounds(180, 170, 120, 40);
//        createPlayerButton.setBackground(Color.yellow);
        selectPlayerButton.setBounds(180, 230, 120, 40);
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
        JButton bButton = new JButton("Back");
        humanButton.setBounds(200, 180, 100, 40);
        undeadButton.setBounds(200, 210, 100, 40);
        enterButton.setBounds(200, 260, 100, 40);
        bButton.setBounds(200, 340, 100, 40);

        humanButton.setSelected(true);
//        createHeroFrame.getRootPane().setDefaultButton(humanButton);

        bGroup.add(humanButton);
        bGroup.add(undeadButton);

        createHeroFrame.add(humanButton);
        createHeroFrame.add(undeadButton);
        createHeroFrame.add(enterButton);
        createHeroFrame.add(bButton);
        createHeroFrame.setSize(500, 500);
        createHeroFrame.setLocationRelativeTo(null);
        createHeroFrame.setLayout(null);
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

        bButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayFrame();
            }
        });
    }

    public void selectHero() {
        JButton enterButton = new JButton("CONTINUE");
        JButton exitButton = new JButton("EXIT SWINGY");

        JButton bButton = new JButton("Back");

        selectExistingPlayer.setBounds(200, 20, 200, 30);
        listOfHeros.setBounds(20, 70, 320, 360);
        enterButton.setBounds(365, 170, 100, 40);
        exitButton.setBounds(365, 270, 100, 40);
        bButton.setBounds(365, 390, 100, 40);

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

        bButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayGUI();
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
        selectHeroFrame.add(bButton);
        selectHeroFrame.add(listOfHeros);
        selectHeroFrame.setSize(500, 500);
        selectHeroFrame.setLocationRelativeTo(null);
        selectHeroFrame.setLayout(null);
        selectHeroFrame.setVisible(true);
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
        label1.setBounds(180, 100, 100, 30);

        label2 = new JLabel("Hero: " + hero);
        label2.setBounds(180, 130, 100, 30);

        final String playerType;
        label3 = new JLabel("Hero: " + (playerType = player.getHeroStatistics().getPlayerType()));
        label3.setBounds(180, 150, 100, 30);

        int lvl;
        label4 = new JLabel("Lvl: " + (lvl = player.getHeroStatistics().getLvl()));
        label4.setBounds(180, 170, 100, 30);

        int attack;
        label5 = new JLabel("Attack: " + (attack = player.getHeroStatistics().getAttack()));
        label5.setBounds(180, 190, 100, 30);

        int protection;
        label6 = new JLabel("Protection: " + (protection = player.getHeroStatistics().getProtection()));
        label6.setBounds(180, 210, 100, 30);

        int hitp;
        label7 = new JLabel("Hit Points: " + (hitp = player.getHeroStatistics().getHitp()));
        label7.setBounds(180, 230, 100, 30);

        label8 = new JLabel("Artifact: " + (artif = player.getArt().getArtType()));
        label8.setBounds(180, 250, 100, 30);

        enterButton.setBounds(180, 300, 100, 40);

        heroStatisticsFrame.add(label1);
        heroStatisticsFrame.add(label2);
        heroStatisticsFrame.add(label3);
        heroStatisticsFrame.add(label4);
        heroStatisticsFrame.add(label5);
        heroStatisticsFrame.add(label6);
        heroStatisticsFrame.add(label7);
        heroStatisticsFrame.add(label8);
        heroStatisticsFrame.add(enterButton);

        heroStatisticsFrame.setSize(500, 500);
        heroStatisticsFrame.setLocationRelativeTo(null);
        heroStatisticsFrame.setLayout(null);
        heroStatisticsFrame.setVisible(true);
        heroStatisticsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


//        heroData = hero.getHeroStats().getHeroType() + " " +
//                player + " " + hero.getHeroStats().getLevel() + " " +
//                hero.getHeroStats().getAttack() + " " + hero.getHeroStats().getAttack() +
//                " " + hero.getHeroStats().getHitPoints() + " " + hero.getHeroStats().getExperience() +
//                " " + artifact.toUpperCase();

        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                playerInfo = player.getHeroStatistics().getPlayerType() + " " + hero + " ";
                playerInfo = player.getHeroStatistics().getPlayerType() + " " + hero + " " + player.getHeroStatistics().getLvl() + " " +
                            player.getHeroStatistics().getAttack() + " " + player.getHeroStatistics().getAttack() + " " +
                            player.getHeroStatistics().getHitp() + " " + player.getHeroStatistics().getExp() + " " +
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

        textArea.setBounds(150, 100, 500, 500);

        northButton.setBounds(130, 660, 100, 40);
        southButton.setBounds(270, 660, 100, 40);
        westButton.setBounds(430, 660, 100, 40);
        eastButton.setBounds(580, 660, 100, 40);

        northButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiMap.updatePlayerPos(-1, 0); // 0 -1
            }
        });
        southButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiMap.updatePlayerPos(1, 0); // 0 1
            }
        });
        westButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiMap.updatePlayerPos(0, -1); // 1 0
            }
        });
        eastButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiMap.updatePlayerPos(0, 1); // -1 0
            }
        });

        gameFrame.add(textArea);
        gameFrame.add(northButton);
        gameFrame.add(southButton);
        gameFrame.add(westButton);
        gameFrame.add(eastButton);

        gameFrame.setSize(800, 800);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setLayout(null);
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

//    public void endOfGame() {
//
//    }

}
