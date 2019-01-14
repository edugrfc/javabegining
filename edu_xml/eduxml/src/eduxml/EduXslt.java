/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eduxml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author nag
 */
public class EduXslt {
    
public static void main(String[] args) throws Exception {
    InputStream in = new FileInputStream("input.xml");
    OutputStream out = new FileOutputStream("output.xml");
    
    Source xmlInput = new StreamSource(in);
    Source xsl = new StreamSource(new File("transform.xsl"));
    Result xmlOutput = new StreamResult(out);
    
    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer transformer = tf.newTransformer(xsl);
    transformer.transform(xmlInput, xmlOutput);
}
}
