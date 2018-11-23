package ru.grfc.edu.vgviewer;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author mada
 */
//new canvas for drawning list of figures
public class ExibitionCanvas extends Canvas {

    Frame frame;
    FigureList figures;

    ExibitionCanvas(Frame frame, FigureList figures) {
        super();
        this.frame = frame;
        this.figures = figures;

    }

    public void paint(Graphics g) {

        super.paint(g);
        fillFigures();
        figures.print(g);

    }
    
    private void fillFigures(){
    
        figures = new FigureList();
        figures.add(new Oval(frame));
        figures.add(new Rectangle(frame));
        figures.add(new Oval(frame));
        figures.add(new Polygon(frame));
        figures.add(new Oval(frame));
        figures.add(new Rectangle(frame));
        figures.add(new Line(frame));
        figures.add(new Oval(frame));
    
    }

    ExibitionCanvas(Frame frame) {

        this.frame = frame;
    }

    
    
}
