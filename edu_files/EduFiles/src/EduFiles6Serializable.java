
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EduFiles6Serializable {

    public static class Order implements Serializable {
        String descr;
        double price;
        int unit;

        public Order(String descr, double price, int unit) {
            this.descr = descr;
            this.price = price;
            this.unit = unit;
        }
        
    }

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
        
        
    }
    
}
