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
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author nag
 */
public class EduDom implements ErrorHandler {

    public void warning(SAXParseException spe) throws SAXException {
        System.out.println("Warning: " + spe.toString());
    }

    public void error(SAXParseException spe) throws SAXException {
        throw new SAXException("Error: " + spe.toString());
    }

    public void fatalError(SAXParseException spe) throws SAXException {
        throw new SAXException("Fatal Error: " + spe.toString());
    }

    public static class Note {

        public String to;
        public String from;
        public String heading;
        public String body;
    }

    public List<Note> notes = new ArrayList<Note>();

    public static void main(String[] args) throws Exception {
        String filename = "notes.xml";

        EduDom eduDom = new EduDom();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //dbf.setNamespaceAware(true);
        dbf.setValidating(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        db.setErrorHandler(eduDom);

        Document doc = db.parse(new File(filename));


        NodeList docNotes = doc.getElementsByTagName("note");
        for (int i = 0; i < docNotes.getLength(); i++) {
            Note note = new Note();
            eduDom.notes.add(note);

            Node docNote = docNotes.item(i);
            NodeList docNoteSubTags = docNote.getChildNodes();
            for (int j = 0; j < docNoteSubTags.getLength(); j++) {
                Node subTag = docNoteSubTags.item(j);
                if (subTag.getNodeType() == Node.ELEMENT_NODE) {
                    switch (subTag.getNodeName()) {
                        case "to":
                            note.to = subTag.getTextContent();
                            break;
                        case "from":
                            note.from = subTag.getTextContent();
                            break;
                        case "heading":
                            note.heading = subTag.getTextContent();
                            break;
                        case "body":
                            note.body = subTag.getTextContent();
                            break;
                    }
                }
            }

        }

        eduDom.notes.forEach((note) -> {
            System.err.println("Note  : " + note.heading);
            System.err.println("    to: " + note.to);
            System.err.println("  from: " + note.from);
            System.err.println("        " + note.body);
        });

    }

}
