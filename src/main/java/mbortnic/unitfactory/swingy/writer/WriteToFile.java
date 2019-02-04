package mbortnic.unitfactory.swingy.writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by mbortnic on 2/4/19.
 */
public class WriteToFile {

    public WriteToFile() {

    }

    public static File f = null;
    public static FileWriter fileWriter;

    public static void create() {
        try {
            if (f == null) {
                f = new File("PlayerList.txt");
                f.createNewFile();
            }
            fileWriter = new FileWriter(f, true);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void close() {
        try {
            if (fileWriter != null) {
                fileWriter.close();
            }
        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void write(String line) {
        try {
            f = new File("PlayerList.txt");
            fileWriter = new FileWriter(f, true);
            fileWriter.append(line);
            fileWriter.append('\n');
            fileWriter.close();
            System.out.println(line);
        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }

}
