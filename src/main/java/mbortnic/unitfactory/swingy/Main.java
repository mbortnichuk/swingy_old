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

