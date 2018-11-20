package ru.grfc.edu.awthello;
import java.awt.event.*;
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
