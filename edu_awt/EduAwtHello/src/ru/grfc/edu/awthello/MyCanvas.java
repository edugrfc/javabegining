
import java.awt.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mada
 */
public class MyCanvas extends Canvas{
    
    Frame frame;

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        
        Insets ins = frame.getInsets();
        
        
        int w=frame.getWidth()-ins.left -ins.right-1;
        int h=frame.getHeight()-ins.bottom-ins.top-1;
        
        //diagonals
        g.setColor(Color.green);
        g.drawLine(0, 0, w, h);
        g.drawLine(w, 0, 0, h);
        
        //rectangle
        g.setColor(Color.red);
        g.drawLine(0, 0, w, 0);
        g.drawLine(0, 0, 0, h);
        g.drawLine(w, 0, w, h);
        g.drawLine(0, h, w, h);
        
        
        //circle
        g.setColor(Color.black);
        int min;
        if (w >= h) min=h;
        else min = w;
        min/=2;     
        g.drawOval((w-min)/2,(h- min)/2, min,min);
        
        //message
        String str="Random message";
        int size = g.getFontMetrics().stringWidth(str);
        double mult = 1.1*(double)w/(double)size;
        float font= g.getFont().getSize()*(float)mult;      
        g.setFont(new Font ("Calibri",Font.BOLD,(int)font));       
        g.drawString(str, (w-g.getFontMetrics().stringWidth(str))/2, (h+g.getFontMetrics().getHeight()/2)/2);
        
        
        
        
        
        
    
        
        
    }
    
    
    
    MyCanvas(Frame e){
        
        super();
        this.frame=e;
    
    
    }
    
    
    
    
    
}

