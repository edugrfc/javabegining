package ru.grfc.edu.vgviewer.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 * Ромб
 *
 * Блок ветвления
 *
 * @author dds
 */
public class Rhombus extends RectangleFig {

    public Rhombus(int width, Coordinate firstPoint, Color color, boolean fill) {
        //у ромба стороны равны
        super(width, width, firstPoint, color, fill);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);

        //вычислим остальные три точки параллелограма
        Coordinate secondPoint = new Coordinate(firstPoint.getX(), firstPoint.getY() + width / 2);
        Coordinate thirdPoint = new Coordinate(firstPoint.getX() - width / 2, firstPoint.getY() + width / 4);
        Coordinate fourthPoint = new Coordinate(firstPoint.getX() + width / 2, firstPoint.getY() + width / 4);
        int[] xps = {firstPoint.getX(), fourthPoint.getX(), secondPoint.getX(), thirdPoint.getX()};
        int[] yps = {firstPoint.getY(), fourthPoint.getY(), secondPoint.getY(), thirdPoint.getY()};
        if (fill) {
            g.fillPolygon(xps, yps, 4);
        } else {
            g.drawPolygon(xps, yps, 4);
        }

    }

    @Override
    public String getName() {
        return "Ромб";
    }

}
