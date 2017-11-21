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
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author admin
 */
public class DemoDOMCreate {

    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        String path = "src\\app\\studentInfo.xml";
        File f = new File(path);
        if (!f.exists()) {

            try {
                DocumentBuilder builder = dbf.newDocumentBuilder();
                Document doc = builder.newDocument();

                Element root = doc.createElement("StudentList");
                Element student = doc.createElement("Student");

                Element studentID = doc.createElement("StudentID");
                Text idValue = doc.createTextNode("A001");
                studentID.appendChild(idValue);

                Element studentName = doc.createElement("StudentName");
                Text nameValue = doc.createTextNode("Demo");
                studentName.appendChild(nameValue);

                Element mark = doc.createElement("Mark");
                Text markValue = doc.createTextNode("20");
                mark.appendChild(markValue);

                student.appendChild(studentID);
                student.appendChild(studentName);
                student.appendChild(mark);

                root.appendChild(student);

                doc.appendChild(root);

                DOMSource source = new DOMSource(doc);

                Result result = new StreamResult(f);
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(source, result);
                System.out.println("Write data to file success: " + path);
            } catch (ParserConfigurationException | TransformerConfigurationException ex) {
                Logger.getLogger(DemoDOMCreate.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                Logger.getLogger(DemoDOMCreate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            try {
                DocumentBuilder builder = dbf.newDocumentBuilder();
                Document doc = builder.parse(f);
                Element root = doc.getDocumentElement();
                Element student = doc.createElement("Student");

                Element studentID = doc.createElement("StudentID");
                Text idValue = doc.createTextNode("A003");
                studentID.appendChild(idValue);

                Element studentName = doc.createElement("StudentName");
                Text nameValue = doc.createTextNode("Demo3");
                studentName.appendChild(nameValue);

                Element mark = doc.createElement("Mark");
                Text markValue = doc.createTextNode("40");
                mark.appendChild(markValue);

                student.appendChild(studentID);
                student.appendChild(studentName);
                student.appendChild(mark);

                root.appendChild(student);

                DOMSource source = new DOMSource(doc);

                Result result = new StreamResult(f);
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(source, result);
                System.out.println("Write data to file success: " + path);
            } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
                Logger.getLogger(DemoDOMCreate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
