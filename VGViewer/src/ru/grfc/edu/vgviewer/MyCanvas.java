/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.grfc.edu.vgviewer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chvl
 */
public class MyCanvas extends Canvas {

    @Override
    public void paint(Graphics g) {
        List<GeomPrimitive> figuresList = new ArrayList<>();
        
        // creating geometrical primitives
        Rectangle rec = new Rectangle((getWidth() - 1) / 2 - 60, (getHeight() - 1) / 2 - 60, 120, 120, Color.GREEN);
        Line line = new Line(0, 0, getWidth() -1,getHeight() -1, Color.RED);
        Circle circle = new Circle((getWidth() - 1) / 2 - 100, (getHeight() - 1) / 2 - 100, 100, Color.BLUE);
        
        // adding geometrical primitives into list 
        figuresList.add(rec);
        figuresList.add(line);
        figuresList.add(circle);
    
        // drawing all geometrical primitives from list       
        for (GeomPrimitive figure : figuresList) {
            figure.draw(g);
        }
    }
}
