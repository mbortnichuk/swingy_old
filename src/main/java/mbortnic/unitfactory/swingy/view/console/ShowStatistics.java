package mbortnic.unitfactory.swingy.view.console;

import mbortnic.unitfactory.swingy.model.Hero.Player;

import javax.swing.text.PlainView;
import java.util.Scanner;

/**
 * Created by mbortnic on 2/9/19.
 */
public class ShowStatistics {

    public static int showInfo(long ch, String hero, Player player) {
        char eol = '\n';
        System.out.println("Welcome to SWINGY!\n\n" + player + ", your statistics: ");

        if (ch == 1) {
            System.out.println("Your choice is ORC: " + eol);
            System.out.println("Lvl: " + player.getHeroStatistics().getLvl() + eol + "Attack: " + player.getHeroStatistics().getAttack() + eol +
                    "Protection: " + player.getHeroStatistics().getProtection() + eol + "Exp: " + player.getHeroStatistics().getExp() + eol +
                    "Hit Points: " + player.getHeroStatistics().getHitp() + eol + eol);
            chooseStartExit();
        } else if (ch == 2) {
            System.out.println("Your choice is DarkElf: " + eol);
            System.out.println("Lvl: " + player.getHeroStatistics().getLvl() + eol + "Attack: " + player.getHeroStatistics().getAttack() + eol +
                    "Protection: " + player.getHeroStatistics().getProtection() + eol + "Exp: " + player.getHeroStatistics().getExp() + eol +
                    "Hit Points: " + player.getHeroStatistics().getHitp() + eol + eol);
            chooseStartExit();
        }
        int check = 0;
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if (str.matches("\\s*[1-2]\\s*")) {
                check = Integer.parseInt(str);
                break;
            } else {
                System.out.println("Choose from given options.");
            }
        }
        return check;
    }

    public static void chooseStartExit() {
        System.out.println("1. Start swingy.\n2. Exit swingy.");
    }

}
