package ru.grfc.edu.awthello;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author mada
 */
//base class figure. every figure stored as set of coordinates
public class Figure {

    protected ArrayList<Pair> figure;
    protected Print printer;

    public void print(Graphics g) {

        printer.print(figure, g);

    }

}
//Oval realization. Oval stored in ArrayList figure as set of 3 coordinates

class Oval extends Figure {

    private int height;
    private int width;
    private Pair center;

    //this function transform parameters to set of coordinates 
    //inside of ArrayList figure
    private void listGenerating() {
        figure = new ArrayList(3);
        //adding 3 basic points to figure
        //top left corner 
        figure.add(new Pair(center.getX() - width, center.getY() - height));
        //the most left point of oval
        figure.add(new Pair(center.getX() - width, center.getY()));
        //the most top point of oval
        figure.add(new Pair(center.getX(), center.getY() - height));

    }

    //constructor which use coordinates of center and width/height
    // to describe oval
    Oval(int x, int y, int h, int w) {

        //basic parameters for ovoid - coordinates of center, heith and width
        this.height = h / 2;
        this.width = w / 2;
        this.center = new Pair(x, y);
        listGenerating();
        printer = new PrintOval();

    }

    //this constructor randomly generates Oval parameters.
    //The result fully lies inside of Container
    Oval(Container c) {

        generateOval(c);
        listGenerating();
        printer = new PrintOval();
    }

    public void generateOval(Container c) {

        Insets bounds = c.getInsets();
        int maxW = c.getWidth() - bounds.left - bounds.right - 1;
        int maxH = c.getHeight() - bounds.bottom - bounds.top - 1;

        Random rand = new Random();
        height = rand.nextInt(maxH / 4 - 21) + 20;
        width = rand.nextInt(maxW / 4 - 21) + 20;
        center = new Pair(rand.nextInt(maxW - 2 * width - 1) + width, rand.nextInt(maxH - 2 * height - 1) + height);

    }

}

//this class describes Line fugure. Line stored in ArrayList figure 
//as set of 2 coordinates
class Line extends Figure {

    Pair start;
    Pair end;

    //this constructor based on 2 coordinates 
    Line(Pair s, Pair e) {

        start = s;
        end = e;
        listGenerating();
        printer = new PrintPolygon();
    }

    //this constructor randomly generates line parameters. 
    //The result fully lies inside of Container form
    Line(Container c) {

        Insets bounds = c.getInsets();
        int maxW = c.getWidth() - bounds.left - bounds.right - 1;
        int maxH = c.getHeight() - bounds.bottom - bounds.top - 1;
        Random rand = new Random();
        start = new Pair(rand.nextInt(maxW), rand.nextInt(maxH));
        end = new Pair(rand.nextInt(maxW), rand.nextInt(maxH));
        listGenerating();
        printer = new PrintPolygon();
    }

    //puts coordinates to ArrayList figure
    private void listGenerating() {

        figure = new ArrayList(2);
        figure.add(start);
        figure.add(end);

    }

}

//Class Polygon describes literally any set of coordinates, connected by lines
class Polygon extends Figure {

    //constructor based on set of coordinates
    Polygon(ArrayList<Pair> figure) {
        this.figure = figure;
        printer = new PrintPolygon();
    }

    //randomly generates polygon
    //the result fully lies in container form
    Polygon(Container c) {

        Random rand = new Random();
        int index = rand.nextInt(3) + 3;

        Insets bounds = c.getInsets();
        int maxW = c.getWidth() - bounds.left - bounds.right - 1;
        int maxH = c.getHeight() - bounds.bottom - bounds.top - 1;

        figure = new ArrayList();

        for (int i = 0; i < index; ++i) {
            figure.add(i, new Pair(rand.nextInt(maxW), rand.nextInt(maxH)));
        }

        printer = new PrintPolygon();
    }

}
//class describes rectangle. stored in arraylist figure as set of 4 points
//generation of rectangles is diffirent from other Polygon objects 
//so there is separated class for them

class Rectangle extends Figure {

    private Pair leftTop;
    private int width;
    private int height;

    //constructor based on coordinates of top left corner and width/height
    Rectangle(Pair start, int w, int h) {
        leftTop = start;
        width = w;
        height = h;
        listGenerating();
        printer = new PrintPolygon();

    }

    //transforms parameters into coordinates
    private void listGenerating() {

        figure = new ArrayList();
        figure.add(0, leftTop);
        figure.add(1, new Pair(leftTop.getX(), leftTop.getY() + height));
        figure.add(2, new Pair(leftTop.getX() + width, leftTop.getY() + height));
        figure.add(3, new Pair(leftTop.getX() + width, leftTop.getY()));

    }

    //randomly generates rectangle parameters
    //the result lies inside of container form
    Rectangle(Container c) {

        Insets bounds = c.getInsets();
        int maxW = c.getWidth() - bounds.left - bounds.right - 1;
        int maxH = c.getHeight() - bounds.bottom - bounds.top - 1;

        Random rand = new Random();

        width = rand.nextInt(maxW / 4);
        height = rand.nextInt(maxH / 4);
        leftTop = new Pair(rand.nextInt(maxW - 2 * width) + width, rand.nextInt(maxH - 2 * height) + height);
        listGenerating();
        printer = new PrintPolygon();

    }

}
