package ru.grfc.edu.vgviewer.figures;

import java.awt.Color;
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

    public Line(Coordinate firstPoint, Coordinate lastPoint, Color color) {
        super(firstPoint, color, false);
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
    }

    @Override
    public String getName() {
        return "Линия";
    }

}
