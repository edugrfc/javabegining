package ru.grfc.edu.awthello;

import java.awt.event.*;
import java.awt.*;

/**
 *
 * @author mada
 */
//simple controller function for frames, closes window after [x] button is pressed
public class MyController extends WindowAdapter {

    Frame frame;

    MyController(Frame e) {

        this.frame = e;

    }

    @Override
    public void windowClosing(WindowEvent e) {
        super.windowClosing(e); //To change body of generated methods, choose Tools | Templates.
        frame.dispose();

    }

    @Override
    public void windowClosed(WindowEvent e) {
        super.windowClosed(e); //To change body of generated methods, choose Tools | Templates.
        frame.dispose();
    }

}
