package ru.grfc.edu.vgviewer.figures;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Прямоугольник с закругленными краями
 *
 * Блок-терминатор
 *
 * @author dds
 */
public class RoundRectangle extends Rectangle {

    //ширина эллипса
    private int arcWidth;

    //высота эллипса
    private int arcHeight;

    public RoundRectangle(int width, int height, int arcWidth, int arcHeight,
            Coordinate firstPoint, Color color, boolean fill) {
        super(width, height, firstPoint, color, fill);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    public int getArcWidth() {
        return arcWidth;
    }

    public int getArcHeight() {
        return arcHeight;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (fill) {
            g.fillRoundRect(firstPoint.getX(), firstPoint.getY(), width, height,
                    arcWidth, arcHeight);
        } else {
            g.drawRoundRect(firstPoint.getX(), firstPoint.getY(), width, height,
                    arcWidth, arcHeight);
        }
    }

    @Override
    public String getName() {
        return "Прямоугольник с закругленными краями";
    }

}
