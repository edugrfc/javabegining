package ru.grfc.edu.vgviewer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author mada
 */
//basic interface for creation of alternative print functions for diffirent figures
public interface Print {

    public void print(ArrayList<Pair> figure, Graphics g);

}

//this function can be used to print any object of Oval class
class PrintOval implements Print {

    public void print(ArrayList<Pair> figure, Graphics g) {

        g.setColor(Color.MAGENTA);
        g.drawOval(figure.get(0).getX(), figure.get(0).getY(), getWidth(figure), getHeight(figure));

    }

    public int getWidth(ArrayList<Pair> figure) {

        return figure.get(2).getX() - figure.get(0).getX();

    }

    public int getHeight(ArrayList<Pair> figure) {

        return figure.get(1).getY() - figure.get(0).getY();
    }

}

//this fuction allows to connect dots from array with lines
//can be used to print Line, Tinagle, Rectangle or any other Poligon
class PrintPolygon implements Print {

    public void print(ArrayList<Pair> figure, Graphics g) {

        g.setColor(Color.BLUE);

        for (int i = 0; i < figure.size() - 1; ++i) {
            g.drawLine(figure.get(i).getX(), figure.get(i).getY(), figure.get(i + 1).getX(), figure.get(i + 1).getY());
        }

        g.drawLine(figure.get(0).getX(), figure.get(0).getY(), figure.get(figure.size() - 1).getX(), figure.get(figure.size() - 1).getY());

    }

}
