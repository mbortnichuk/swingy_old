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


//    public static void run(Hero hero) { //
//        ConsoleMap printMap = new ConsoleMap(hero);
//
//        printMap.printMap();
//        HeroDisplay.printDirections();
//        Console console = System.console();
//        while (true) {
//            String line = console.readLine();
//
//
//            if (line.matches("\\s*[1-5]\\s*")) {
//                int direction = Integer.parseInt(line);
//
//                if (direction == 1) {
//                    printMap.updateHeroPosition(1, 0);
//                    printMap.printMap();
//                    HeroDisplay.printDirections();
//                } else if (direction == 2) {
//                    printMap.updateHeroPosition(0, 1);
//                    printMap.printMap();
//                    HeroDisplay.printDirections();
//                } else if (direction == 3) {
//                    printMap.updateHeroPosition(-1, 0);
//                    printMap.printMap();
//                    HeroDisplay.printDirections();
//                }
//                else if (direction == 4) {
//                    printMap.updateHeroPosition(0, -1);
//                    printMap.printMap();
//                    HeroDisplay.printDirections();
//                }
//                else if(direction == 5)
//                {
//                    System.exit(0);
//                }
//            }
//            else {
//                System.out.println("invalid input");
//
//            }
//        }
//    }




