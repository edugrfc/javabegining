package ru.grfc.edu.vgviewer.figures;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Параллелограм
 *
 * Блок для ввода-вывода данных
 *
 * @author dds
 */
public class Parallelogram extends Rectangle {

    //тупой или острый левый верхний угол, true - тупой
    private boolean isBlunt;

    public Parallelogram(boolean isBlunt, int width, int height, Coordinate firstPoint, Color color, boolean fill, String text, Coordinate textPoint) {
        super(width, height, firstPoint, color, fill, text, textPoint);
        this.isBlunt = isBlunt;
    }

    public boolean isIsBlunt() {
        return isBlunt;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);

        //вычислим остальные три точки параллелограма
        //для простоты угол всегда делаем исходя из того, что второй катет - треть ширины
        int leg;
        if (isBlunt) {
            leg = -width / 3;
        } else {
            leg = width / 3;
        }
        Coordinate secondPoint = new Coordinate(firstPoint.getX() + width, firstPoint.getY());
        Coordinate thirdPoint = new Coordinate(firstPoint.getX() + leg, firstPoint.getY() + height);
        Coordinate fourthPoint = new Coordinate(firstPoint.getX() + width + leg, firstPoint.getY() + height);
        int[] xps = {firstPoint.getX(), secondPoint.getX(), fourthPoint.getX(), thirdPoint.getX()};
        int[] yps = {firstPoint.getY(), secondPoint.getY(), fourthPoint.getY(), thirdPoint.getY()};
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
        return "Параллелограм";
    }

}
