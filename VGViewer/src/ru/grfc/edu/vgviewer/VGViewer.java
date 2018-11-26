/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.grfc.edu.vgviewer;

import java.awt.Frame;

/**
 *
 * @author chvl
 */
public class VGViewer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    
        Frame f = new MainWindow();
        f.setSize(300, 300);
        f.setVisible(true);
        f.add(new MyCanvas());
    }

}