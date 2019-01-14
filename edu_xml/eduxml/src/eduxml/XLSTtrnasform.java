/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eduxml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;

import javax.xml.transform.stream.StreamResult;

import javax.xml.transform.Result;
import javax.xml.transform.Source;

import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;

import org.xml.sax.SAXException;

/**
 *
 * @author mada
 */
public class XLSTtrnasform {

    /**
     * @param args the command line arguments
     */
    static Document document;

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        // TODO code application logic here

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Source xmlInput = new StreamSource(new File("src/transformedInfo.xml"));
        Source xsl = new StreamSource(new File("src/reverse.xsl"));
        Result xmlOutput = new StreamResult(new File("src/result.xml"));

        try {
            long startTime = System.currentTimeMillis();
            Transformer transformer = TransformerFactory.newInstance().newTransformer(xsl);
            transformer.transform(xmlInput, xmlOutput);
            long endTime = System.currentTimeMillis();
            double res = (endTime - startTime) / 1000.0;
            System.out.println("Total transformation time: " + res);
        } catch (TransformerException TE) {
            TE.getMessage();
        }
    }

}
