package ru.grfc.edu.vgviewer;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import ru.grfc.edu.vgviewer.figures.Coordinate;
import ru.grfc.edu.vgviewer.figures.Figure;
import ru.grfc.edu.vgviewer.figures.Line;
import ru.grfc.edu.vgviewer.figures.Parallelogram;
import ru.grfc.edu.vgviewer.figures.Rectangle;
import ru.grfc.edu.vgviewer.figures.Rhombus;
import ru.grfc.edu.vgviewer.figures.RoundRectangle;

/**
 * Главный класс для запуска вьювера векторной графики
 *
 * @author dds
 */
public class VGViewer {

    public static void main(String[] args) {

        
        ArrayList<Figure> figures = new ArrayList();

        Frame frame = new Frame();
        frame.setLayout(new BorderLayout());
        frame.setSize(500, 500);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
        
        Panel panel = new Panel(new FlowLayout(FlowLayout.LEFT));
        MyCanvas canvas = new MyCanvas(figures);
        MyChoice choice = new MyChoice();
        TextField text = new TextField(20);
        MyButton button = new MyButton("Добавить", choice, text, figures, canvas);
        MyLabel label = new MyLabel(" ", choice, text, button, frame);

        panel.add(choice);
        panel.add(text);
        panel.add(label);
        panel.add(button);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(canvas, BorderLayout.CENTER);
        frame.setVisible(true);
        
    }

    private static class MyCanvas extends Canvas {

        private ArrayList<Figure> figures;

        public void setFigures(ArrayList<Figure> figures) {
            this.figures = figures;
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (Figure figure : figures) {
                figure.draw(g);
            }
        }

        MyCanvas(ArrayList<Figure> list) {

            super();
            this.figures = list;

        }
    }

    private static ArrayList<Figure> generateFigures() {

        ArrayList<Figure> figures = new ArrayList<>();
        int width = 300;

        //для примера нарисуем что-то похожее на блок-схему
        //терминатор начала
        RoundRectangle rr = new RoundRectangle(70, 30, 20, 20,
                new Coordinate((width / 2) - 30, 10), Color.RED, false);
        figures.add(rr);
        Line line1 = new Line(new Coordinate(rr.getFirstPoint().getX() + rr.getWidth() / 2,
                10 + rr.getHeight()), new Coordinate(rr.getFirstPoint().getX() + rr.getWidth() / 2,
                10 + rr.getHeight() + 30), Color.red);
        figures.add(line1);

        //блок ввода
        Parallelogram p = new Parallelogram(true, rr.getWidth(), rr.getHeight(),
                new Coordinate(rr.getFirstPoint().getX() + rr.getWidth() / 10,
                        line1.getLastPoint().getY()), Color.red, false);
        figures.add(p);
        Line line2 = new Line(new Coordinate(line1.getFirstPoint().getX(),
                line1.getLastPoint().getY() + p.getHeight()),
                new Coordinate(line1.getFirstPoint().getX(),
                        line1.getLastPoint().getY() + p.getHeight() + 30), Color.red);
        figures.add(line2);

        //блок операций
        Rectangle r = new Rectangle(rr.getWidth(), rr.getHeight(),
                new Coordinate(rr.getFirstPoint().getX(), line2.getLastPoint().getY()),
                Color.red, false);
        figures.add(r);
        Line line3 = new Line(new Coordinate(line1.getFirstPoint().getX(),
                line2.getLastPoint().getY() + r.getHeight()),
                new Coordinate(line1.getFirstPoint().getX(),
                        line2.getLastPoint().getY() + r.getHeight() + 30), Color.red);
        figures.add(line3);

        //блок ветвления, но само ветвление не рисуем
        Rhombus rb = new Rhombus(r.getWidth(), new Coordinate(line3.getLastPoint().getX(),
                line3.getLastPoint().getY()), Color.red, false);
        figures.add(rb);
        Line line4 = new Line(new Coordinate(line1.getFirstPoint().getX(),
                line3.getLastPoint().getY() + rb.getWidth() / 2),
                new Coordinate(line1.getFirstPoint().getX(),
                        line3.getLastPoint().getY() + rb.getWidth() / 2 + 30), Color.red);
        figures.add(line4);

        //блок операций
        Rectangle r2 = new Rectangle(rr.getWidth(), rr.getHeight(),
                new Coordinate(rr.getFirstPoint().getX(), line4.getLastPoint().getY()),
                Color.red, false);
        figures.add(r2);
        Line line5 = new Line(new Coordinate(line1.getFirstPoint().getX(),
                line4.getLastPoint().getY() + r2.getHeight()),
                new Coordinate(line1.getFirstPoint().getX(),
                        line4.getLastPoint().getY() + r2.getHeight() + 30), Color.red);
        figures.add(line5);

        //терминатор конца
        RoundRectangle rr2 = new RoundRectangle(rr.getWidth(), rr.getHeight(), rr.getArcHeight(), rr.getArcWidth(),
                new Coordinate(rr.getFirstPoint().getX(), line5.getLastPoint().getY()), Color.RED, false);
        figures.add(rr2);

        return figures;
    }

}
