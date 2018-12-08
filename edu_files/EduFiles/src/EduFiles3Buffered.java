
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EduFiles3Buffered {

    public static void main(String[] args) {
        InputStream in = null;
        OutputStream out = null;

        try {
            in = new BufferedInputStream(new FileInputStream("EduFiles1Std.class"), 1024);
            long startTime = System.currentTimeMillis();
            out = new BufferedOutputStream(new FileOutputStream("EduFiles1Std.bin"), 1024);
            long endTime = System.currentTimeMillis();
            System.out.println(String.format("delay: %7.3f", (endTime - startTime) / 1000.0));
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch (IOException ex) {
            Logger.getLogger(EduFiles3Buffered.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(EduFiles3Buffered.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
