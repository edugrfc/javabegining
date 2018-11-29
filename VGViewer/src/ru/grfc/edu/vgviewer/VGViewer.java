package ru.grfc.edu.vgviewer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import ru.grfc.edu.vgviewer.figures.Coordinate;
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
        Frame frame = new Frame();
        frame.setSize(500, 500);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

        Canvas canvas = new MyCanvas();
        frame.add(canvas);
        frame.setVisible(true);
    }

    private static class MyCanvas extends Canvas {

        @Override
        public void paint(Graphics g) {
            int width = getWidth();
            int height = getHeight();

            //для примера нарисуем что-то похожее на блок-схему
            //терминатор начала
            RoundRectangle rr = new RoundRectangle(70, 30, 20, 20,
                    new Coordinate((width / 2) - 30, 10), Color.RED, false);
            rr.draw(g);
            Line line1 = new Line(new Coordinate(rr.getFirstPoint().getX() + rr.getWidth() / 2,
                    10 + rr.getHeight()), new Coordinate(rr.getFirstPoint().getX() + rr.getWidth() / 2,
                    10 + rr.getHeight() + 30), Color.red);
            line1.draw(g);

            //блок ввода
            Parallelogram p = new Parallelogram(true, rr.getWidth(), rr.getHeight(),
                    new Coordinate(rr.getFirstPoint().getX() + rr.getWidth() / 10,
                            line1.getLastPoint().getY()), Color.red, false);
            p.draw(g);
            Line line2 = new Line(new Coordinate(line1.getFirstPoint().getX(),
                    line1.getLastPoint().getY() + p.getHeight()),
                    new Coordinate(line1.getFirstPoint().getX(),
                            line1.getLastPoint().getY() + p.getHeight() + 30), Color.red);
            line2.draw(g);

            //блок операций
            Rectangle r = new Rectangle(rr.getWidth(), rr.getHeight(),
                    new Coordinate(rr.getFirstPoint().getX(), line2.getLastPoint().getY()),
                    Color.red, false);
            r.draw(g);
            Line line3 = new Line(new Coordinate(line1.getFirstPoint().getX(),
                    line2.getLastPoint().getY() + r.getHeight()),
                    new Coordinate(line1.getFirstPoint().getX(),
                            line2.getLastPoint().getY() + r.getHeight() + 30), Color.red);
            line3.draw(g);

            //блок ветвления, но само ветвление не рисуем
            Rhombus rb = new Rhombus(r.getWidth(), new Coordinate(line3.getLastPoint().getX(),
                    line3.getLastPoint().getY()), Color.red, false);
            rb.draw(g);
            Line line4 = new Line(new Coordinate(line1.getFirstPoint().getX(),
                    line3.getLastPoint().getY() + rb.getWidth() / 2),
                    new Coordinate(line1.getFirstPoint().getX(),
                            line3.getLastPoint().getY() + rb.getWidth() / 2 + 30), Color.red);
            line4.draw(g);

            //блок операций
            Rectangle r2 = new Rectangle(rr.getWidth(), rr.getHeight(),
                    new Coordinate(rr.getFirstPoint().getX(), line4.getLastPoint().getY()),
                    Color.red, false);
            r2.draw(g);
            Line line5 = new Line(new Coordinate(line1.getFirstPoint().getX(),
                    line4.getLastPoint().getY() + r2.getHeight()),
                    new Coordinate(line1.getFirstPoint().getX(),
                            line4.getLastPoint().getY() + r2.getHeight() + 30), Color.red);
            line5.draw(g);

            //терминатор конца
            RoundRectangle rr2 = new RoundRectangle(rr.getWidth(), rr.getHeight(), rr.getArcHeight(), rr.getArcWidth(),
                    new Coordinate(rr.getFirstPoint().getX(), line5.getLastPoint().getY()), Color.RED, false);
            rr2.draw(g);

        }
    }

}
