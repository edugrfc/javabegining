package ru.grfc.edu.vgviewer.figures;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Прямоугольник
 *
 * Блок операций для блок-схем
 *
 * @author dds
 */
public class Rectangle extends Figure {

    //ширина
    int width;

    //высота
    int height;

    public Rectangle(int width, int height, Coordinate firstPoint, Color color, boolean fill) {
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
            g.fillRect(firstPoint.getX(), firstPoint.getY(), width, height);
        } else {
            g.drawRect(firstPoint.getX(), firstPoint.getY(), width, height);
        }
    }

    @Override
    public String getName() {
        return width == height ? "Квадрат" : "Прямоугольник";
    }

}
