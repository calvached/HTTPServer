package main.java.server.method;

import main.java.server.request.Request;
import main.java.server.response.ResponseBuilder;

public class PostHandler {
    private ResponseBuilder builder;

    public PostHandler(ResponseBuilder responseBuilder) {
        builder = responseBuilder;
    }

    public void handle(Request request) {
        if (request.url().equals("/form")) {
            builder.createSuccessfulResponse();
        } else {
            builder.createFourOhFour();
        }
    }
}
