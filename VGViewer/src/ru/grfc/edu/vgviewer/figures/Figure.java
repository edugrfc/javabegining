package ru.grfc.edu.vgviewer.figures;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Абстрактый класс фигуры
 *
 * @author dds
 */
abstract public class Figure {

    //начальная координата фигуры (левый верххний угол)
    Coordinate firstPoint;

    //цвет контура фигуры
    Color color;

    //наличие заливки
    boolean fill;

    public Figure(Coordinate firstPoint, Color color, boolean fill) {
        this.firstPoint = firstPoint;
        this.color = color;
        this.fill = fill;
    }

    public Coordinate getFirstPoint() {
        return firstPoint;
    }

    public Color getColor() {
        return color;
    }

    //метод, который отрисовывает фигуру
    public abstract void draw(Graphics g);

    //имя фигуры
    public abstract String getName();

}
