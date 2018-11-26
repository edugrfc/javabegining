/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.grfc.edu.vgviewer;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author chvl
 */
public class Circle extends GeomPrimitive {

    private final int radius;

    public Circle(int x, int y, int radius, Color color) {
        super(x, y, color);
        this.radius = radius;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color); // setting color lines of the circle
        g.drawArc(x, y, 2*radius, 2*radius, 0, 360);// drawing the circle
    }
}
