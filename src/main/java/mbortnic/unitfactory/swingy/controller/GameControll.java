package mbortnic.unitfactory.swingy.controller;

import mbortnic.unitfactory.swingy.model.Hero.Player;
import mbortnic.unitfactory.swingy.model.Villian.Villian;
import mbortnic.unitfactory.swingy.reader.ReadFromFile;
import mbortnic.unitfactory.swingy.view.console.DisplayPlayer;

import java.io.Console;
import java.util.Random;

/**
 * Created by mbortnic on 2/9/19.
 */
public class GameControll {

    public GameControll() {

    }

    public static boolean luck() {
        Random rand = new Random();
        int l = rand.nextInt(2) + 1;
        if (l == 1) {
            return true;
        }
        return false;
    }

    public static void go(Player player) {
        MapForConsole map = new MapForConsole(player);
        map.showGameField();
        DisplayPlayer.showAvailableDirections();
        Console console = System.console();

        while (true) {
            String str = console.readLine();

            if (str.matches("\\s*[1-5]\\s*")) {
                int dir = Integer.parseInt(str);
                if (dir == 1) {
                    map.updatePlayerPos(1, 0);
                    map.showGameField();
                    DisplayPlayer.showAvailableDirections();
                } else if (dir == 2) {
                    map.updatePlayerPos(0, 1);
                    map.showGameField();
                    DisplayPlayer.showAvailableDirections();
                } else if (dir == 3) {
                    map.updatePlayerPos(0, -1);
                    map.showGameField();
                    DisplayPlayer.showAvailableDirections();
                } else if (dir == 4) {
                    map.updatePlayerPos(-1, 0);
                    map.showGameField();
                    DisplayPlayer.showAvailableDirections();
                } else if (dir == 5) {
                    System.exit(0);
                }
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    public static int fight(Player player, Villian villian) {
        Random rand = new Random();
        int fi = 0;
        int success = 0;
        int shot = 0;

        if (luck() == true || player.getHeroStatistics().getPow() > villian.getPow()) {
            fi = 1;
        }
        if (player.getHeroStatistics().getHitp() > 0) {
            while (player.getHeroStatistics().getHitp() > 0 && villian.getHitp() > 0) {
                System.out.println("Your hero hit points: " + player.getHeroStatistics().getHitp());
                System.out.println("Villian hit points: " + villian.getHitp());

                if (fi == 0) {
                    shot = rand.nextInt(30) + 1;
                    if (villian.getHitp() > 0) {
                        player.getHeroStatistics().setHitp(-shot);
                        ReadFromFile.refreshFile(player);
                        System.out.println("You've been attacked. You've lost" + shot + " hit points.");
                        if (player.getHeroStatistics().getHitp() <= 0) {
                            success = 0;
                            break ;
                        }
                        fi = 1;
                    }
                } else if (fi == 1) {
                    shot = rand.nextInt(50) + 1;
                    if (player.getHeroStatistics().getHitp() > 0) {
                        villian.setHitp(-shot);
                        System.out.println("You damaged your enemy with " + shot + "points.");
                        if (villian.getHitp() <= 0) {
                            success = 1;
                            break;
                        }
                        fi = 0;
                    }
                }
            }
        } else {
            System.out.println("You are too weak to fight!\n");
            System.out.println("Your HP is: " + player.getHeroStatistics().getHitp());
        }
        return success;
    }



}
