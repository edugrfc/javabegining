package ru.grfc.edu.vgviewer.figures;

import java.awt.Color;
import java.awt.Font;
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

    public Rectangle(int width, int height, Coordinate firstPoint, Color color, boolean fill, String text, Coordinate textPoint) {
        super(firstPoint, color, fill, text, textPoint);
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
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString(text, textPoint.getX(), textPoint.getY());
    }

    @Override
    public String getName() {
        return width == height ? "Квадрат" : "Прямоугольник";
    }
}
