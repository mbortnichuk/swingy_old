package mbortnic.unitfactory.swingy.view.console;

import mbortnic.unitfactory.swingy.model.Hero.Player;
import mbortnic.unitfactory.swingy.reader.ReadFromFile;
import mbortnic.unitfactory.swingy.view.gui.SwingyView;

import java.util.Scanner;

/**
 * Created by mbortnic on 2/9/19.
 */
public class Console {

    public static void start() {
        String hero;
        Player player;
        int type;
        int option = 0;
        int start;
        int createPlayer;

        try {
            createPlayer = DisplayPlayer.setUpHero();
            if (createPlayer == 1) {
                hero = DisplayPlayer.greetPlayer();
                type = DisplayPlayer.printHeroList();
                player = SwingyView.determinePlayer(hero, type);
                start = ShowStatistics.showInfo(type, hero, player);

                if (start == 1) {
//                    GameControll.go(player);
                } else {
                    System.out.println("Thanks for visiting my game!\n");
                    System.exit(0);
                }
            } else if (createPlayer == 2) {
                ReadFromFile.getBaseOfPlayers();

                Scanner scanner = new Scanner(System.in);

                while (scanner.hasNextLine()) {
                    String str = scanner.nextLine();
                    int linesNbr = ReadFromFile.getLinesInFile();
                    if (isNbr(str) == true) {
                        try {
                            int ind = Integer.parseInt(str);
                            if (ind > 0 && ind <= linesNbr) {
                                option = ind;
                                break ;
                            }
                        } catch (Exception exception) {
                            System.out.println("Try one more time.");
                        }
                    } else {
                        System.out.println("Try one more time.");
                    }
                    player = SwingyView.DBPlayer(ReadFromFile.getPlayer(option));
//                    GameControll.go(player);
                }
            }
        } catch (Exception exception) {
            exception.getMessage();
        }
    }

    public static boolean isNbr(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c) != true) {
                return false;
            }
        }
        return true;
    }


}
