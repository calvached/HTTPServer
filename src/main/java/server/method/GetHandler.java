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
        Path filePath = Paths.get("../cob_spec/public" + request.url());

        if (isNotFileOrDirectory(filePath)) {
           serveResponse(request);
        } else {
           String mimeType = getFileMimeTypeFromName(filePath.getFileName().toString());
           serveContent(request, mimeType);
        }
    }

    private boolean isNotFileOrDirectory(Path path) {
        return Files.isDirectory(path) || Files.notExists(path);
    }

    private void serveContent(Request request, String mimeType) {
        builder.serveContent("../cob_spec/public" + request.url(), mimeType);
    }

    private void serveResponse(Request request) {
        if (request.url().equals("/")) {
            builder.createSuccessfulResponse();
        }
        else if (request.url().equals("/form")) {
            builder.serveContent("../cob_spec/public/postData.txt", "text/plain");
        } else {
            builder.createFourOhFour();
        }
    }

    private String getFileMimeTypeFromName(String fileName) {
        String mimeType = "";

        String[] lines = fileName.split("\\.");

        if (lines.length > 1) {
            mimeType += "image/" + lines[1];
        } else {
           mimeType += "text/plain";
        }

        return mimeType;
    }
}