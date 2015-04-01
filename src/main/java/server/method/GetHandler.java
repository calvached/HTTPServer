package main.java.server.method;

import main.java.server.request.Request;
import main.java.server.response.ResponseBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetHandler {
    private ResponseBuilder builder;

    public GetHandler(ResponseBuilder responseBuilder) {
        builder = responseBuilder;
    }

    public void handle(Request request) throws IOException {
        Path filePath = Paths.get("public" + request.url());

        if (isNotFileOrDirectory(filePath)) {
           serveResponse(request);
        } else {
           serveContent(request);
        }
    }

    private boolean isNotFileOrDirectory(Path path) {
        return Files.isDirectory(path) || Files.notExists(path);
    }

    private void serveContent(Request request) {
        builder.serveContent("public" + request.url());
    }

    private void serveResponse(Request request) {
        if (request.url().equals("/")) {
            builder.createSuccessfulResponse();
        } else {
            builder.createFourOhFour();
        }
    }
}
