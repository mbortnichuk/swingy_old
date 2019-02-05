package mbortnic.unitfactory.swingy.reader;

import mbortnic.unitfactory.swingy.model.Hero.Player;

import java.io.*;
import java.nio.Buffer;

/**
 * Created by mbortnic on 2/4/19.
 */
public class ReadFromFile{

    public static int getLinesInFile() {
        try {
            File f = new File("PlayerList.txt");
            FileReader fileReader = new FileReader(f);
            LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
            lineNumberReader.skip(Long.MAX_VALUE);
            int counter = lineNumberReader.getLineNumber();
            lineNumberReader.close();
            return (counter);
        } catch (IOException exception) {
            exception.getMessage();
        }
        return (-1);
    }

    public static String[] readLineFromFile() {
        try {
            String str = null;
            String elements[] = new String[getLinesInFile()];
            int ind = 0;
            File f = new File("PlayerList.txt");
            FileReader fileReader = new FileReader(f);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((str = bufferedReader.readLine()) != null) {
                elements[ind] = str;
                ind++;
            }
            bufferedReader.close();
            return elements;
        } catch (IOException exception) {
            exception.getMessage();
        }
        return null;
    }

    public static void getBaseOfPlayers() {
        String[] elements;
        int ind = 0;
        int count = 1;
        elements = readLineFromFile();
        System.out.println("\033[H\033[2J");
        System.out.flush();
        System.out.println("     Choose your player     \n\n");
        while (ind < getLinesInFile()) {
            System.out.println(count++ + ". " + elements[ind++]);
        }
    }

    public static String getPlayer(long player) {
        String[] elements = new String[getLinesInFile()];
        elements = readLineFromFile();
        return (elements[(int)player - 1]);
    }

    public static void refreshFile(Player player) {
        try {
            String[] elements = readLineFromFile();
            String delStr = null;
            String newStr = null;
            File f = new File("PlayerList.txt");
            FileWriter fileWriter = new FileWriter(f);
            for (String str : elements) {
                if (str.contains(player.getNewPlayer()) && str.contains(player.getHeroStatistics().getPlayerType())) {
                    delStr = str;
                }
            }


            newStr = (player.getHeroStatistics().getPlayerType() + " " +
                    player.getNewPlayer() + " " +
                    Integer.toString(player.getHeroStatistics().getLvl()) + " " +
                    Integer.toString(player.getHeroStatistics().getAttack()) + " " +
                    Integer.toString(player.getHeroStatistics().getProtection()) + " " +
                    Integer.toString(player.getHeroStatistics().getHitp()) + " " +
                    Integer.toString(player.getHeroStatistics().getExp()) + " " +
                    player.getArt().getArtType().toUpperCase());

            if (newStr!= null) {
                for (String str : elements) {
                    if (str.equals(delStr)) {
                        fileWriter.write(newStr + "\n");
                    } else {
                        fileWriter.write(str + "\n");
                    }
                }
            }
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println("Error while updating hero statisctics: " + exception);
        }

    }



}
