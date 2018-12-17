package ru.grfc.edu.vgviewer.figures;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Serializable;

/**
 * Класс координаты (x, y)
 *
 * @author chvl
 */
public class Coordinate implements Serializable {

    private int x;
    private int y;

    public Coordinate() {
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
