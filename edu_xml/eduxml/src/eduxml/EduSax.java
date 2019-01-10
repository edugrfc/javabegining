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
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * <>
 * @author nag
 */
public class EduSax extends DefaultHandler {

    public static class Note {
        public String to;
        public String from;
        public String heading;
        public String body;
    }

    public List<Note> notes = new ArrayList<Note>();
    private String currentTag = "";

    @Override
    public void startDocument() throws SAXException {
        //notes = new ArrayList();
    }

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startElement(String namespaceURI, String localName,
            String qName, Attributes atts) throws SAXException {
        //System.out.println("eduxml.EduSax.startElement():"+ qName);
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
//            default:

        }

    }

    @Override
    public void endElement(String namespaceURI, String localName,
            String qName) throws SAXException {
        //System.out.print("</" + qName + ">");
        //currentTag = "";
    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        char[] s = Arrays.copyOfRange(ch, start, start + length);
        String string = new String(s);
        //if (notes.size() != 0) {

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
        //}

        //System.out.print("===" + new String(ch) + "===");
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length)
            throws SAXException {
    }
    
//    public void warning(SAXParseException spe) throws SAXException {
//        System.out.println("Warning: " + spe.toString());
//    }
//
//    public void error(SAXParseException spe) throws SAXException {
//        throw new SAXException("Error: " + spe.toString());
//    }
//
//    public void fatalError(SAXParseException spe) throws SAXException {
//        throw new SAXException("Fatal Error: " + spe.toString());
//    }
    

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        String filename = "notes.xml";

        SAXParserFactory spf = SAXParserFactory.newInstance();
        //spf.setNamespaceAware(true);
//        spf.setValidating(true);
        SAXParser saxParser = spf.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();

        EduSax saxHandler = new EduSax();

        xmlReader.setContentHandler(saxHandler);
//        xmlReader.setErrorHandler(saxHandler);

        xmlReader.parse(filename);

        saxHandler.notes.forEach((note) -> {
            System.err.println("Note  : " + note.heading);
            System.err.println("    to: " + note.to);
            System.err.println("  from: " + note.from);
            System.err.println("        " + note.body);
        });

    }

}
