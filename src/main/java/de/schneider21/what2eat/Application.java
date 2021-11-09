package de.schneider21.what2eat;

import de.schneider21.what2eat.framework.HttpServer;
import de.schneider21.what2eat.meal.api.MealController;
import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        final HttpServer httpServer = new HttpServer(8080);
        httpServer.registerRestController(new MealController());
        httpServer.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("Application: Server running on localhost:8080/...");
    }
}
