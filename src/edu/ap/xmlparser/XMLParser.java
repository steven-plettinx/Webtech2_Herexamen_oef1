package edu.ap.xmlparser;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.InputSource;

import java.io.*;


public class XMLParser {
    private String INPUTFILE = "/home/steven/orders.xml";

    /** Get all races from the xml file and return them
     * in html format
     */
    public String getOrders() {
        File inputFile = new File(INPUTFILE);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            String result = "<h2>Orders</h2>";

            NodeList orders = doc.getElementsByTagName("order");

            for (int i = 0; i < orders.getLength(); i++) {
                Node nNode = orders.item(i);
                Element eElement = (Element) nNode;

                result += "<br/><b>Naam klant : </b>" + eElement.getAttribute("nameCustomer");
                result += "<br/><b>Adres : </b>" + eElement.getAttribute("address");
                result += "<br/><b>Datum bestelling : </b>" + eElement.getAttribute("date");
                result += "<br/><b>Productnaam : </b>" + eElement.getAttribute("nameProduct");
            result += "<br/><b>Quantity : </b>" + eElement.getAttribute("quantity");
                result += "<br/>";
            }

            return result;
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    /** Get the race with race_id and return them
     * in html format
     */
    public String getOrder(String order_id) {
        File inputFile = new File(INPUTFILE);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            String result = "<h2>Order</h2>";

            NodeList orders = doc.getElementsByTagName("order");

            for (int i = 0; i < orders.getLength(); i++) {

                Node nNode = orders.item(i);
                Element eElement = (Element) nNode;

                if(eElement.getAttribute("id").equalsIgnoreCase(order_id)) {
                    result += "<br/><b>Naam klant : </b>" + eElement.getAttribute("nameCustomer");
                    result += "<br/><b>Adres : </b>" + eElement.getAttribute("address");
                    result += "<br/><b>Datum bestelling : </b>" + eElement.getAttribute("date");
                    result += "<br/><b>Productnaam : </b>" + eElement.getAttribute("nameProduct");
                    result += "<br/><b>Quantity : </b>" + eElement.getAttribute("quantity");
                    result += "<br/>";
                }
            }

            return result;
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    /** Delete the rqce with race_id and return all races in
     * valid format
     */
/*    public String deleteRace(String race_id) {
        File inputFile = new File(INPUTFILE);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList races = doc.getElementsByTagName("race");

            for (int i = 0; i < races.getLength(); i++) {

                Node nNode = races.item(i);
                Element eElement = (Element)nNode;

                if(eElement.getAttribute("id").equalsIgnoreCase(race_id)) {
                    eElement.getParentNode().removeChild(eElement);
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(new StringWriter());
                    transformer.transform(source, result);
                }
            }

            return this.getRaces();
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }*/

    /**
     * Add a race and return all races in
     * html format
     */
    public String addOrder(String xml) {
        File inputFile = new File(INPUTFILE);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        // set validating false to enable copying
        // node from one document to another
        dbFactory.setValidating(false);
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc1 = dBuilder.parse(inputFile);
            Document doc2 = dBuilder.parse(new InputSource(new StringReader(xml)));
            Element element = doc2.getDocumentElement();
            // imports a node from doc2 document to doc1, without altering
            // or removing the source node from the original document
            Node copiedNode = doc1.importNode(element, true);
            // adds the node to the end of the list of children of this node
            doc1.getDocumentElement().appendChild(copiedNode);

	        /*FileWriter fw = new FileWriter(INPUTFILE);
	        fw.write(doc1.toString());
	        fw.close();*/
            // write the new document to file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc1);
            StreamResult result = new StreamResult(new File(INPUTFILE));
            transformer.transform(source, result);

            return this.getOrders();
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }


}
