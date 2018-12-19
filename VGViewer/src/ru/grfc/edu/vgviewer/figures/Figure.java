package ru.grfc.edu.vgviewer.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * Абстрактый класс фигуры
 *
 * @author dds
 */
abstract public class Figure implements Serializable {

    //начальная координата фигуры (левый верхний угол)
    Coordinate firstPoint;

    //цвет контура фигуры
    Color color;

    //наличие заливки
    boolean fill;

    // Надпись
    String text;

    // Координата надписи
    Coordinate textPoint;

    public Figure(Coordinate firstPoint, Color color, boolean fill, String text, Coordinate textPoint) {
        this.firstPoint = firstPoint;
        this.color = color;
        this.fill = fill;
        this.text = text;
        this.textPoint = textPoint;
    }

    public Figure() {
    }

    public Coordinate getFirstPoint() {
        return firstPoint;
    }

    public Color getColor() {
        return color;
    }

    public boolean isFill() {
        return fill;
    }

    public String getText() {
        return text;
    }

    public Coordinate getTextPoint() {
        return textPoint;
    }

    //метод, который отрисовывает фигуру
    public abstract void draw(Graphics g);

    //имя фигуры
    public abstract String getName();
}
