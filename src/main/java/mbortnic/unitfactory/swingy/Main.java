package mbortnic.unitfactory.swingy;

import mbortnic.unitfactory.swingy.view.GUI;
import mbortnic.unitfactory.swingy.writer.WriteToFile;

/**
 * Created by mbortnic on 1/25/19.
 */
public class Main {

    public static void main(String[] args) {

        try {
            WriteToFile.create();

            if (args.length != 1) {
                System.out.println("Usage: java -jar swingy.jar [gui/console]");
                System.exit(1);
            }

            if (args[0].equals("console")) {
                System.out.println("Console View!");
//                Console.show();
            } else {
                GUI gui = new GUI();
                gui.displayGUI();
            }
        } finally {
            WriteToFile.close();
        }

    }

}


//hero = GameView.NewHero(player, type);
//        JLabel l1, l2, l3, l4, l5, l6, l7, l8;
//        JButton enter;
//
//        l8 = new JLabel("YOUR HERO STATS");
//        l8.setBounds(200,200, 200,30);

//        l1 = new JLabel("Hero: " + player);
//        l1.setBounds(200,220, 200,30);

//        String heroType;
//        l2 = new JLabel("Hero: " + (heroType = hero.getHeroStats().getHeroType()));
//        l2.setBounds(200,240, 200,30);

//        int level;
//        l3 = new JLabel("Level: " +  (level = hero.getHeroStats().getLevel()));
//        l3.setBounds(200,260, 200,30);

//        int attack;
//        l4 = new JLabel("Attack: " + (attack = hero.getHeroStats().getAttack()));
//        l4.setBounds(200,280, 200,30);

//        int defense;
//        l5 = new JLabel("Defense: " + (defense = hero.getHeroStats().getDefense()));
//        l5.setBounds(200,300, 200,30);

//        int hitpoints;
//        l6 = new JLabel("Hitpoints: " + (hitpoints = hero.getHeroStats().getHitPoints()));
//        l6.setBounds(200,320, 200,30);

//        l7 = new JLabel("Artifact: " + (artifact = hero.getArtifact().getType()));
//        l7.setBounds(200,340, 200,30);

//        enter = new JButton("Enter");
//        enter.setBounds(200,370, 200,30);
//
//        statsFrame.add(l1);
//        statsFrame.add(l2);
//        statsFrame.add(l3);
//        statsFrame.add(l4);
//        statsFrame.add(l5);
//        statsFrame.add(l6);
//        statsFrame.add(l7);
//        statsFrame.add(l8);
//        statsFrame.add(enter);
//        statsFrame.setSize(600, 600);
//        statsFrame.setLocationRelativeTo(null);
//        statsFrame.setLayout(null);
//        statsFrame.setVisible(true);
//        statsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        enter.addActionListener(new ActionListener(){
//@Override
//public void actionPerformed(ActionEvent arg0) {
//        heroData = hero.getHeroStats().getHeroType() + " " +
//        player + " " + hero.getHeroStats().getLevel() + " " +
//        hero.getHeroStats().getAttack() + " " + hero.getHeroStats().getAttack() +
//        " " + hero.getHeroStats().getHitPoints() + " " + hero.getHeroStats().getExperience() +
//        " " + artifact.toUpperCase();
//
//        WriteFile.writeToFile(heroData);
//        WriteFile.closeFile();
//        playGame();
//        statsFrame.setVisible(false);
//        statsFrame.dispose();
//        }
//        });

