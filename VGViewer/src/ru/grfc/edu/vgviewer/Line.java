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
public class Line extends GeomPrimitive {

    private final int x1;
    private final int y1;

    public Line(int x, int y, int x1, int y1, Color color) {
        super(x, y, color);
        this.x1 = x1;
        this.y1 = y1;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color); // setting color of the line
        g.drawLine(x, y, x1 - 1, y1 - 1); // drawing the line
    }
}
