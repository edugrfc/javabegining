package ru.grfc.edu.vgviewer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import ru.grfc.edu.vgviewer.figures.Coordinate;
import ru.grfc.edu.vgviewer.figures.Figure;
import ru.grfc.edu.vgviewer.figures.Line;
import ru.grfc.edu.vgviewer.figures.Parallelogram;
import ru.grfc.edu.vgviewer.figures.Rectangle;
import ru.grfc.edu.vgviewer.figures.Rhombus;
import ru.grfc.edu.vgviewer.figures.RoundRectangle;
import ru.grfc.edu.vgviewer.figures.support.FigureEnum;
import ru.grfc.edu.vgviewer.figures.support.NormalFigureFactory;

/**
 * Главный класс для запуска вьювера векторной графики
 *
 * @author dds
 */
public class VGViewer {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                VGViewer vGViewer = new VGViewer();
            }
        });
    }

    public VGViewer() {
        List<Figure> figuresToPrint = new ArrayList<>();
        Frame f = new MainWindow("VGViewer");
        f.setLayout(new BorderLayout());

        ViewerCanvas canvas = new ViewerCanvas();
        canvas.setFigures(figuresToPrint);

        Panel topPanel = new Panel();
        topPanel.setLayout(new BorderLayout());

        Panel topLeftPanel = new Panel();
        topLeftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        Label choiceFigureLabel = new Label("Выбор фигруры:");
        Choice choiceFigure = new Choice();
        for (FigureEnum value : FigureEnum.values()) {
            choiceFigure.add(value.toString());
        }

        TextField imputParamTextField = new TextField();
        imputParamTextField.setColumns(40);

        choiceFigure.addItemListener((ItemEvent e) -> {
            String figureName = choiceFigure.getSelectedItem();
            FigureEnum figureEnum = getFigureElement(figureName);
            if (figureEnum != null) {
                imputParamTextField.setText(figureEnum.getTemplate());
            }
        });
        choiceFigure.getItemListeners()[0].itemStateChanged(null);

        topLeftPanel.add(choiceFigureLabel);
        topLeftPanel.add(choiceFigure);
        topLeftPanel.add(imputParamTextField);

        Panel topRigthPanel = new Panel();
        topRigthPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        Button addFigureButton = new Button("Добавить");
        addFigureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String figureName = choiceFigure.getSelectedItem();
                FigureEnum figureEnum = getFigureElement(figureName);
                Figure figure = NormalFigureFactory.getFigure(figureEnum, imputParamTextField.getText());
                if(figure != null){
                    figuresToPrint.add(figure);
                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            canvas.setFigures(figuresToPrint);
                            canvas.repaint();
                        }
                    });
                }
            }
        });
        topRigthPanel.add(addFigureButton);

        topPanel.add(topLeftPanel, BorderLayout.WEST);
        topPanel.add(topRigthPanel, BorderLayout.EAST);

        f.add(topPanel, BorderLayout.NORTH);
        f.add(canvas, BorderLayout.CENTER);
        f.setSize(800, 500);
        f.setVisible(true);
    }

    private FigureEnum getFigureElement(String figureName) {
        FigureEnum figureEnum = null;
        for (FigureEnum value : FigureEnum.values()) {
            if (value.equalsName(figureName)) {
                figureEnum = value;
            }
        }
        return figureEnum;
    }
}
