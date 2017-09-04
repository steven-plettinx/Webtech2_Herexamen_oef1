package edu.ap.bolcom;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import edu.ap.xmlparser.XMLParser;


public class BolComResource extends ServerResource {

    @Get("html")
    public String getOrders() {
        XMLParser parser = new XMLParser();
        return parser.getOrders();
    }

    @Post("txt")
    public String addOrder(String order) {
        XMLParser parser = new XMLParser();
        return parser.addOrder(order);
    }

}
