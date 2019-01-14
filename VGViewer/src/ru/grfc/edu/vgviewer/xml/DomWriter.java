package ru.grfc.edu.vgviewer.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
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
import ru.grfc.edu.vgviewer.figures.*;
/**
 *
 * @author mada
 */
public class DomWriter {

    public DomWriter(File file, List<Figure> figures) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();
        Element figuresNode = document.createElement("figures");
        document.appendChild(figuresNode);
        
        for(Figure fig : figures){
            
            if (fig instanceof Line) {
                Element line = document.createElement("line");
                line.setAttribute("x", String.valueOf(fig.getFirstPoint().getX()));
                line.setAttribute("y", String.valueOf(fig.getFirstPoint().getY()));
                String color = "rgb(" + fig.getColor().getRed() + "," + fig.getColor().getGreen() + "," + fig.getColor().getBlue() + ")";
                line.setAttribute("color", color);
                Element point = document.createElement("point");
                point.setAttribute("x", String.valueOf(((Line) fig).getLastPoint().getX()));
                point.setAttribute("y", String.valueOf(((Line) fig).getLastPoint().getY()));
                Element label = document.createElement("label");
                label.setAttribute("x", String.valueOf(fig.getTextPoint().getX()));
                label.setAttribute("y", String.valueOf(fig.getTextPoint().getY()));
                label.appendChild(document.createTextNode(fig.getText()));
                figuresNode.appendChild(line);
                line.appendChild(point);
                line.appendChild(label);
            } else if (fig instanceof Rectangle) {
                Element rectangle = document.createElement("rectangle");
                rectangle.setAttribute("width", String.valueOf(((Rectangle) fig).getHeight()));
                rectangle.setAttribute("height", String.valueOf(((Rectangle) fig).getWidth()));
                String color = "rgb(" + fig.getColor().getRed() + "," + fig.getColor().getGreen() + "," + fig.getColor().getBlue() + ")";
                rectangle.setAttribute("color", color);
                rectangle.setAttribute("isFilled", String.valueOf(fig.isFill()));
                Element point = document.createElement("point");
                point.setAttribute("x", String.valueOf(fig.getFirstPoint().getX()));
                point.setAttribute("y", String.valueOf(fig.getFirstPoint().getY()));
                Element label = document.createElement("label");
                label.setAttribute("x", String.valueOf(fig.getTextPoint().getX()));
                label.setAttribute("y", String.valueOf(fig.getTextPoint().getY()));
                label.appendChild(document.createTextNode(fig.getText()));
                figuresNode.appendChild(rectangle);
                rectangle.appendChild(point);
                rectangle.appendChild(label);

            }
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        Transformer transformer = transformerFactory.newTransformer();

        DOMSource domSource = new DOMSource(document);

        StreamResult streamResult = new StreamResult(file);
        
        transformer.transform(domSource, streamResult);
        
    }

}
