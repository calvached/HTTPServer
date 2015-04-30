package main.java.server.response;

import main.java.server.request.Request;
import main.java.server.routeData.RouteData;

import java.io.*;

public class ParamProcessor {
    private final Request request;
    private final RouteData routeData;

    public ParamProcessor(Request clientRequest, RouteData clientRouteData) {
        request = clientRequest;
        routeData = clientRouteData;
    }

    public void process() throws IOException {
        if (!routeData.notFound() && !routeData.methodNotAllowed()) {
            if (methodIs("POST")) {
                add();
            }
            else if (methodIs("PUT") || methodIs("PATCH")) {
                update();
            }
            else if (methodIs("DELETE")) {
                delete();
            }
        }
    }

    private synchronized void delete() throws IOException {
        File file = new File(routeData.contentPath());

        FileWriter writer = new FileWriter(file, false);
        writer.write(" ");
        writer.close();
    }

    private synchronized void update() throws IOException {
        File file = new File(routeData.contentPath());

        FileWriter writer = new FileWriter(file, false);
        writer.write(request.params());
        writer.close();
    }

    private synchronized void add() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(routeData.contentPath(), "UTF-8");

        writer.println(request.params());
        writer.close();
    }

    private boolean methodIs(String method) {
        return request.method().equals(method);
    }
}
