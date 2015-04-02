package main.java.server.response;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class ResponseBuilder {
    private ResponseWriter writer;
    private static final Map<Integer, String> statusCodeHeaders;

    static {
        statusCodeHeaders = new StatusCodes().headerLines;
    }

    public ResponseBuilder(ResponseWriter responseWriter) {
        writer = responseWriter;
    }

    public void createSuccessfulResponse() {
        try {
            writer.write(statusCodeHeaders.get(200));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFourOhFour() {
        try {
            writer.write(statusCodeHeaders.get(404));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void serveContent(String path) {
        Path filePath = Paths.get(path);

        try {
            writer.write(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

