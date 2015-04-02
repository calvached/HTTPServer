package main.java.server.router;

import main.java.server.method.GetHandler;
import main.java.server.method.PostHandler;
import main.java.server.method.PutHandler;
import main.java.server.request.Request;
import main.java.server.request.RequestBuilder;
import main.java.server.request.RequestReader;
import main.java.server.response.ResponseBuilder;
import main.java.server.response.ResponseWriter;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Router {
    private Map<String, Runnable> requestMethods = new HashMap();
    private Request request;
    private ResponseBuilder responseBuilder;

    public Router() {
        requestMethods.put("GET", Router.this::getMethod);
        requestMethods.put("POST", Router.this::postMethod);
        requestMethods.put("PUT", Router.this::putMethod);
    }

    public void handleTrafficFor(InputStream in, OutputStream out){
        RequestBuilder requestBuilder = createRequestBuilder(in);
        responseBuilder = createResponseBuilder(out);
        request = requestBuilder.getRequest();

        if (requestMethods.get(request.method()) == null) {
            responseBuilder.createFourOhFour();
        } else {
            requestMethods.get(request.method()).run();
        }
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

    private RequestBuilder createRequestBuilder(InputStream in) {
        RequestBuilder builder =
                new RequestBuilder(
                        new RequestReader(
                                new BufferedReader(
                                        new InputStreamReader(in))));

        return builder;
    }

    private ResponseBuilder createResponseBuilder(OutputStream out) {
        ResponseBuilder builder =
                new ResponseBuilder(
                        new ResponseWriter(out));

        return builder;
    }
}