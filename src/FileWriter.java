import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {

    public static void writeFile(StringBuilder inputString)  {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("file.xmi");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            out.write(inputString.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
