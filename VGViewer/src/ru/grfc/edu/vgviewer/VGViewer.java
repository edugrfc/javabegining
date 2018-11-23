/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.grfc.edu.vgviewer;

import java.awt.*;

/**
 * Главный класс для запуска вьювера векторной графики
 *
 * @author nag
 */
public class VGViewer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Frame frame = new Frame("Random Frame");
        frame.addWindowListener(new MyController(frame));
        frame.setSize(500, 500);

        frame.add(new ExibitionCanvas(frame));

        frame.setVisible(true);

        // TODO code application logic here
    }

}
