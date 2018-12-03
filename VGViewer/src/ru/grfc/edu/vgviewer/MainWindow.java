/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.grfc.edu.vgviewer;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author win
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
