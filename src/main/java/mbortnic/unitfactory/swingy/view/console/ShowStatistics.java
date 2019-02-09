package mbortnic.unitfactory.swingy.view.console;

import mbortnic.unitfactory.swingy.model.Hero.Player;

import javax.swing.text.PlainView;

/**
 * Created by mbortnic on 2/9/19.
 */
public class ShowStatistics {



    public static int showInfo(long ch, String hero, Player player) {
        char eol = '\n';
        System.out.println("Welcome to SWINGY!\n\n" + player + ", your statistics: ");

        if (ch == 1) {
            System.out.println("Your choice is ORC: \n");
            System.out.println("Lvl: " + player.getHeroStatistics().getLvl() + eol + );
        }
    }

}
