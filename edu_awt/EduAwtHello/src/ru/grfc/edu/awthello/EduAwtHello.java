/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.grfc.edu.awthello;

import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Насоновы
 */
public class EduAwtHello {

    public static class MainWindow extends Frame {

        public MainWindow() {
            WindowListener listeren = new MainWindowListeren();
            addWindowListener(listeren);
        }

        public class MainWindowListeren extends WindowAdapter {

            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        }
    }

    public static class MyCanvas extends Canvas {

        @Override
        public void paint(Graphics g) {
            int width = getWidth();
            int height = getHeight();
            g.setColor(Color.RED);
            g.drawRect(0, 0, width - 1, height - 1);
            g.setColor(Color.GREEN);
            g.drawLine(0, 0, width - 1, height - 1);
            g.drawLine(width - 1, 0, 0, height - 1);
            g.setColor(Color.MAGENTA);
            g.fillArc(((width -1) / 2 - Math.min(height, width) / 4), ((height -1) / 2 - Math.min(height, width) / 4), Math.min(height, width) / 2, Math.min(height, width) / 2, 0, 360);
            g.setColor(Color.YELLOW);
            int fontSize = 10;
            g.setFont(new Font("Times", 1, fontSize));
            FontMetrics fm = g.getFontMetrics();
            int tWidth = fm.stringWidth("Hello, ГРЧЦ!");
            fontSize = (int) (fontSize * 0.8 * width / tWidth);
            g.setFont(new Font("Times", 1, fontSize));
            fm = g.getFontMetrics();
            tWidth = fm.stringWidth("Hello, ГРЧЦ!");
            g.drawString("Hello, ГРЧЦ!", (((width -1) / 2) - (tWidth / 2)), (((height - 1) / 2 ) + (fontSize / 3)));
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hello, GRFC!");
        Frame f = new MainWindow();
        f.setSize(300, 300);
        f.setVisible(true);
        f.add(new MyCanvas());
    }
}
