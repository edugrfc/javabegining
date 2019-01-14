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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.grfc.edu.vgviewer.figures.Coordinate;
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
                File file;
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
                File file;
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

        // Пункт меню "Open xml with SAX" для загрузки данных, сохраненных в xml файл
        MenuItem oXmlSax = new MenuItem("Open xml with SAX");
        oXmlSax.addActionListener(new ActionListener() {
            long startTime = 0;
            long endTime = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                class XMLHandler extends DefaultHandler {

                    private String w, h, isFilled, fx, fy, px, py, color, text, tx, ty, lastElementName, figureParamStr;

                    @Override
                    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                        switch (qName) {
                            case ("line"):
                                fx = attributes.getValue("x");
                                fy = attributes.getValue("y");
                                color = attributes.getValue("color");
                                break;
                            case ("rectangle"):
                                w = attributes.getValue("width");
                                h = attributes.getValue("height");
                                isFilled = attributes.getValue("isFilled");
                                color = attributes.getValue("color");
                                break;
                            case ("point"):
                                px = attributes.getValue("x");
                                py = attributes.getValue("y");
                                break;
                            case ("label"):
                                tx = attributes.getValue("x");
                                ty = attributes.getValue("y");
                        }
                        lastElementName = qName;
                    }

                    @Override
                    public void characters(char[] ch, int start, int length) throws SAXException {
                        if ("label".equals(lastElementName)) {
                            String information = new String(ch, start, length);
                            information = information.replace("\n", "").trim();
                            text = information;
                        }
                        lastElementName = "";
                    }

                    @Override
                    public void endElement(String uri, String localName,
                            String qName) throws SAXException {
                        if ("line".equals(qName)) {
                            figureParamStr = "fx=" + fx + " fy=" + fy + " lx=" + px + " ly=" + py + " color=" + color
                                    + " text=" + text + " tx=" + tx + " ty=" + ty;

                            figuresToPrint.add(NormalFigureFactory.getFigure(FigureEnum.LINE, figureParamStr));
                        }
                        if ("rectangle".equals(qName)) {
                            figureParamStr = "w=" + w + " h=" + h + " fx=" + px + " fy=" + py + " color=" + color
                                    + " fill=" + isFilled + " text=" + text + " tx=" + tx + " ty=" + ty;
                            figuresToPrint.add(NormalFigureFactory.getFigure(FigureEnum.RECTANGLE, figureParamStr));
                        }
                    }
                }

                JFileChooser fileopen = new JFileChooser();
                File file;
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileopen.getSelectedFile();
                    try {
                        SAXParserFactory factory = SAXParserFactory.newInstance();
                        SAXParser parser = factory.newSAXParser();
                        XMLHandler handler = new XMLHandler();
                        figuresToPrint.clear();
                        startTime = System.currentTimeMillis();
                        parser.parse(file, handler);
                        endTime = System.currentTimeMillis();
                        if (figuresToPrint.size() > 1000) {
                            JOptionPane.showMessageDialog(null, "Время чтения xml файла: " + (endTime - startTime) / 1000.0 + " c");
                        }
                    } catch (ParserConfigurationException | SAXException | IOException se) {
                    }
                    canvas.repaint();
                }
            }
        });

        // Пункт меню "Open xml with DOM" для загрузки данных, сохраненных в xml файл
        MenuItem oXmlDom = new MenuItem("Open xml with DOM");
        oXmlDom.addActionListener(new ActionListener() {
            long startTime = 0;
            long endTime = 0;

            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileopen = new JFileChooser();
                File file;
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileopen.getSelectedFile();

                    try {
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        figuresToPrint.clear();
                        startTime = System.currentTimeMillis();
                        Document document = builder.parse(file);
                        String figureParamStr, w, h, color, fx, fy, isFilled;
                        String px = null;
                        String py = null;
                        String text = null;
                        String tx = null;
                        String ty = null;

                        // Получение списка всех элементов line внутри корневого элемента
                        NodeList lineElements = document.getDocumentElement().getElementsByTagName("line");

                        for (int i = 0; i < lineElements.getLength(); i++) {
                            Node line = lineElements.item(i);
                            NamedNodeMap lineAttributes = line.getAttributes();
                            fx = lineAttributes.getNamedItem("x").getNodeValue();
                            fy = lineAttributes.getNamedItem("y").getNodeValue();
                            color = lineAttributes.getNamedItem("color").getNodeValue();
                            for (int j = 0; j < line.getChildNodes().getLength(); j++) {
                                switch (line.getChildNodes().item(j).getNodeName()) {
                                    case ("point"):
                                        NamedNodeMap pointAttributes = line.getChildNodes().item(j).getAttributes();
                                        px = pointAttributes.getNamedItem("x").getNodeValue();
                                        py = pointAttributes.getNamedItem("y").getNodeValue();
                                        break;
                                    case ("label"):
                                        NamedNodeMap labelAttributes = line.getChildNodes().item(j).getAttributes();
                                        tx = labelAttributes.getNamedItem("x").getNodeValue();
                                        ty = labelAttributes.getNamedItem("y").getNodeValue();
                                        text = line.getChildNodes().item(j).getTextContent();
                                        break;
                                }

                            }
                            figureParamStr = "fx=" + fx + " fy=" + fy + " lx=" + px + " ly=" + py + " color=" + color
                                    + " text=" + text + " tx=" + tx + " ty=" + ty;

                            figuresToPrint.add(NormalFigureFactory.getFigure(FigureEnum.LINE, figureParamStr));
                        }

                        // Получение списка всех элементов rectangle внутри корневого элемента
                        NodeList recElements = document.getDocumentElement().getElementsByTagName("rectangle");
                        for (int i = 0; i < recElements.getLength(); i++) {
                            Node rec = recElements.item(i);
                            NamedNodeMap recAttributes = rec.getAttributes();
                            w = recAttributes.getNamedItem("width").getNodeValue();
                            h = recAttributes.getNamedItem("height").getNodeValue();
                            isFilled = recAttributes.getNamedItem("isFilled").getNodeValue();
                            color = recAttributes.getNamedItem("color").getNodeValue();
                            for (int j = 0; j < rec.getChildNodes().getLength(); j++) {
                                switch (rec.getChildNodes().item(j).getNodeName()) {
                                    case ("point"):
                                        NamedNodeMap pointAttributes = rec.getChildNodes().item(j).getAttributes();
                                        px = pointAttributes.getNamedItem("x").getNodeValue();
                                        py = pointAttributes.getNamedItem("y").getNodeValue();
                                        break;
                                    case ("label"):
                                        NamedNodeMap labelAttributes = rec.getChildNodes().item(j).getAttributes();
                                        tx = labelAttributes.getNamedItem("x").getNodeValue();
                                        ty = labelAttributes.getNamedItem("y").getNodeValue();
                                        text = rec.getChildNodes().item(j).getTextContent();
                                        break;
                                }

                            }
                            figureParamStr = "w=" + w + " h=" + h + " fx=" + px + " fy=" + py + " color=" + color
                                    + " fill=" + isFilled + " text=" + text + " tx=" + tx + " ty=" + ty;
                            figuresToPrint.add(NormalFigureFactory.getFigure(FigureEnum.RECTANGLE, figureParamStr));
                        }
                        endTime = System.currentTimeMillis();
                        if (figuresToPrint.size() > 1000) {
                            JOptionPane.showMessageDialog(null, "Время чтения xml файла: " + (endTime - startTime) / 1000.0 + " c");
                        }
                    } catch (ParserConfigurationException | SAXException | IOException se) {
                    }
                    canvas.repaint();
                }
            }
        }
        );

        // Пункт меню "Save xml" для сохранения данных в xml файл
        MenuItem sXml = new MenuItem("Save xml");

        sXml.addActionListener(new ActionListener() {
            long startTime = 0;
            long endTime = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                File file = null;
                int ret = fileopen.showDialog(null, "Сохранить в файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileopen.getSelectedFile();
                    try {
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        Document doc = factory.newDocumentBuilder().newDocument();
                        Element figures = doc.createElement("figures");
                        doc.appendChild(figures);
                        for (Figure figure : figuresToPrint) {
                            switch ((figure.getClass()).getSimpleName()) {
                                case ("Line"):
                                    Line figLine = (Line) figure;
                                    Element line = doc.createElement("line");
                                    line.setAttribute("x", String.valueOf(figLine.getFirstPoint().getX()));
                                    line.setAttribute("y", String.valueOf(figLine.getFirstPoint().getY()));
                                    line.setAttribute("color", "rgb(" + figLine.getColor().getRed() + "," + figLine.getColor().getGreen() + "," + figLine.getColor().getBlue() + ")");
                                    figures.appendChild(line);
                                    Element linePoint = doc.createElement("point");
                                    linePoint.setAttribute("x", String.valueOf(figLine.getLastPoint().getX()));
                                    linePoint.setAttribute("y", String.valueOf(figLine.getLastPoint().getY()));
                                    line.appendChild(linePoint);
                                    Element label = doc.createElement("label");
                                    label.setAttribute("x", String.valueOf(figLine.getTextPoint().getX()));
                                    label.setAttribute("y", String.valueOf(figLine.getTextPoint().getY()));
                                    label.appendChild(doc.createTextNode(figLine.getText()));
                                    line.appendChild(label);
                                    break;
                                case ("Rectangle"):
                                    Rectangle figRectangle = (Rectangle) figure;
                                    Element rectangle = doc.createElement("rectangle");
                                    rectangle.setAttribute("width", String.valueOf(figRectangle.getWidth()));
                                    rectangle.setAttribute("height", String.valueOf(figRectangle.getHeight()));
                                    rectangle.setAttribute("isFilled", String.valueOf(figRectangle.isFill()));
                                    rectangle.setAttribute("color", "rgb(" + figRectangle.getColor().getRed() + "," + figRectangle.getColor().getGreen() + "," + figRectangle.getColor().getBlue() + ")");
                                    figures.appendChild(rectangle);
                                    Element recPoint = doc.createElement("point");
                                    recPoint.setAttribute("x", String.valueOf(figRectangle.getFirstPoint().getX()));
                                    recPoint.setAttribute("y", String.valueOf(figRectangle.getFirstPoint().getY()));
                                    rectangle.appendChild(recPoint);
                                    Element recLabel = doc.createElement("label");
                                    recLabel.setAttribute("x", String.valueOf(figRectangle.getTextPoint().getX()));
                                    recLabel.setAttribute("y", String.valueOf(figRectangle.getTextPoint().getY()));
                                    recLabel.appendChild(doc.createTextNode(figRectangle.getText()));
                                    rectangle.appendChild(recLabel);
                                    break;
                            }
                        }
                        try {
                            Transformer transformer = TransformerFactory.newInstance().newTransformer();
                            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                            transformer.transform(new DOMSource(doc), new StreamResult(file));
                        } catch (Exception ep) {

                        }

                    } catch (ParserConfigurationException pce) {

                    }
                }
            }
        });

        // Пункт меню "To generate figures" для генерации фигур
        MenuItem fGen = new MenuItem("To generate 20000 figures");
        fGen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                figuresToPrint.clear();
                Random random = new Random();

                // Создадим 10000 линий и прямоугольников
                for (int i = 0; i < 10000; i++) {
                    Coordinate lineFirstPoint = new Coordinate(random.nextInt(f.getWidth() - 1), random.nextInt(f.getHeight() - 1));
                    Coordinate lineLastPoint = new Coordinate(random.nextInt(f.getWidth() - 1), random.nextInt(f.getHeight() - 1));
                    String text = "" + (i + 1);
                    Coordinate lineTextPoint = new Coordinate(lineFirstPoint.getX() + 10, lineFirstPoint.getY());
                    Line line = new Line(lineFirstPoint, lineLastPoint, Color.GREEN, text, lineTextPoint);
                    figuresToPrint.add(line);
                    int width = random.nextInt(150) + 20;
                    int height = random.nextInt(150) + 20;
                    Coordinate recFirstPoint = new Coordinate(random.nextInt(f.getWidth() - 1), random.nextInt(f.getHeight() - 1));
                    Coordinate recTextPoint = new Coordinate(recFirstPoint.getX() + 10, recFirstPoint.getY());
                    Rectangle rectangle = new Rectangle(width, height, recFirstPoint, Color.RED, false, text, recTextPoint);
                    figuresToPrint.add(rectangle);
                }
                canvas.repaint();
            }
        }
        );

        fileMenu.add(oSer);
        fileMenu.add(sSer);
        fileMenu.add(oTxt);
        fileMenu.add(sTxt);
        fileMenu.add(oXmlSax);
        fileMenu.add(oXmlDom);
        fileMenu.add(sXml);
        fileMenu.add(fGen);
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

    private FigureEnum
            getFigureEnumElement(String figureName) {
        FigureEnum figureEnum = null;
        for (FigureEnum value : FigureEnum.values()) {
            if (value.equalsName(figureName)) {
                figureEnum = value;
            }
        }
        return figureEnum;
    }
}
