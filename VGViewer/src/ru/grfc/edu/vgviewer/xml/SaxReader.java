/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.grfc.edu.vgviewer.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.List;
import ru.grfc.edu.vgviewer.figures.*;
import ru.grfc.edu.vgviewer.figures.support.*;

/**
 *
 * @author mada
 */
public class SaxReader {

    public SaxReader(String path, List<Figure> figures) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = spf.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        SaxReading saxHandler = new SaxReading();
        xmlReader.setContentHandler(saxHandler);
        xmlReader.setErrorHandler(saxHandler);
        xmlReader.parse(path);
        figures.clear();
        for (Params par : saxHandler.params) {
            String parStr ;
            if (par.fig == "li") {
                parStr = "fx=" + par.x1 + " fy=" + par.y1 + " lx="
                        + par.x2 + " ly=" + par.y2 + " color=" + par.color
                        + " text=" + par.label + " tx=" + par.xL + " ty=" + par.yL;  
                figures.add(NormalFigureFactory.getFigure(FigureEnum.LINE, parStr));
                
            } else {
                parStr = "w=" + par.w + " h=" + par.h + " fx=" + par.x1 + " fy="
                        + par.y1 + " color=" + par.color + " fill=" + par.filled 
                        + " text=" + par.label + " tx=" + par.xL + " ty=" + par.yL;
                System.out.println(parStr);
                figures.add(NormalFigureFactory.getFigure(FigureEnum.RECTANGLE, parStr));
            }
        }


    }
}

class SaxReading extends DefaultHandler {

    public List<Params> params;
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
        Params par = null;
        if (!params.isEmpty()) {
            par = params.get(params.size() - 1);
        }
        switch (qName) {
            case "figures":
                break;
            case "line":
                currentFigure = qName;
                currentElement = "";
                params.add(new Params());
                par = params.get(params.size() - 1);
                par.x1 = Integer.parseInt(atts.getValue("x"));
                par.y1 = Integer.parseInt(atts.getValue("y"));
                par.color = atts.getValue("color");
                par.fig = "li";
                break;
            case "rectangle":
                currentFigure = qName;
                params.add(new Params());
                par = params.get(params.size() - 1);
                par.h = Integer.parseInt(atts.getValue("height"));
                par.w = Integer.parseInt(atts.getValue("width"));
                par.filled = Boolean.valueOf(atts.getValue("isFilled"));
                par.color = atts.getValue("color");
                currentElement = "";
                par.fig = "re";
                break;
            case "label":
                currentElement = qName;
                par.xL = Integer.parseInt(atts.getValue("x"));
                par.yL = Integer.parseInt(atts.getValue("y"));
                break;
            case "point":
                switch (currentFigure) {
                    case "rectangle":
                        par.x1 = Integer.parseInt(atts.getValue("x"));
                        par.y1 = Integer.parseInt(atts.getValue("y"));
                        break;
                    case "line":
                        par.x2 = Integer.parseInt(atts.getValue("x"));
                        par.y2 = Integer.parseInt(atts.getValue("y"));
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
        if (!params.isEmpty()) {
            Params par = params.get(params.size() - 1);
            switch (currentElement) {
                case "label":
                    par.label = string;
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

    SaxReading() {

        super();
        params = new ArrayList();

    }

}

class Params {

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
