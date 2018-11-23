package ru.grfc.edu.vgviewer;
/**
 *
 * @author mada
 */
//class describes pairs of intergers
//further used to store coordinates of class Figure
public class Pair {

    private int x;
    private int y;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void print() {

        System.out.println("X= " + x + " Y= " + y);

    }

}
