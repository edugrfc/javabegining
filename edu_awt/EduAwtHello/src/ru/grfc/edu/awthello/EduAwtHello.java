package ru.grfc.edu.awthello;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class EduAwtHello {

    public static void main(String args[]) {
        Frame f = new MainWindow("HW1 Canvas");
        f.add(new FormCanvas());
        f.setSize(500, 300);
        f.setVisible(true);
    }

    public static class MainWindow extends Frame {

        public MainWindow(String formTitle) {
            this.setTitle(formTitle);
            WindowListener listener = new MainWindowListener();
            addWindowListener(listener);
        }

        private class MainWindowListener extends WindowAdapter {

            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }

        }

    }

}

