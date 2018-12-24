package ru.grfc.edu.vgviewer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import ru.grfc.edu.vgviewer.figures.Ellipse;
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

        // Строка меню
        MenuBar mbar = new MenuBar();

        // Меню "File"
        Menu fileMenu = new Menu("File");

        // Пункт меню "Open ser" для открытия сериализованных данных
        MenuItem oSer = new MenuItem("Open ser");
        oSer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                File file = null;
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileopen.getSelectedFile();
                }
                try ( ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                    figuresToPrint.clear();
                    ArrayList<Figure> al = (ArrayList) in.readObject();
                    for (Figure figure : al) {
                        figuresToPrint.add(figure);
                    }
                    canvas.repaint();
                } catch (Exception ex) {
                    System.out.println("Исключение " + ex);
                }
            }
        });

        // Пункт меню "Save ser" для сохранения сериализованных данных
        MenuItem sSer = new MenuItem("Save ser");
        sSer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                File file = null;
                int ret = fileopen.showDialog(null, "Сохранить в файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileopen.getSelectedFile();

                    try ( ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                        out.writeObject(figuresToPrint);
                    } catch (Exception ex) {
                        System.out.println("Исключение " + ex);
                    }
                }
            }
        });

        // Пункт меню "Open txt" для открытия данных, сохраненных в текстовый файл
        MenuItem oTxt = new MenuItem("Open txt");
        oTxt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                File file = null;
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileopen.getSelectedFile();

                    try ( BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String paramStr = null;
                        figuresToPrint.clear();
                        while (reader.ready()) {
                            paramStr = reader.readLine();
                            switch (paramStr.substring(0, 2)) {
                                case ("El"):
                                    figuresToPrint.add(NormalFigureFactory.getFigure(FigureEnum.ELLIPSE, paramStr.substring(3, paramStr.length())));
                                    break;
                                case ("Li"):
                                    figuresToPrint.add(NormalFigureFactory.getFigure(FigureEnum.LINE, paramStr.substring(3, paramStr.length())));
                                    break;
                                case ("Pa"):
                                    figuresToPrint.add(NormalFigureFactory.getFigure(FigureEnum.PARALLELOGRAM, paramStr.substring(3, paramStr.length())));
                                    break;
                                case ("Re"):
                                    figuresToPrint.add(NormalFigureFactory.getFigure(FigureEnum.RECTANGLE, paramStr.substring(3, paramStr.length())));
                                    break;
                                case ("Rh"):
                                    figuresToPrint.add(NormalFigureFactory.getFigure(FigureEnum.RHOMBUS, paramStr.substring(3, paramStr.length())));
                                    break;
                                case ("Ro"):
                                    figuresToPrint.add(NormalFigureFactory.getFigure(FigureEnum.ROUND_RECTAGLE, paramStr.substring(3, paramStr.length())));
                                    break;
                            }
                        }

                        canvas.repaint();

                    } catch (Exception ex) {
                        System.out.println("Исключение " + ex);
                    }
                }
            }
        });

        // Пункт меню "Save txt" для сохранения данных в текстовый файл
        MenuItem sTxt = new MenuItem("Save txt");
        sTxt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                File file = null;
                int ret = fileopen.showDialog(null, "Сохранить в файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileopen.getSelectedFile();
                    try ( BufferedWriter writer = new BufferedWriter(new FileWriter(file), 1024)) {
                        String str = null;
                        for (Figure figure : figuresToPrint) {
                            switch ((figure.getClass()).getSimpleName()) {
                                case ("Ellipse"):
                                    Ellipse el = (Ellipse) figure;
                                    str = "El" + " w=" + el.getWidth() + " h=" + el.getHeight()
                                            + " fx=" + el.getFirstPoint().getX() + " fy=" + el.getFirstPoint().getY()
                                            + " color=" + "rgb(" + el.getColor().getRed() + "," + el.getColor().getGreen() + "," + el.getColor().getBlue() + ")" + " fill=" + el.isFill() + " text=" + el.getText()
                                            + " tx=" + el.getTextPoint().getX() + " ty=" + el.getTextPoint().getY();
                                    writer.write(str + "\n");
                                    break;
                                case ("Line"):
                                    Line li = (Line) figure;
                                    str = "Li" + " fx=" + li.getFirstPoint().getX() + " fy=" + li.getFirstPoint().getY()
                                            + " lx=" + li.getLastPoint().getX() + " ly=" + li.getLastPoint().getY()
                                            + " color=" + "rgb(" + li.getColor().getRed() + "," + li.getColor().getGreen() + "," + li.getColor().getBlue() + ")" + " text=" + li.getText()
                                            + " tx=" + li.getTextPoint().getX() + " ty=" + li.getTextPoint().getY();
                                    writer.write(str + "\n");
                                    break;
                                case ("Parallelogram"):
                                    Parallelogram pa = (Parallelogram) figure;
                                    str = "Pa" + " blunt=" + pa.isIsBlunt() + " w=" + pa.getWidth() + " h=" + pa.getHeight()
                                            + " fx=" + pa.getFirstPoint().getX() + " fy=" + pa.getFirstPoint().getY()
                                            + " color=" + "rgb(" + pa.getColor().getRed() + "," + pa.getColor().getGreen() + "," + pa.getColor().getBlue() + ")" + " fill=" + pa.isFill() + " text=" + pa.getText()
                                            + " tx=" + pa.getTextPoint().getX() + " ty=" + pa.getTextPoint().getY();
                                    writer.write(str + "\n");
                                    break;
                                case ("Rectangle"):
                                    Rectangle re = (Rectangle) figure;
                                    str = "Re" + " w=" + re.getWidth() + " h=" + re.getHeight()
                                            + " fx=" + re.getFirstPoint().getX() + " fy=" + re.getFirstPoint().getY()
                                            + " color=" + "rgb(" + re.getColor().getRed() + "," + re.getColor().getGreen() + "," + re.getColor().getBlue() + ")" + " fill=" + re.isFill() + " text=" + re.getText()
                                            + " tx=" + re.getTextPoint().getX() + " ty=" + re.getTextPoint().getY();
                                    writer.write(str + "\n");
                                    break;
                                case ("Rhombus"):
                                    Rhombus rh = (Rhombus) figure;
                                    str = "Rh" + " w=" + rh.getWidth() + " fx=" + rh.getFirstPoint().getX() + " fy=" + rh.getFirstPoint().getY()
                                            + " color=" + "rgb(" + rh.getColor().getRed() + "," + rh.getColor().getGreen() + "," + rh.getColor().getBlue() + ")" + " fill=" + rh.isFill() + " text=" + rh.getText()
                                            + " tx=" + rh.getTextPoint().getX() + " ty=" + rh.getTextPoint().getY();
                                    writer.write(str + "\n");
                                    break;
                                case ("RoundRectangle"):
                                    RoundRectangle ro = (RoundRectangle) figure;
                                    str = "Ro" + " w=" + ro.getWidth() + " h=" + ro.getHeight() + " aw=" + ro.getArcWidth() + " ah=" + ro.getArcHeight()
                                            + " fx=" + ro.getFirstPoint().getX() + " fy=" + ro.getFirstPoint().getY()
                                            + " color=" + "rgb(" + ro.getColor().getRed() + "," + ro.getColor().getGreen() + "," + ro.getColor().getBlue() + ")" + " fill=" + ro.isFill() + " text=" + ro.getText()
                                            + " tx=" + ro.getTextPoint().getX() + " ty=" + ro.getTextPoint().getY();
                                    writer.write(str + "\n");
                                    break;
                            }
                        }
                    } catch (Exception ex) {
                        System.out.println("Исключение " + ex);
                    }
                }
            }
        });

        fileMenu.add(oSer);
        fileMenu.add(sSer);
        fileMenu.add(oTxt);
        fileMenu.add(sTxt);
        mbar.add(fileMenu);
        f.setMenuBar(mbar);

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