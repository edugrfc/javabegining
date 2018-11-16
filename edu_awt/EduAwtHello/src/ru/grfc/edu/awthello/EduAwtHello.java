  
import java.awt.*;

public class project {
    
    
    public static void main(String [] Args){
    
    
    Frame frame = new Frame("Random Frame");
    frame.addWindowListener(new MyController(frame));
    frame.setSize(300, 300);
    
    frame.add(new MyCanvas(frame));
    
    frame.setVisible(true);
    
    }
    
    
    
}
