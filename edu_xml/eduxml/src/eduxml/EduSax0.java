/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class EduSax0 extends DefaultHandler {

    public List<Note> notes = new ArrayList<>();
    private String currentTag = "";

    @Override
    public void startDocument() throws SAXException {
        //notes = new ArrayList<Note>();
    }

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startElement(String namespaceURI, String localName,
            String qName, Attributes atts) throws SAXException {
        switch (qName) {
            case "notes":
                break;
            case "note":
                notes.add(new Note());
                break;
            case "to":
            case "from":
            case "heading":
            case "body":
                currentTag = qName;
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
        Note note = notes.get(notes.size() - 1);
        switch (currentTag) {
            case "to":
                note.to = string;
                break;
            case "from":
                note.from = string;
                break;
            case "heading":
                note.heading = string;
                break;
            case "body":
                note.body = string;
                break;
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
        String filename = "notes.xml";
        SAXParserFactory spf = SAXParserFactory.newInstance();
        //spf.setNamespaceAware(true);
        spf.setValidating(true);
        SAXParser saxParser = spf.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        EduSax0 saxHandler = new EduSax0();
        xmlReader.setContentHandler(saxHandler);
        xmlReader.setErrorHandler(saxHandler);
        xmlReader.parse(filename);
        saxHandler.notes.forEach((note) -> {
            System.out.println("Note  : " + note.heading);
            System.out.println("    to: " + note.to);
            System.out.println("  from: " + note.from);
            System.out.println("        " + note.body);
        });

    }

    public static class Note {
        public String to;
        public String from;
        public String heading;
        public String body;
    }

}
