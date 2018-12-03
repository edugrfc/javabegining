import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EduFiles2CopyByte {
    
    public static void main(String[] args) {
        try {
            FileInputStream in = new FileInputStream("EduFiles1Std.class");
            
            try {
                FileOutputStream out = new FileOutputStream("EduFiles1Std.bin");

                int c;
                while ((c = in.read()) != -1) {
                    out.write(c);
                }
            } catch (IOException ex) {
                Logger.getLogger(EduFiles2CopyByte.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                //out.close();
            }        
        } catch (IOException ex) {
            Logger.getLogger(EduFiles2CopyByte.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //in.close();
        }        
    }

}
