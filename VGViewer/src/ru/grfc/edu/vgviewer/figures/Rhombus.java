package ru.grfc.edu.vgviewer.figures;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Ромб
 *
 * Блок ветвления
 *
 * @author dds
 */
public class Rhombus extends Rectangle {

    public Rhombus(int width, Coordinate firstPoint, Color color, boolean fill, String text, Coordinate textPoint) {
        //у ромба стороны равны
        super(width, width, firstPoint, color, fill, text, textPoint);
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
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString(text, textPoint.getX(), textPoint.getY());
    }

    @Override
    public String getName() {
        return "Ромб";
    }
}
