package main.java.server.method;

import main.java.server.request.Request;
import main.java.server.response.ResponseBuilder;

import java.io.IOException;

public class GetHandler {
    private ResponseBuilder builder;

    public GetHandler(ResponseBuilder responseBuilder) {
        builder = responseBuilder;
    }

    public void handle(Request request) throws IOException {
        if (request.url().equals("/")) {
            builder.createSuccessfulResponse();
        } else {
            builder.createFourOhFour();
        }
    }
}
