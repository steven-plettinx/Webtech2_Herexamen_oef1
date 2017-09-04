package edu.ap.bolcom;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class BolComApplication extends Application {

    @Override
    public synchronized Restlet createInboundRoot() {

        Router router = new Router(getContext());

        router.attach("/bestellingen", BolComResource.class);
        //router.attach("/bestelling/{order_id}", BolComResource.class)

        return router;
    }

}
