package ru.grfc.edu.vgviewer.figures;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Линия
 *
 * (пока вместо стрелки)
 *
 * @author dds
 */
public class Line extends Figure {

    //конечная точка линии
    private Coordinate lastPoint;

    public Line(Coordinate firstPoint, Coordinate lastPoint, Color color, String text, Coordinate textPoint) {
        super(firstPoint, color, false, text, textPoint);
        this.lastPoint = lastPoint;
    }

    public Coordinate getLastPoint() {
        return lastPoint;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(firstPoint.getX(), firstPoint.getY(),
                lastPoint.getX(), lastPoint.getY());
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString(text, textPoint.getX(), textPoint.getY());
    }

    @Override
    public String getName() {
        return "Линия";
    }
}
