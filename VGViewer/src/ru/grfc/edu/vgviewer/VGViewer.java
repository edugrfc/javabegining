package ru.grfc.edu.vgviewer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import ru.grfc.edu.vgviewer.figures.Figure;
import ru.grfc.edu.vgviewer.figures.support.FigureEnum;
import ru.grfc.edu.vgviewer.figures.support.NormalFigureFactory;

/**
 * Главный класс для запуска вьювера векторной графики
 *
 * @author gsv
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

        // Для центральной части
        ViewerCanvas canvas = new ViewerCanvas();
        canvas.setFigures(figuresToPrint);

        // Для левой верхней панели
        Label choiceFigureLabel = new Label("Выбор фигруры:");
        Choice choiceFigure = new Choice();
        for (FigureEnum value : FigureEnum.values()) {
            choiceFigure.add(value.toString());
        }
        TextField imputParamTextField = new TextField();
        imputParamTextField.setColumns(40);
        choiceFigure.addItemListener((ItemEvent e) -> {
            String figureName = choiceFigure.getSelectedItem();
            FigureEnum figureEnum = getFigureEnumElement(figureName);
            if (figureEnum != null) {
                imputParamTextField.setText(figureEnum.getTemplate());
            } else {
                imputParamTextField.setText("Для выбора нет данных");
            }

        });
        choiceFigure.getItemListeners()[0].itemStateChanged(null);

        // Для правой верхней панели
        Button addFigureButton = new Button("Добавить");
        addFigureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String figureName = choiceFigure.getSelectedItem();
                FigureEnum figureEnum = getFigureEnumElement(figureName);
                Figure figure = NormalFigureFactory.getFigure(figureEnum, imputParamTextField.getText());
                if (figure != null) {
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

        Panel topLeftPanel = new Panel();
        topLeftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topLeftPanel.add(choiceFigureLabel);
        topLeftPanel.add(choiceFigure);
        topLeftPanel.add(imputParamTextField);

        Panel topRigthPanel = new Panel();
        topRigthPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        topRigthPanel.add(addFigureButton);

        Panel topPanel = new Panel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(topLeftPanel, BorderLayout.WEST);
        topPanel.add(topRigthPanel, BorderLayout.EAST);

        f.add(topPanel, BorderLayout.NORTH);
        f.add(canvas, BorderLayout.CENTER);
        f.setSize(800, 500);
        f.setVisible(true);
    }

    private FigureEnum getFigureEnumElement(String figureName) {
        FigureEnum figureEnum = null;
        for (FigureEnum value : FigureEnum.values()) {
            if (value.equalsName(figureName)) {
                figureEnum = value;
            }
        }
        return figureEnum;
    }
}
