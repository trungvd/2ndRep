/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author admin
 */
public class DemoDOM {
    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            String path = "src\\app\\Data.xml";
            File f = new File(path);
            Document doc = builder.parse(f);
            System.out.println("Root name: " + doc.getDocumentElement().getNodeName());
            NodeList products = doc.getElementsByTagName("Product");
            System.out.println("Number of products: " + products.getLength());
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(DemoDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
