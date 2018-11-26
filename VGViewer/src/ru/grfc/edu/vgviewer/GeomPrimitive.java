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
public abstract class GeomPrimitive {
   protected final Color color; // Color of geometrical figure
   protected final int x; // the x coordinate
   protected final int y; // the y coordinate

    public GeomPrimitive(int x, int y, Color color) {
        this.color = color;
        this.x = x;
        this.y = y;
    }
    public abstract void draw(Graphics g);
}
