package mbortnic.unitfactory.swingy.reader;

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

    

}
