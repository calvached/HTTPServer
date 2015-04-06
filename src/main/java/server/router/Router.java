package main.java.server.router;

import main.java.server.method.ConnectionHandler;
import main.java.server.request.Request;
import main.java.server.request.RequestStringBuilder;
import main.java.server.request.RequestStringReader;
import main.java.server.response.ResponseBuilder;
import main.java.server.response.ResponseWriter;

import java.io.*;
import java.util.Map;

public class Router {
    public void handleTrafficFor(InputStream in, OutputStream out){
        RequestStringBuilder requestBuilder = createRequestBuilder(in);
        ResponseBuilder responseBuilder = createResponseBuilder(out);
        Request request = requestBuilder.getRequest();

        ConnectionHandler connectionHandler =
                new ConnectionHandler(request, responseBuilder);

        Map<String, Runnable> httpMethods = connectionHandler.methods;

        if (httpMethods.get(request.method()) == null) {
            responseBuilder.createFourOhFour();
        } else {
            httpMethods.get(request.method()).run();
        }
    }

    private RequestStringBuilder createRequestBuilder(InputStream in) {
        RequestStringBuilder builder =
                new RequestStringBuilder(
                        new RequestStringReader(
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