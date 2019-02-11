package mbortnic.unitfactory.swingy.view.console;

import java.util.Scanner;

/**
 * Created by mbortnic on 2/9/19.
 */
public class DisplayPlayer extends ShowStatistics{

    public static String greetPlayer() {
        System.out.println("Enter your hero name to continue: \n");
        String hero = null;
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            hero = scanner.nextLine();
            hero = hero.trim();
            if (hero.length() > 0) {
                String[] ch = hero.split("\\s");

                if (ch != null) {
                    hero = String.join("_", ch);
                    break ;
                }
            } else {
                System.out.println("Enter name!");
            }
        }
        return hero;
    }

    public static int setUpHero() {
        System.out.println("     SWINGY     \n\n");
        System.out.println("1. Create your hero.\n2. Select your hero.\n");

        int check = 0;

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if (str.matches("\\s*[1-2]\\s*")) {
                check = Integer.parseInt(str);
                break ;
            } else {
                System.out.println("Enter 1 or 2!");
            }
        }
        return check;
    }

    public static int printHeroList() {
        System.out.println("Your race is: \n");
        System.out.println("1. Human.\n2. Undead.\n");
        int check = 0;
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if (str.matches("\\s*[1-2]\\s*")) {
                check = Integer.parseInt(str);
                break ;
            } else {
                System.out.println("Enter 1 or 2!");
            }
        }
        return check;
    }

    public static void showAvailableDirections() {
        System.out.println("\n     Directions:     \n");
        System.out.println("1. North.\n2. South.\n3. West.\n4. East.\n5. Exit swingy.\n");
    }

}
