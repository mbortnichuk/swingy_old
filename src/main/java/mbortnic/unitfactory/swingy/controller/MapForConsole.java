package mbortnic.unitfactory.swingy.controller;

import mbortnic.unitfactory.swingy.model.Hero.Player;
import mbortnic.unitfactory.swingy.model.Villian.Villian;
import mbortnic.unitfactory.swingy.reader.ReadFromFile;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by mbortnic on 2/9/19.
 */
public class MapForConsole {

    private static ArrayList<Villian> villianArray = new ArrayList<Villian>();
    private static ArrayList<Villian> tmpArray = new ArrayList<Villian>();

    private static int size;
    private static int[][] map;
    private int villianNbr;

    private int xCoordinate;
    private int yCoordinate;

    private static int xmap;
    private static int ymap;

    private boolean set = false;
    private int lvl;

    private static Player player;
    private Villian villian = new Villian();

    public MapForConsole(Player player) {
        this.player = player;
    }

    public void endOfGame() {
        System.out.println("\nYou loose!\n\n");
        System.exit(0);
    }

    public void setEnemies() {
        switch (this.villianNbr = player.getHeroStatistics().getLvl() * 8) {

        }
    }

    public static void setMap() {
        size = (player.getHeroStatistics().getLvl() - 1) * 5 + 10 - (player.getHeroStatistics().getLvl() % 2);
        xmap = size;
        ymap = size;
        map = new int[size][size];
    }

    public void setPlayerPosition() {
        int x = 0;
        int y = 0;
        if ((size % 2) == 1) {
            x = (int)(size / 2);
            y = (int)(size / 2);
        } else if ((size % 2) == 0) {
            x = (size / 2);
            y = (size / 2);
        }
        this.xCoordinate = x;
        this.yCoordinate = y;
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
            ReadFromFile.refreshFile(player);
            System.out.println("You're victorious!\n\n");
            System.out.println("1. Continue playing.\n2. Exit swingy.\n");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (str.matches("\\s*[1-2]\\s*")) {
                    int ch = Integer.parseInt(str);
                    if (ch == 1) {
                        villianArray.removeAll(villianArray);
                        GameControll.go(player);
                        System.out.println("Continua playing.");
                    } else if (ch == 2) {
                        System.out.println("Thanks for playing SWINGY!\n\n");
                        System.exit(0);
                    }
                } else {
                    System.out.println("Select 1 or 2!");
                }
            }
        } else if (this.lvl == player.getHeroStatistics().getLvl()) {
            villianArray.removeAll(villianArray);
        }
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
            } else if (player.getHeroStatistics().getExp() > 8050) {
                exp = 8050;
                player.getHeroStatistics().setExp(exp);
            } else if (player.getHeroStatistics().getExp() < 12200) {
                exp = 12200;
                player.getHeroStatistics().setExp(exp);
            } else if (player.getHeroStatistics().getExp() < 12201) {
                System.out.println("     GAME ENDED     \n\n");
                System.exit(0);
            }
