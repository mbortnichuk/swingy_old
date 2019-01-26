package mbortnic.unitfactory.swingy;

import mbortnic.unitfactory.swingy.view.GUI;

/**
 * Created by mbortnic on 1/25/19.
 */
public class Main {

    public static void main(String[] args) {

        try {
//            WriteToFile.createFile();

            if (args.length != 1) {
                System.out.println("Usage: java -jar swingy.jar [gui/console]");
                System.exit(1);
            }

            if (args[0].equals("console")) {
//                Console.show();
            } else {
                GUI gui = new GUI();
//                gui.show();
            }
        } finally {
//            WriteToFile.closeFile();
        }

    }

}
