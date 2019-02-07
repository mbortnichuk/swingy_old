package mbortnic.unitfactory.swingy.controller;

import mbortnic.unitfactory.swingy.model.Hero.Player;
import mbortnic.unitfactory.swingy.model.Villian.Villian;
import mbortnic.unitfactory.swingy.reader.ReadFromFile;
import mbortnic.unitfactory.swingy.view.GUI;
import oracle.jvm.hotspot.jfr.JFR;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import static mbortnic.unitfactory.swingy.reader.ReadFromFile.readLineFromFile;

/**
 * Created by mbortnic on 1/27/19.
 */
public class MapForGUI extends JFrame {

    private static ArrayList<Villian> villianArray = new ArrayList();
    private static ArrayList<Villian> tmpArray = new ArrayList();

    private Player player;
    private Villian enemy = new Villian();
    private int[][] map;
    private boolean set = false;

    private JTextArea textArea = new JTextArea();

    private JFrame jFrame;

    private static int xCoordinate;
    private static int yCoordinate;

    private int size;
    private int lvl;
    private int xPos;
    private int yPos;
    private int xOld;
    private int yOld;
    private int villiansNbr;

    public MapForGUI(Player player, JFrame jFrame) {
        this.player = player;
        this.jFrame = jFrame;
    }

    public void setMap() {
        size = (player.getHeroStatistics().getLvl() - 1) * 5 + 10 - (player.getHeroStatistics().getLvl() % 2);
        xCoordinate = size;
        yCoordinate = size;
        map = new int[size][size];
    }

    public void setEnemy() {
        this.villiansNbr = player.getHeroStatistics().getLvl() * 8;
    }

    public void setPlayerPosition() {
        int x = 0;
        int y = 0;
        if ((size % 2) == 1) {
            x = (int)(size / 2);
            y = (int)(size / 2);
        } else if ((size % 2) == 0) {
            x = size / 2;
            y = size / 2;
        }
        this.xPos = x;
        this.yPos = y;
    }

    public void victory() {
        if (player.getHeroStatistics().getExp() > 1000 && player.getHeroStatistics().getExp() < 2450) {
            this.lvl = 1;
        } else if (player.getHeroStatistics().getExp() >= 2450 && player.getHeroStatistics().getExp() < 4800) {
            this.lvl = 2;
        } else if (player.getHeroStatistics().getExp() >= 4800 && player.getHeroStatistics().getExp() < 8050) {
            this.lvl = 3;
        } else if (player.getHeroStatistics().getExp() >= 8050 && player.getHeroStatistics().getExp() < 12200) {
            this.lvl = 4;
        } else if (player.getHeroStatistics().getExp() == 12200) {
            this.lvl = 5;
        }

        if (this.lvl > player.getHeroStatistics().getLvl()) {
            player.getHeroStatistics().setLvl(this.lvl);
//            readLineFromFile().refreshFile();
            JOptionPane.showMessageDialog(null, "Congrats! Next Level!");
            villianArray.removeAll(villianArray);
            textArea.append(this.lvl + "\n");
        } else if (this.lvl == player.getHeroStatistics().getLvl()) {
            textArea.selectAll();
            textArea.replaceSelection("");
            tmpArray.addAll(villianArray);
            villianArray.removeAll(villianArray);
        }
    }

    public void updatePlayerPos(int xPos, int yPos) {
        this.xOld = this.xPos;
        this.yOld = this.yPos;

        this.xPos += xPos;
        if (this.xPos < 0) {
            this.xPos = (int)(size / 2);
//            upgrdExp(1);
            victory();
            set = false;
//            showGamefield();
        } else if (this.xPos >= this.size) {
//            upgrdExp(1);
            victory();
            set = false;
//            showGamefield();
        } else {
            textArea.selectAll();
            textArea.replaceSelection("");
//            showGamefield();
        }

        this.yPos += yPos;
        if (this.yPos < 0) {
            this.yPos = (int)(size / 2);
//            upgrdExp(1);
            victory();
            set = false;
//            showGamefield();
        } else if (this.yPos >= this.size) {
//            upgrdExp();
            victory();
            set = false;
//            showGamefield();
        } else {
            textArea.selectAll();
            textArea.replaceSelection("");
//            showGamefield();
        }
    }

//    public JTextArea showGamefield() {
//
//    }

