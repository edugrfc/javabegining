package ru.grfc.edu.vgviewer;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author mada
 */
//ArrayList of Figure objects with basic Arraylist function
public class FigureList {

    private ArrayList<Figure> fig;

    public void print(Graphics g) {

        for (int i = 0; i < fig.size(); ++i) {
            fig.get(i).print(g);
        }

    }

    public void add(Figure f) {

        fig.add(f);

    }

    public void remove(int i) {

        fig.remove(i);

    }

    public Figure get(int i) throws Exception {

        if (i >= fig.size()) {
            throw new Exception("index out of range");
        }

        return fig.get(i);

    }

    FigureList() {

        fig = new ArrayList();

    }

    FigureList(ArrayList<Figure> f) {

        fig = f;

    }

}
