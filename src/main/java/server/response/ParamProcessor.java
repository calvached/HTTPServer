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

    public void process() {
        if (!routeData.notFound() && !routeData.methodNotAllowed()) {
            if (methodIs("POST")) {
                add();
            }
            else if (methodIs("PUT")) {
                update();
            }
            else if (methodIs("DELETE")) {
                delete();
            }
        }
    }

    private void delete() {
        File file = new File(routeData.contentPath());

        FileWriter writer;
        try {
            writer = new FileWriter(file, false);
            writer.write(" ");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void update() {
        File file = new File(routeData.contentPath());

        FileWriter writer;
        try {
            writer = new FileWriter(file, false);
            writer.write(request.params());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void add() {
        PrintWriter writer;
        try {
            writer = new PrintWriter(routeData.contentPath(), "UTF-8");
            writer.println(request.params());
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private boolean methodIs(String method) {
        return request.method().equals(method);
    }
}