    public static void regEnemy(Villian villian) {
        if (villianArray.contains(villian)) {
            return;
        }
        villianArray.add(villian);
    }

    public void createEnemies() {
        for (int i = 0; i < this.villiansNbr; i++) {
            Random rand = new Random();
            int enemyPosX = rand.nextInt(size);
            int enemyPosY = rand.nextInt(size);

            while (enemyPosX == this.xPos || enemyPosY == this.yPos) {
                enemyPosX = rand.nextInt(size);
                enemyPosY = rand.nextInt(size);
            }
            enemy = Heros.newVillian(player);
            enemy.setVillianPosition(enemyPosX, enemyPosY);
            regEnemy(enemy);
        }
    }

    public Villian getEnemyCollision() {
        for (int i = 0; i < villianArray.size(); i++) {
            if (villianArray.get(i).getyCoordinate() == this.yPos && villianArray.get(i).getyCoordinate() == this.yPos) {
                return (villianArray.get(i));
            }
        }
        return null;
    }

    public void upgrdExp(int type) {
        if (type == 1) {
            int exp;
            if (player.getHeroStatistics().getExp() < 2450) {
                exp = 2450;
                player.getHeroStatistics().setExp(exp);
            } else if (player.getHeroStatistics().getExp() < 4800) {
                exp = 4800;
                player.getHeroStatistics().setExp(exp);
            } else if (player.getHeroStatistics().getExp() < 8050) {
                exp = 8050;
                player.getHeroStatistics().setExp(exp);
            } else if (player.getHeroStatistics().getExp() < 12200) {
                exp = 12200;
                player.getHeroStatistics().setExp(exp);
            } else if (player.getHeroStatistics().getExp() < 12201) {
                System.out.println("     GAME ENDED     \n\n");
//                GUI.displayGUI();
            }
            victory();
        } else if (type == 2) {
            victory();
        }
    }

    public boolean luckySituation() {
        Random rand = new Random();
        int luckySituation = rand.nextInt(2) + 1;
        if (luckySituation == 1) {
            return (true);
        }
        return (false);
    }

    public int fatality() {
        int fatality = 0;
        int victory = 0;
        int shot = 0;
        Random rand = new Random();
        if (luckySituation() == true || player.getHeroStatistics().getPow() > enemy.getPow()) {
            fatality = 1;
        }
        if (player.getHeroStatistics().getHitp() > 0) {
            while (player.getHeroStatistics().getHitp() > 0 && enemy.getHitp() > 0) {
                if (fatality == 0) {
                    shot = rand.nextInt(20) + 1;
                    if (enemy.getHitp() > 0) {
                        player.getHeroStatistics().setHitp(-shot);
//                    readLineFromFile().refreshFile(player);

                        if (player.getHeroStatistics().getHitp() <= 0) {
                            victory = 0;
                            break ;
                        }
                        fatality = 1;
                    }
                } else if (fatality == 1) {
                    shot = rand.nextInt(50) + 1;
                    if (player.getHeroStatistics().getHitp() > 0) {
                        enemy.setHitp(-shot);
                        if (enemy.getHitp() <= 0) {
                            victory = 1;
                            break ;
                        }
                        fatality = 0;
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "You are too weak to fight!");
        }
        return victory;
    }

    public boolean enemyCollision(int heroX, int heroY, int enemyX, int enemyY) {
        if ((heroX == enemyX) && (heroY == enemyY)) {
            enemy = getEnemyCollision();
            int buttonForDialog = JOptionPane.YES_NO_OPTION;
            int buttonForResult = JOptionPane.showConfirmDialog(this, "You faced your enemy!", "Fight or Run?", buttonForDialog);

            if (buttonForResult == 0) {
                if (fatality() == 0) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "You died!\n\n");
                    jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
                }
            } else {
                Random rand = new Random();
                int go = rand.nextInt(2) + 1;
                if (go == 1) {
                    textArea.selectAll();
                    textArea.replaceSelection("");
                    textArea.append("Duck\n\n");
                    this.xPos = this.xOld;
                    this.yPos = this.yOld;
                } else {
                    if (fatality() == 1) {
                        villianArray.remove(enemy);
                        upgrdExp(2);
                        return (true);
                    } else {
                        JOptionPane.showMessageDialog(null, "You died!\n\n");
                        jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
                    }
                }
            }
        }
        return (false);
    }

}
