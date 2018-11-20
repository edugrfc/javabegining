/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.grfc.edu.awthello;

/**
 *
 * @author v.veligorskaya
 */
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.plaf.FontUIResource;



public class EduAwtHello {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Frame myFrame = new Frame();
        myFrame.addWindowListener(new MyWindowAdapter(myFrame));
        myFrame.setSize(500,400);
        myFrame.add(new MyCanvas());
        myFrame.setVisible(true);
    }
    
    private static class MyCanvas extends Canvas {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int width = this.getWidth();
        int height = this.getHeight();
        //cross
        g.setColor(Color.green);
        g.drawLine(0,0,width,height);
        g.drawLine(0,height,width,0);
        //frame
        g.setColor(Color.red);
        g.drawRect(0,0, width - 1, height - 1);
 //       drawFrameAsLines(g);
        //circle
        g.setColor(Color.orange);
        g.drawOval(width/2 - Math.min(width, height)/4,
                height/2 - Math.min(width,height)/4,
                   Math.min(width, height)/2,
                    Math.min(width, height)/2);
        //
        g.setColor(Color.red);
        String helloStr = "Hello GRFC!";
        FontMetrics metrics = g.getFontMetrics();
        int stringWidth = metrics.stringWidth(helloStr);
        int fontCoef = (stringWidth + stringWidth/4)/g.getFont().getSize();
        Font f = new Font("Arial", Font.PLAIN, this.getWidth()/fontCoef);
        g.setFont(f);
        g.drawString(helloStr, this.getWidth()/10, this.getHeight()/2);
    }

    private void drawFrameAsLines(Graphics g) {
        int width = this.getWidth();
        int height = this.getHeight();
        g.drawLine(width - 1,0,width - 1, height - 1);
        g.drawLine(width - 1,height - 1,0, height - 1);
        g.drawLine(0,height - 1,0, 0);
        g.drawRect(0,0, width - 1, height - 1);
    }

}
    
    private static class MyWindowAdapter extends WindowAdapter{
    Frame f;
    MyWindowAdapter(Frame f) {
        this.f = f;
    }
    @Override
    public void windowClosing(WindowEvent e) {
        f.dispose();
    }


}

}
