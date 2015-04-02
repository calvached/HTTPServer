package main.java.server.method;

import main.java.server.request.Request;
import main.java.server.response.ResponseBuilder;

import java.io.IOException;
import java.util.HashMap;

public class ConnectionHandler {
    public HashMap<String, Runnable> methods = new HashMap();
    private Request request;
    private ResponseBuilder responseBuilder;

    public ConnectionHandler(Request req, ResponseBuilder resBuilder){
        request = req;
        responseBuilder = resBuilder;

        methods.put("GET", ConnectionHandler.this::getMethod);
        methods.put("POST", ConnectionHandler.this::postMethod);
        methods.put("PUT", ConnectionHandler.this::putMethod);
        methods.put("DELETE", ConnectionHandler.this::deleteMethod);
    }

    private void getMethod() {
        GetHandler get = new GetHandler(responseBuilder);

        try {
            get.handle(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void postMethod() {
        PostHandler get = new PostHandler(responseBuilder);

        get.handle(request);
    }

    private void putMethod() {
        PutHandler put = new PutHandler(responseBuilder);

        try {
            put.handle(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteMethod() {
        DeleteHandler delete = new DeleteHandler(responseBuilder);

        try {
            delete.handle(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}