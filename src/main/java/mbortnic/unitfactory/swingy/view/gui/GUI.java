package mbortnic.unitfactory.swingy.view.gui;

import mbortnic.unitfactory.swingy.controller.MapForGUI;
import mbortnic.unitfactory.swingy.model.Hero.Player;
import mbortnic.unitfactory.swingy.reader.ReadFromFile;
import mbortnic.unitfactory.swingy.writer.WriteToFile;
import oracle.jvm.hotspot.jfr.JFR;

import javax.swing.*;
import javax.swing.border.Border;
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

//    DefaultListModel listModel = new DefaultListModel();

    private final String[] heroList = ReadFromFile.readLineFromFile();
    private final JList listOfHeros = new JList(heroList);
//    JScrollPane scrollPane = new JScrollPane();


    private final JFrame createHeroFrame = new JFrame("Create your Hero");
    private final JFrame selectHeroFrame = new JFrame("Select your Hero");
    private final JFrame heroStatisticsFrame = new JFrame("Hero Statistics");
    private final JFrame swingyFrame = new JFrame("SWINGY");
    private final JFrame playerCreationFrame = new JFrame("Player creation");
    private final JFrame gameFrame = new JFrame("Swingy Game");
    private static JFrame gameOverFrame = new JFrame("Game Over");

    private JLabel createPlayerLabel = new JLabel(" CREATE YOUR HERO");
    private JLabel enterPlayerNameLabel = new JLabel(" Name your hero");
    private JLabel selectExistingPlayer = new JLabel(" Select existing hero");

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
        createPlayerLabel.setBounds(165, 95, 200, 35);
        createPlayerLabel.setBackground(Color.BLACK);
        createPlayerLabel.setForeground(Color.GREEN);
        createPlayerLabel.setOpaque(true);
        createPlayerLabel.setFont(new Font("Courier", Font.PLAIN, 16));

        enterPlayerNameLabel.setBounds(190, 170, 125, 30);
        enterPlayerNameLabel.setBackground(Color.BLACK);
        enterPlayerNameLabel.setForeground(Color.GREEN);
        enterPlayerNameLabel.setOpaque(true);
        enterPlayerNameLabel.setFont(new Font("Courier", Font.PLAIN, 14));;

        playerNameField.setBounds(130, 210, 250, 30);
        playerNameField.setCaretColor(Color.GREEN);
        playerNameField.setBackground(Color.BLACK);
        playerNameField.setForeground(Color.GREEN);
        playerNameField.setText("Enter name here");
        Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
        playerNameField.setBorder(border);

        welcomeButton.setBounds(155, 260, 200, 30);
        welcomeButton.setBackground(Color.GREEN);
        welcomeButton.setOpaque(true);
        welcomeButton.setBorderPainted(false);

        backButton.setBounds(200, 340, 100, 40);
        backButton.setBackground(Color.GREEN);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);

        playerCreationFrame.add(enterPlayerNameLabel);
        playerCreationFrame.add(createPlayerLabel);
        playerCreationFrame.add(backButton);
        playerCreationFrame.add(playerNameField);
        playerCreationFrame.add(welcomeButton);
        playerCreationFrame.setSize(500, 500);
        playerCreationFrame.setBackground(Color.BLACK);
        playerCreationFrame.getContentPane().setBackground(Color.BLACK);
        playerCreationFrame.setLocationRelativeTo(null);
        playerCreationFrame.setLayout(null);
        playerCreationFrame.setVisible(true);
        playerCreationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayGUI();
                playerCreationFrame.dispose();
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
        createPlayerButton.setBackground(Color.GREEN);
        createPlayerButton.setOpaque(true);
        createPlayerButton.setBorderPainted(false);
        createPlayerButton.setFont(new Font("Courier", Font.PLAIN, 16));
