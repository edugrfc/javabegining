package ru.grfc.edu.vgviewer;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author gsv
 */
public class MainWindow extends Frame {

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
