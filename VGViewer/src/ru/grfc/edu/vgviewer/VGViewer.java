package ru.grfc.edu.vgviewer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.grfc.edu.vgviewer.figures.Figure;
import ru.grfc.edu.vgviewer.figures.support.FigureEnum;
import ru.grfc.edu.vgviewer.figures.support.FigureParams;
import ru.grfc.edu.vgviewer.figures.support.FigureTextFileReaderWriter;
import ru.grfc.edu.vgviewer.figures.support.NormalFigureWithStdLabelFactory;
import ru.grfc.edu.vgviewer.figures.support.SimpleFigureTextFormatConverter;

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
        TextField inputParamTextField = new TextField();
        inputParamTextField.setColumns(40);
        TextField inputLabelTextField = new TextField();
        inputLabelTextField.setColumns(7);

        choiceFigure.addItemListener((ItemEvent e) -> {
            String figureName = choiceFigure.getSelectedItem();
            FigureEnum figureEnum = getFigureEnumElement(figureName);
            if (figureEnum != null) {
                inputParamTextField.setText(figureEnum.getTemplate());
                inputLabelTextField.setText("");
            } else {
                inputParamTextField.setText("Для выбора нет данных");
                inputLabelTextField.setText("");
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
                String finalParamStr = inputParamTextField.getText();
                if (!inputLabelTextField.getText().isEmpty()) {
                    finalParamStr = finalParamStr + " label=" + inputLabelTextField.getText();
                }
                Figure figure = new NormalFigureWithStdLabelFactory().getFigure(figureEnum, finalParamStr);
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
        topLeftPanel.add(inputParamTextField);
        topLeftPanel.add(inputLabelTextField);

        Panel topRigthPanel = new Panel();
        topRigthPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        topRigthPanel.add(addFigureButton);

        Panel topPanel = new Panel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(topLeftPanel, BorderLayout.WEST);
        topPanel.add(topRigthPanel, BorderLayout.EAST);

        f.add(topPanel, BorderLayout.NORTH);
        f.add(canvas, BorderLayout.CENTER);

        MenuBar mBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem menuItemSaveText = new MenuItem("Save text");
        MenuItem menuItemSaveSerial = new MenuItem("Save serial");
        MenuItem menuItemOpenText = new MenuItem("Open text");
        MenuItem menuItemOpenSerial = new MenuItem("Open serial");

        fileMenu.add(menuItemSaveText);
        fileMenu.add(menuItemSaveSerial);
        fileMenu.add(menuItemOpenText);
        fileMenu.add(menuItemOpenSerial);

        mBar.add(fileMenu);
        f.setMenuBar(mBar);

        menuItemSaveText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileDialog fg = new FileDialog(f, "Save as text");
                    fg.setFile("out-text-fig.txt");
                    fg.setVisible(true);
                    String file = fg.getDirectory() + fg.getFile();

                    NormalFigureWithStdLabelFactory figureFactory = new NormalFigureWithStdLabelFactory();
                    SimpleFigureTextFormatConverter figureTextConverter = new SimpleFigureTextFormatConverter(figureFactory);
                    FigureTextFileReaderWriter figuresWriter = new FigureTextFileReaderWriter(figureTextConverter);
                    figuresWriter.saveFiguresToFile(file, figuresToPrint);
                } catch (Exception ex) {
                    Logger.getLogger(VGViewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menuItemSaveSerial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog fg = new FileDialog(f, "Save as serialse");
                fg.setFile("out-ser-fig.bin");
                fg.setVisible(true);
                String dataFile = fg.getDirectory() + fg.getFile();
                try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)));) {
                    for (Figure fig : figuresToPrint) {
                        out.writeObject(fig);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(VGViewer.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        menuItemOpenText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileDialog fg = new FileDialog(f, "Open as text");
                    fg.setFile("out-text-fig.txt");
                    fg.setVisible(true);
                    String file = fg.getDirectory() + fg.getFile();

                    NormalFigureWithStdLabelFactory figureFactory = new NormalFigureWithStdLabelFactory();
                    SimpleFigureTextFormatConverter figureTextConverter = new SimpleFigureTextFormatConverter(figureFactory);
                    FigureTextFileReaderWriter figuresReader = new FigureTextFileReaderWriter(figureTextConverter);
                    List<Figure> newFiguresFromFile = figuresReader.loadFiguresFromFile(file);
                    
                    figuresToPrint.clear();
                    figuresToPrint.addAll(newFiguresFromFile);                                        

                    if (figuresToPrint == null || figuresToPrint.isEmpty()) {
                        return;
                    }
                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            canvas.setFigures(figuresToPrint);
                            canvas.repaint();
                        }
                    });

                } catch (Exception ex) {
                    Logger.getLogger(VGViewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menuItemOpenSerial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog fg = new FileDialog(f, "Open as serial");
                fg.setVisible(true);
                String dataFile = fg.getDirectory() + fg.getFile();

                List<Figure> newFigList = new ArrayList<>();
                try (FileInputStream fileInputStream = new FileInputStream(dataFile);
                        BufferedInputStream buf = new BufferedInputStream(fileInputStream);
                        ObjectInputStream in = new ObjectInputStream(buf);) {
                    while (true) {
                        Figure fig = (Figure) in.readObject();
                        newFigList.add(fig);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(VGViewer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VGViewer.class.getName()).log(Level.SEVERE, null, ex);
                }
                figuresToPrint.clear();
                figuresToPrint.addAll(newFigList);

                if (figuresToPrint == null || figuresToPrint.isEmpty()) {
                    return;
                }
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        canvas.setFigures(figuresToPrint);
                        canvas.repaint();
                    }
                });
            }
        });

        f.setSize(800, 500);
        f.setVisible(true);
    }

    private FigureEnum getFigureEnumElement(String figureName) {
        if (figureName == null || figureName.isEmpty()) {
            return null;
        }
        FigureEnum figureType = null;
        for (FigureEnum value : FigureEnum.values()) {
            if (value.equalsName(figureName)) {
                figureType = value;
            }
        }
        return figureType;
    }
}
