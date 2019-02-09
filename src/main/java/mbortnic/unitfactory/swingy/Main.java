package mbortnic.unitfactory.swingy;

import mbortnic.unitfactory.swingy.view.gui.GUI;
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



//if (this.level > hero.getHeroStats().getLevel()) {
//        hero.getHeroStats().setLevel(this.level);
//        ReadFile.updateFile(hero);
//
//        System.out.println("You have defeated the Villian\n\n" +
//        "1. continue playing this game\n" +
//        "2. Quit game\n");
//
//        Scanner scanner = new Scanner(System.in);
//
//        while (scanner.hasNextLine()) {
//        String line = scanner.nextLine();
//
//        if (line.matches("\\s*[1-2]\\s*")) {
//        int option = Integer.parseInt(line);
//
//        if (option == 1) {
//        enemyArray.removeAll(enemyArray);
//        GameController.run(hero);
////                        System.out.println("Continue to play game.");
//        } else if (option == 2) {
//        System.out.println("*****Thanks for taking the time to play this game...Until next time*****\n\n\n");
//        System.exit(0);
//        }
//        } else
//        System.out.println("Invalid input. Please select any of the given options");
//        }
//        } else if (this.level == hero.getHeroStats().getLevel()) {
//        enemyArray.removeAll(enemyArray);
//        }




