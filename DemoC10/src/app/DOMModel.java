/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author admin
 */
public class DOMModel {

    private static String path = "src\\app\\data.xml";

    public static Document parserData() {
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            File f = new File(path);
            doc = builder.parse(f);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(DOMModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doc;
    }

    public static ArrayList<Node> search(String name) {
        Document doc = parserData();
        ArrayList<Node> result = new ArrayList<Node>();
        NodeList temp = doc.getElementsByTagName("ProductName");
        for (int i = 0; i < temp.getLength(); i++) {
            String text = temp.item(i).getTextContent();
            if (text.startsWith(name) || text.endsWith(name)) {
                Node p = temp.item(i).getParentNode();
                result.add(p);
            }
        }
        return result;
    }
}
