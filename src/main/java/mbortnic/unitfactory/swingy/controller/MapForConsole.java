package mbortnic.unitfactory.swingy.controller;

import mbortnic.unitfactory.swingy.model.Hero.Player;
import mbortnic.unitfactory.swingy.model.Villian.Villian;
import mbortnic.unitfactory.swingy.reader.ReadFromFile;

import javax.swing.*;
import java.util.ArrayList;
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




}
