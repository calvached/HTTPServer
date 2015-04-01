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
        }
        else if (request.url().equals("/sample.pdf")) {
            builder.serveContent("public" + request.url());
        } else {
            builder.createFourOhFour();
        }
    }
}
