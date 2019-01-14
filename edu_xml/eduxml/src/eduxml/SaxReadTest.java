package eduxml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class SaxReadTest extends DefaultHandler {

    public List<Figure> figures = new ArrayList<>();
    private String currentFigure = "";
    private String currentElement = "";

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startElement(String namespaceURI, String localName,
            String qName, Attributes atts) throws SAXException {
        Figure fig = null;
        if (!figures.isEmpty()) {
            fig = figures.get(figures.size() - 1);
        }
        switch (qName) {
            case "figures":
                break;
            case "line":
                currentFigure = qName;
                currentElement = "";
                figures.add(new Figure());
                fig = figures.get(figures.size() - 1);
                String x = atts.getValue("x");
                int xInt = Integer.parseInt(x);
                fig.x1 = Integer.parseInt(atts.getValue("x"));
                fig.y1 = Integer.parseInt(atts.getValue("y"));
                fig.color = atts.getValue("color");
                break;
            case "rectangle":
                currentFigure = qName;
                figures.add(new Figure());
                fig = figures.get(figures.size() - 1);
                fig.h = Integer.parseInt(atts.getValue("height"));
                fig.w = Integer.parseInt(atts.getValue("width"));
                fig.filled = Boolean.valueOf(atts.getValue("isFilled"));
                fig.color = atts.getValue("color");
                currentElement = "";
                break;
            case "label":
                currentElement = qName;
                fig.xL = Integer.parseInt(atts.getValue("x"));
                fig.yL = Integer.parseInt(atts.getValue("y"));
                break;
            case "point":
                switch (currentFigure) {
                    case "rectangle":
                        fig.x1 = Integer.parseInt(atts.getValue("x"));
                        fig.y1 = Integer.parseInt(atts.getValue("y"));
                        break;
                    case "line":
                        fig.x2 = Integer.parseInt(atts.getValue("x"));
                        fig.y2 = Integer.parseInt(atts.getValue("y"));
                        break;
                }
                break;
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName,
            String qName) throws SAXException {
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        char[] s = Arrays.copyOfRange(ch, start, start + length);
        String string = new String(s);
        if (!figures.isEmpty()) {
            Figure fig = figures.get(figures.size() - 1);
            switch (currentElement) {
                case "label":
                    fig.label = string;
                    currentElement = "";
                    break;
            }
        }
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length)
            throws SAXException {
    }

    public void warning(SAXParseException spe) throws SAXException {
        System.out.println("Warning: " + spe.toString());
    }

    public void error(SAXParseException spe) throws SAXException {
        String message = "Error: " + spe.toString();
        throw new SAXException(message);
    }

    public void fatalError(SAXParseException spe) throws SAXException {
        String message = "Fatal Error: " + spe.toString();
        throw new SAXException(message);
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        String filename = "src/result.xml";
        long startTime = System.currentTimeMillis();
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = spf.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        SaxReadTest saxHandler = new SaxReadTest();
        xmlReader.setContentHandler(saxHandler);
        xmlReader.setErrorHandler(saxHandler);
        xmlReader.parse(filename);
        long endTime = System.currentTimeMillis();
        double res = (endTime - startTime) / 1000.0;
        saxHandler.figures.forEach((fig) -> {
            System.out.println("Figure: " + fig.label);
        });

        System.out.println("Total parse read time: " + res);
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

    }

}