//        createPlayerButton.setForeground(Color.BLACK);

        selectPlayerButton.setBounds(180, 260, 120, 40);
        selectPlayerButton.setBackground(Color.GREEN);
        selectPlayerButton.setOpaque(true);
        selectPlayerButton.setBorderPainted(false);
        selectPlayerButton.setFont(new Font("Courier", Font.PLAIN, 16));

        swingyFrame.add(createPlayerButton);
        swingyFrame.add(selectPlayerButton);
        swingyFrame.setSize(500, 500);
        swingyFrame.setBackground(Color.BLACK);
        swingyFrame.getContentPane().setBackground(Color.BLACK);
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
        JLabel race = new JLabel("CHOOSE YOUR RACE");

        race.setBounds(165, 55, 200, 30);
        race.setForeground(Color.GREEN);
        race.setFont(new Font("Courier", Font.PLAIN, 16));

        humanButton.setBounds(200, 130, 100, 40);
        humanButton.setForeground(Color.GREEN);
        humanButton.setFont(new Font("Courier", Font.PLAIN, 14));

        undeadButton.setBounds(200, 160, 100, 40);
        undeadButton.setForeground(Color.GREEN);
        undeadButton.setFont(new Font("Courier", Font.PLAIN, 14));


        enterButton.setBounds(200, 230, 100, 40);
        enterButton.setBackground(Color.GREEN);
        enterButton.setOpaque(true);
        enterButton.setBorderPainted(false);
        enterButton.setFont(new Font("Courier", Font.PLAIN, 16));

        bButton.setBounds(200, 360, 100, 40);
        bButton.setBackground(Color.GREEN);
        bButton.setOpaque(true);
        bButton.setBorderPainted(false);
        bButton.setFont(new Font("Courier", Font.PLAIN, 16));


        humanButton.setSelected(true);
