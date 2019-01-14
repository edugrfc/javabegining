/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eduxml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author mada
 */
public class DomReadTest implements ErrorHandler {

    public void warning(SAXParseException spe) throws SAXException {
        System.out.println("Warning: " + spe.toString());
    }

    public void error(SAXParseException spe) throws SAXException {
        throw new SAXException("Error: " + spe.toString());
    }

    public void fatalError(SAXParseException spe) throws SAXException {
        throw new SAXException("Fatal Error: " + spe.toString());
    }

    public static class Figure {

        public int x1;
        public int y1;
        public int x2;
        public int y2;
        public int h;
        public int w;
        public String color;
        public String label;
        public int xL;
        public int yL;
        public boolean filled;
        public String fig;

    }

    public List<Figure> figures = new ArrayList<Figure>();

    public static void main(String[] args) throws Exception {
        String filename = "src/result.xml";
        String xmlFilePath = "src/domwriter.xml";
        long startTime = System.currentTimeMillis();
        DomReadTest eduDom = new DomReadTest();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //dbf.setNamespaceAware(true);
        //dbf.setValidating(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        db.setErrorHandler(eduDom);
        Document doc = db.parse(new File(filename));
        //парсим линии
        NodeList lines = doc.getElementsByTagName("line");
        for (int i = 0; i < lines.getLength(); i++) {
            Figure fig = new Figure();
            eduDom.figures.add(fig);
            Node lineNode = lines.item(i);
            //вытаскиваем атрибуты line
            Element el = (Element) lineNode;
            fig.x1 = Integer.parseInt(el.getAttribute("x"));
            fig.y1 = Integer.parseInt(el.getAttribute("y"));
            fig.color = el.getAttribute("color");
            fig.fig = "line";
            NodeList docNoteSubTags = lineNode.getChildNodes();
            for (int j = 0; j < docNoteSubTags.getLength(); j++) {
                Node subTag = docNoteSubTags.item(j);
                if (subTag.getNodeType() == Node.ELEMENT_NODE) {
                    el = (Element) subTag;
                    switch (subTag.getNodeName()) {
                        case "point":
                            fig.x2 = Integer.parseInt(el.getAttribute("x"));
                            fig.y2 = Integer.parseInt(el.getAttribute("y"));
                            break;
                        case "label":
                            fig.xL = Integer.parseInt(el.getAttribute("x"));
                            fig.yL = Integer.parseInt(el.getAttribute("y"));
                            fig.label = subTag.getTextContent();
                            break;
                    }
                }
            }
        }
        //парсим прямоугольники
        NodeList rectangles = doc.getElementsByTagName("rectangle");
        for (int i = 0; i < rectangles.getLength(); i++) {
            Figure fig = new Figure();
            eduDom.figures.add(fig);
            Node rectNode = rectangles.item(i);
            //вытаскиваем атрибуты line
            Element el = (Element) rectNode;
            fig.h = Integer.parseInt(el.getAttribute("height"));
            fig.w = Integer.parseInt(el.getAttribute("width"));
            fig.color = el.getAttribute("color");
            fig.filled = Boolean.getBoolean(el.getAttribute("isFilled"));
            fig.fig = "rectangle";
            NodeList docNoteSubTags = rectNode.getChildNodes();
            for (int j = 0; j < docNoteSubTags.getLength(); j++) {
                Node subTag = docNoteSubTags.item(j);
                if (subTag.getNodeType() == Node.ELEMENT_NODE) {
                    el = (Element) subTag;
                    switch (subTag.getNodeName()) {
                        case "point":
                            fig.x1 = Integer.parseInt(el.getAttribute("x"));
                            fig.y1 = Integer.parseInt(el.getAttribute("y"));
                            break;
                        case "label":
                            fig.xL = Integer.parseInt(el.getAttribute("x"));
                            fig.yL = Integer.parseInt(el.getAttribute("y"));
                            fig.label = subTag.getTextContent();
                            break;
                    }
                }
            }
        }
        long endTime = System.currentTimeMillis();
        double res = (endTime - startTime) / 1000.0;
        eduDom.figures.forEach((fig) -> {
            System.out.println("fig  : (" + fig.label);
        });

        System.out.println("Total dom reading time: " + res);
        //domwrite test
        /*
        Document document = db.newDocument();
        Element figures = document.createElement("figures");
        document.appendChild(figures);

        eduDom.figures.forEach((fig) -> {

            if (fig.fig.equals("line")) {
                Element line = document.createElement("line");
                line.setAttribute("x", String.valueOf(fig.x1));
                line.setAttribute("y", String.valueOf(fig.y1));
                line.setAttribute("color", fig.color);
                Element point = document.createElement("point");
                point.setAttribute("x", String.valueOf(fig.x2));
                point.setAttribute("y", String.valueOf(fig.y2));
                Element label = document.createElement("label");
                label.setAttribute("x", String.valueOf(fig.xL));
                label.setAttribute("y", String.valueOf(fig.yL));
                label.appendChild(document.createTextNode(fig.label));
                figures.appendChild(line);
                line.appendChild(point);
                line.appendChild(label);
            } else {
                Element rectangle = document.createElement("rectangle");
                rectangle.setAttribute("width", String.valueOf(fig.w));
                rectangle.setAttribute("height", String.valueOf(fig.h));
                rectangle.setAttribute("color", fig.color);
                rectangle.setAttribute("isFilled", String.valueOf(fig.filled));
                Element point = document.createElement("point");
                point.setAttribute("x", String.valueOf(fig.x1));
                point.setAttribute("y", String.valueOf(fig.y1));
                Element label = document.createElement("label");
                label.setAttribute("x", String.valueOf(fig.xL));
                label.setAttribute("y", String.valueOf(fig.yL));
                label.appendChild(document.createTextNode(fig.label));
                figures.appendChild(rectangle);
                rectangle.appendChild(point);
                rectangle.appendChild(label);

            }
        });

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        Transformer transformer = transformerFactory.newTransformer();

        DOMSource domSource = new DOMSource(document);

        StreamResult streamResult = new StreamResult(new File(xmlFilePath));
        
        transformer.transform(domSource, streamResult);*/
        
    }

}
