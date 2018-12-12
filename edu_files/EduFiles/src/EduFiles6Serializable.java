
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EduFiles6Serializable {

    static final List<Order> orders = new ArrayList();
    
    static {
        orders.add(new Order("Java T-shirt", 19.99, 12));
        orders.add(new Order("Java Mug", 9.99, 8));
        orders.add(new Order("Duke Juggling Dolls", 15.99, 13));
        orders.add(new Order("Java Pin", 3.99, 29));
        orders.add(new Order("Java Key Chain",4.99, 50));
    }
    
    public static void main(String[] args) {
        String dataFile = "object.bin";
        //ObjectOutputStream
        //ObjectInputStream
        
        try ( ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)));) {
            for (Order order : orders) {
                out.writeObject(order);
            }
        } catch (IOException ex) {
            Logger.getLogger(EduFiles6Serializable.class.getName()).log(Level.SEVERE, null, ex);
        }

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
                Logger.getLogger(EduFiles6Serializable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EduFiles6Serializable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
