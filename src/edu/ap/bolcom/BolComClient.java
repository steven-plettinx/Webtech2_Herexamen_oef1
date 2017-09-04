package edu.ap.bolcom;

import edu.ap.xmlparser.XMLParser;
import org.restlet.resource.ClientResource;

public class BolComClient {

    public static void main(String[] args) {

        try {
            ClientResource resource = new ClientResource("http://localhost:8181/BolCom/bestellingen");

            //add two  new orders
            String order = "<order nameCustomer=\"Steven Plettinx\" address=\"Noneofyourdamnbusiness street 1\"";
            order += "date=\"04/09/2017\" nameProduct=\"A lifesize poster of Margot Robbie\" quantity=\"1\"></order>";

            resource.post(order);

            String order2 = "<order nameCustomer=\"Steven Plettinx\" address=\"Noneofyourdamnbusiness avenue 1\"";
            order2 += "date=\"05/09/2017\" nameProduct=\"Bodypillow of Emma Stone\" quantity=\"1\"></order>";

            resource.post(order2);

            // get the response
            System.out.println(resource.getResponseEntity().getText());
        }
        catch (Exception e) {
            System.out.println("In main : " + e.getMessage());
        }
    }

}
