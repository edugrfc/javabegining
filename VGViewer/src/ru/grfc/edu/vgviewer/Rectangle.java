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
public class Rectangle extends GeomPrimitive {

    private final int width;
    private final int height;

    public Rectangle(int x, int y, int width, int height, Color color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color); // setting color lines of the rectangle
        g.drawRect(x, y, width - 1, height - 1); // drawing the rectangle
    }

}
