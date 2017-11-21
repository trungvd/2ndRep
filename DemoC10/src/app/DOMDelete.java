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
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author admin
 */
public class DOMDelete {

    public static void main(String[] args) {
        try {
            String path = "src\\app\\Data.xml";
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse(new File(path));
            String id = "P04";
            Node delNode = searchById(id, doc);
            doc.getDocumentElement().removeChild(delNode);
            saveData(doc, path);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(DOMDelete.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static Node searchById(String id, Document doc) {
        NodeList lst = doc.getElementsByTagName("ProductID");
        for (int i = 0; i < lst.getLength(); i++) {
            String content = lst.item(i).getTextContent();
            if (content.equalsIgnoreCase(id)) {
                Node p = lst.item(i).getParentNode();
                return p;
            }
        }
        return null;
    }

    public static void saveData(Document doc, String path) {
        try {
            DOMSource source = new DOMSource(doc);
            File f = new File(path);
            Result result = new StreamResult(f);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println("Save success!");
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(DOMDelete.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(DOMDelete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
