package ru.grfc.edu.vgviewer.figures;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Эллипс, круг
 *
 * В блок-схемах используется только круг в качестве соединителя
 *
 * @author dds
 */
public class Ellipse extends Figure {

    //если ширина равна высоте, то получаем круг
    //ширина эллипса
    private int width;

    //высота эллипса
    private int height;

    public Ellipse(int width, int height, Coordinate firstPoint, Color color, boolean fill) {
        super(firstPoint, color, fill);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (fill) {
            g.fillOval(firstPoint.getX(), firstPoint.getY(), width, height);
        } else {
            g.drawOval(firstPoint.getX(), firstPoint.getY(), width, height);
        }
    }

    @Override
    public String getName() {
        return width == height ? "Круг" : "Эллипс";
    }

}