//            victory();
        } else if (type == 2) {
            player.getHeroStatistics().setExp(player.getHeroStatistics().getExp() + villian.getPow());
            ReadFromFile.refreshFile(player);
//            victory();
        }
    }

    public static void regEnemy(Villian villian) {
        if (villianArray.contains(villian)) {
            return ;
        }
        villianArray.add(villian);
    }

    public static void deleteEnemy(Villian villian) {
        if (villianArray.contains(villian)) {
            villianArray.remove(villian);
        }
    }

    public void createEnemies() {
        for (int i = 0; i < this.villianNbr; i++) {
            Random rand = new Random();
            int enemyPosX = rand.nextInt(size);
            int enemyPosY = rand.nextInt(size);
            while (enemyPosY == this.yCoordinate || enemyPosX == this.xCoordinate) {
                enemyPosX = rand.nextInt(size);
                enemyPosY = rand.nextInt(size);
            }
            villian = Heros.newVillian(player);
            villian.setVillianPosition(enemyPosX, enemyPosY);
            regEnemy(villian);
        }
    }

    public Villian getEnemyCollision() {
        for (int i = 0; i < villianArray.size(); i++) {
            if (villianArray.get(i).getyCoordinate() == this.yCoordinate && villianArray.get(i).getxCoordinate() == this.xCoordinate) {
                return (villianArray.get(i));
            }
        }
        return null;
    }

    public void showGameField() {
        if (set = false) {
            setMap();
            setPlayerPosition();
            setEnemies();
            if (tmpArray.isEmpty()) {
                createEnemies();
            } else {
                villianArray.addAll(tmpArray);
            }
            set = true;
        }

        //initialize map array to zeros
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                map[y][x] = 0;
            }
        }

        // initialize villians
        for (Villian enemy : villianArray) {
            map[villian.getyCoordinate()][villian.getxCoordinate()] = villian.getIdType();
        }

        //initialize hero
        map[this.yCoordinate][this.xCoordinate] = 4;

        // check collision with enemy
        for (Villian villian : villianArray) {
//            boolean collision = enemyCollision(this.xCoordinate, this.yCoordinate, villian.getyCoordinate(), villian.getxCoordinate());
//            if (collision == true) {
//                break ;
//            }
        }

        System.out.println("Lvl: " + player.getHeroStatistics().getLvl() + " | " + "Attack: " + player.getHeroStatistics().getAttack() + " | " +
                            "Protection: "+ player.getHeroStatistics().getProtection() + " | " + "Hit Points: " + player.getHeroStatistics().getHitp() + " | " +
                            "Exp: " + player.getHeroStatistics().getExp() + "\n\n");

        for (int y = 0; y < ymap; y++) {
            for (int x = 0; x < xmap; x++) {
                switch (map[y][x]) {
                    case 0:
                        System.out.println("|   |");
                        break ;
                    case 1:
                        System.out.println("| m |");
                        break ;
                    case 2:
                        System.out.println("| s |");
                        break ;
                    default:
                        System.out.println("| H |")
                        break ;
                }
            }
            System.out.println();
        }

    }

    public void updatePlayerPos(int xpos, int ypos) {
        int previousX = this.xCoordinate;
        int previousY = this.yCoordinate;
        this.xCoordinate += xpos;
        if (this.xCoordinate < 0) {
            this.xCoordinate = (int)(size / 2);
            upgrdExp(1);
            victory();
            set = false;
            showGameField();
        } else if (this.xCoordinate >= this.size) {
            this.xCoordinate = (int)(size / 2);
            upgrdExp(1);
            victory();
            set = false;
            showGameField();
        } else {
            showGameField();
        }

        this.yCoordinate += ypos;
        if (this.yCoordinate < 0) {
            this.yCoordinate = (int)(size / 2);
            upgrdExp(1);
            victory();
            set = false;
            showGameField();
        } else if (this.yCoordinate >= this.size) {
            this.yCoordinate = (int)(size / 2);
            upgrdExp(1);
            victory();
            set = false;
            showGameField();
        } else {
            showGameField();
        }
    }

    public boolean enemyCollision(int heroY, int heroX, int villianY, int villianX) {
        if ((heroX == villianX) && (heroY == villianY)) {
            System.out.println("You faced a villian, what you gonna to do:\n");
            System.out.println("1. Run.\n2. Fight.\n");
            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (str.matches("\\s*[1-2]\\s*")) {
                    int ch = Integer.parseInt(str);
                    if (ch == 1) {
                        Random rand = new Random();
                        int go = rand.nextInt(2) + 1;
                        if (go == 1) {
                            System.out.println("You're a coward! You loose 5XP\n");
                            System.out.println("Your XP: " + (player.getHeroStatistics().getExp() - 5));
                            showGameField();
                        }
                    } else if (ch == 2) {
                        Villian collided = getEnemyCollision();
                        int victorious = GameControll.fight(player, collided);
                        if (victorious == 1) {
//                            victorious(collided);
                            deleteEnemy(collided);
                            return true;
                        } else {
                            endOfGame();
                            break ;
                        }
                    } else {
                        System.out.println("Choose 1 or 2!");
                    }
                } else {
                    System.out.println("Choose 1 or 2!");
                }
            }
        }
        return false;
    }



}