//        createHeroFrame.getRootPane().setDefaultButton(humanButton);

        bGroup.add(humanButton);
        bGroup.add(undeadButton);

        createHeroFrame.add(race);
        createHeroFrame.add(humanButton);
        createHeroFrame.add(undeadButton);
        createHeroFrame.add(enterButton);
        createHeroFrame.add(bButton);
        createHeroFrame.setSize(500, 500);
        createHeroFrame.setBackground(Color.BLACK);
        createHeroFrame.getContentPane().setBackground(Color.BLACK);
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
                createHeroFrame.dispose();
            }
        });
    }

    public void selectHero() {
        JButton enterButton = new JButton("CONTINUE");
        JButton exitButton = new JButton("EXIT");

        JButton bButton = new JButton("Back");

        selectExistingPlayer.setBounds(160, 20, 200, 30);
        selectExistingPlayer.setBackground(Color.GREEN);
        selectExistingPlayer.setOpaque(true);
        selectExistingPlayer.setFont(new Font("Courier", Font.PLAIN, 15));

        listOfHeros.setBounds(20, 70, 320, 360);
        listOfHeros.setBackground(Color.GREEN);
        listOfHeros.setOpaque(true);
        listOfHeros.setFont(new Font("Courier", Font.PLAIN, 12));
        listOfHeros.setSelectedIndex(0);

        enterButton.setBounds(365, 70, 100, 40); // 365 70 100 40
        enterButton.setBackground(Color.GREEN);
        enterButton.setOpaque(true);
        enterButton.setBorderPainted(false);
        enterButton.setFont(new Font("Courier", Font.PLAIN, 12));

        exitButton.setBounds(365, 390, 100, 40);
        exitButton.setBackground(Color.GREEN);
        exitButton.setOpaque(true);
        exitButton.setBorderPainted(false);
        exitButton.setFont(new Font("Courier", Font.PLAIN, 13));

        bButton.setBounds(365, 170, 100, 40); // 365 390 100 40
        bButton.setBackground(Color.GREEN);
        bButton.setOpaque(true);
        bButton.setBorderPainted(false);
        bButton.setFont(new Font("Courier", Font.PLAIN, 13));


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
                selectHeroFrame.dispose();
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
//        selectHeroFrame.add(new JScrollPane(listOfHeros));
        selectHeroFrame.setSize(500, 500);
        selectHeroFrame.setBackground(Color.BLACK);
        selectHeroFrame.getContentPane().setBackground(Color.BLACK);
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
        enterButton.setBackground(Color.GREEN);
        enterButton.setOpaque(true);
        enterButton.setBorderPainted(false);
        enterButton.setFont(new Font("Courier", Font.PLAIN, 12));

        label1 = new JLabel("YOUR STATISTICS: ");
        label1.setBounds(170, 95, 400, 30);
        label1.setForeground(Color.GREEN);
        label1.setFont(new Font("Courier", Font.PLAIN, 13));

        label2 = new JLabel("Hero: ................... " + hero);
        label2.setBounds(110, 130, 400, 30);
        label2.setForeground(Color.GREEN);
        label2.setFont(new Font("Courier", Font.PLAIN, 13));

        final String playerType;
        label3 = new JLabel("Hero: ................... " + (playerType = player.getHeroStatistics().getPlayerType()));
        label3.setBounds(110, 150, 400, 30);
        label3.setForeground(Color.GREEN);
        label3.setFont(new Font("Courier", Font.PLAIN, 13));

        int lvl;
        label4 = new JLabel("Lvl: .................... " + (lvl = player.getHeroStatistics().getLvl()));
        label4.setBounds(110, 170, 400, 30);
        label4.setForeground(Color.GREEN);
        label4.setFont(new Font("Courier", Font.PLAIN, 13));

        int attack;
        label5 = new JLabel("Attack: ................. " + (attack = player.getHeroStatistics().getAttack()));
        label5.setBounds(110, 190, 400, 30);
        label5.setForeground(Color.GREEN);
        label5.setFont(new Font("Courier", Font.PLAIN, 13));

        int protection;
        label6 = new JLabel("Protection: ............. " + (protection = player.getHeroStatistics().getProtection()));
        label6.setBounds(110, 210, 400, 30);
        label6.setForeground(Color.GREEN);
        label6.setFont(new Font("Courier", Font.PLAIN, 13));

        int hitp;
        label7 = new JLabel("Hit Points: ............. " + (hitp = player.getHeroStatistics().getHitp()));
        label7.setBounds(110, 230, 400, 30);
        label7.setForeground(Color.GREEN);
        label7.setFont(new Font("Courier", Font.PLAIN, 13));

        label8 = new JLabel("Artifact: ............... " + (artif = player.getArt().getArtType()));
        label8.setBounds(110, 250, 400, 30);
        label8.setForeground(Color.GREEN);
        label8.setFont(new Font("Courier", Font.PLAIN, 13));

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
        heroStatisticsFrame.setBackground(Color.BLACK);
        heroStatisticsFrame.getContentPane().setBackground(Color.BLACK);
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
        textArea.setForeground(Color.GREEN);
        textArea.setBackground(Color.BLACK);
        Border border = BorderFactory.createLineBorder(Color.GREEN, 1);
        textArea.setBorder(border);

        northButton.setBounds(130, 660, 100, 40);
        northButton.setBackground(Color.GREEN);
        northButton.setOpaque(true);
        northButton.setBorderPainted(false);
        northButton.setFont(new Font("Courier", Font.PLAIN, 13));

        southButton.setBounds(270, 660, 100, 40);
        southButton.setBackground(Color.GREEN);
        southButton.setOpaque(true);
        southButton.setBorderPainted(false);
        southButton.setFont(new Font("Courier", Font.PLAIN, 13));

        westButton.setBounds(430, 660, 100, 40);
        westButton.setBackground(Color.GREEN);
        westButton.setOpaque(true);
        westButton.setBorderPainted(false);
        westButton.setFont(new Font("Courier", Font.PLAIN, 13));

        eastButton.setBounds(580, 660, 100, 40);
        eastButton.setBackground(Color.GREEN);
        eastButton.setOpaque(true);
        eastButton.setBorderPainted(false);
        eastButton.setFont(new Font("Courier", Font.PLAIN, 13));


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
                guiMap.updatePlayerPos(-1, 0);
            }
        });
        eastButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiMap.updatePlayerPos(1, 0);
            }
        });

        gameFrame.add(textArea);
        gameFrame.add(northButton);
        gameFrame.add(southButton);
        gameFrame.add(westButton);
        gameFrame.add(eastButton);
        gameFrame.setBackground(Color.BLACK);
        gameFrame.getContentPane().setBackground(Color.BLACK);

        gameFrame.setSize(800, 800);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setLayout(null);
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void endOfGame() {
        JButton exitButton = new JButton("END GAME");
        JLabel newLabel = new JLabel("Game ended!");

        newLabel.setBounds(145, 90, 150, 40);
        newLabel.setForeground(Color.GREEN);
        newLabel.setFont(new Font("Courier", Font.PLAIN, 16));

        exitButton.setBounds(150, 150, 100, 40);
        exitButton.setBackground(Color.GREEN);
        exitButton.setOpaque(true);
        exitButton.setBorderPainted(false);
        exitButton.setFont(new Font("Courier", Font.PLAIN, 12));

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameOverFrame.dispose();
                System.exit(0);
            }
        });

        gameOverFrame.add(newLabel);
        gameOverFrame.add(exitButton);
        gameOverFrame.setSize(400, 400);
        gameOverFrame.setBackground(Color.BLACK);
        gameOverFrame.getContentPane().setBackground(Color.BLACK);
        gameOverFrame.setLocationRelativeTo(null);
        gameOverFrame.setLayout(null);
        gameOverFrame.setVisible(true);
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
