
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EduFiles5Data {

    static final String[] descrs = {
        "Java T-shirt",
        "Java Mug",
        "Duke Juggling Dolls",
        "Java Pin",
        "Java Key Chain"
    };
    static final double[] prices = {19.99, 9.99, 15.99, 3.99, 4.99};
    static final int[] units = {12, 8, 13, 29, 50};

    public static void main(String[] args) {
        String dataFile = "data.bin";

        try ( DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)));) {
            for (int i = 0; i < prices.length; i++) {
                out.writeUTF(descrs[i]);
                out.writeDouble(prices[i]);
                out.writeInt(units[i]);
            }
        } catch (IOException ex) {
            Logger.getLogger(EduFiles5Data.class.getName()).log(Level.SEVERE, null, ex);
        }

        try ( DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(dataFile)));) {

            String descr;
            double price;
            int unit;
            double total = 0.0;

            try {
                while (true) {
                    descr = in.readUTF();
                    price = in.readDouble();
                    unit = in.readInt();
                    System.out.format("You ordered %d" + " units of %s at $%.2f%n",
                            unit, descr, price);
                    total += unit * price;
                }
            } catch (EOFException e) {
            }
            System.out.format("Total: " + " $%.2f%n", total);
            
        } catch (IOException ex) {
            Logger.getLogger(EduFiles5Data.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
