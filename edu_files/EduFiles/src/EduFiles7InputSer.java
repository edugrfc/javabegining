
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nag
 */
public class EduFiles7InputSer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String dataFile = "object.bin";
        //ObjectOutputStream
        //ObjectInputStream
        
        try ( ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)));) {

            double total = 0.0;

            try {
                while (true) {
                    Order order = (Order) in.readObject();
                    System.out.format("You ordered %d" + " units of %s at $%.2f%n",
                            order.unit, order.descr, order.price);
                    total += order.unit * order.price;
                }
            } catch (EOFException e) {
            }
            System.out.format("Total: " + " $%.2f%n", total);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EduFiles7InputSer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EduFiles6Serializable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
