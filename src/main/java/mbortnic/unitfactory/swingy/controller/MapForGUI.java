package mbortnic.unitfactory.swingy.controller;

import mbortnic.unitfactory.swingy.model.Hero.Player;
import mbortnic.unitfactory.swingy.model.Villian.Villian;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by mbortnic on 1/27/19.
 */
public class MapForGUI extends JFrame {

//    private static ArrayList<Villian> villianArray = new ArrayList<>();
//    private static ArrayList<Villian> tmpArray = new ArrayList<>();

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

//        if (this.lvl > player.getHeroStatistics().getLvl()) {
//            player.getHeroStatistics().setLvl(this.lvl);
//
//        }

    }

}
