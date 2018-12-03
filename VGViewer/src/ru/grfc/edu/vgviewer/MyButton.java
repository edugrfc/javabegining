/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.grfc.edu.vgviewer;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.event.MouseInputAdapter;
import ru.grfc.edu.vgviewer.figures.Coordinate;
import ru.grfc.edu.vgviewer.figures.Ellipse;
import ru.grfc.edu.vgviewer.figures.Figure;
import ru.grfc.edu.vgviewer.figures.Line;
import ru.grfc.edu.vgviewer.figures.Rectangle;

/**
 *
 * @author mada
 */
//кнопка с реакцией на нажатие
public class MyButton extends Button {

    MyButton(String s, MyChoice choice, TextField text, ArrayList<Figure> figures, Component comp) {
        super(s);
        super.addMouseListener(new MyMouseListener(choice, text, figures, comp));

    }

}
//обработка реакции на нажатие. при нажатии выбранная в селекторе фигура добавляется в массив, если это возможно
//затем весь массив перерисовывается на холсте

class MyMouseListener extends MouseInputAdapter {

    MyChoice choice;
    TextField text;
    ArrayList<Figure> figures;
    Component comp;

    MyMouseListener(MyChoice choice, TextField text, ArrayList<Figure> figures, Component comp) {
        super();
        this.text = text;
        this.choice = choice;
        this.figures = figures;
        this.comp = comp;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e); //To change body of generated methods, choose Tools | Templates.
        figureSelection();
    }

    //обработка выбранной фигуры
    private void figureSelection() {

        //обработка исключений на случай ошибки ввода данных
        try {

            int[] data = textTransform();

            switch (choice.getSelectedItem()) {

                case "Rectangle":
                    figures.add(new Rectangle(data[0], data[1], new Coordinate(data[2], data[3]), Color.CYAN, false));
                    break;
                case "Square":
                    figures.add(new Rectangle(data[0], data[0], new Coordinate(data[1], data[2]), Color.GREEN, false));
                    break;
                case "Line":
                    figures.add(new Line(new Coordinate(data[0], data[1]), new Coordinate(data[2], data[3]), Color.MAGENTA));
                    break;
                case "Ellipse":
                    figures.add(new Ellipse(data[0], data[1], new Coordinate(data[2], data[3]), Color.BLACK, false));
                    break;
                case "Circle":
                    figures.add(new Ellipse(data[0], data[0], new Coordinate(data[1], data[2]), Color.BLACK, false));
                    break;
            }

            comp.repaint();
        } catch (Exception ex) {

            System.out.println("Exception happend, check input parameters");

        }

    }

    private int[] textTransform() {

        String[] stringData = text.getText().split(" ");

        int[] data = new int[stringData.length];

        for (int i = 0; i < data.length; ++i) {
            data[i] = Integer.parseInt(stringData[i].trim());

        }

        return data;
    }

}
