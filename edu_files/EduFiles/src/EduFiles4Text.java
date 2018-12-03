
import java.io.*;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EduFiles4Text {

    public static void main(String[] args) {
        try ( FileReader inputStream = new FileReader("in.txt"); /*, Charset.forName("UTF-8")*/  
                FileWriter outputStream = new FileWriter("out.txt");) {

            int c;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
            }
        } catch (IOException ex) {
            Logger.getLogger(EduFiles4Text.class.getName()).log(Level.SEVERE, null, ex);
        }
        // interface java.lang.AutoCloseable
        // jad
    }

}
