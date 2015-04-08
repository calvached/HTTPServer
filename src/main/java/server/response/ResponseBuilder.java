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
            writer.flush();
            writer.write(statusCodeHeaders.get(200));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFourOhFour() {
        try {
            writer.flush();
            writer.write(statusCodeHeaders.get(404));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void serveContent(String path, String contentType) {
        Path filePath = Paths.get(path);

        try {
            writer.flush();
            writer.write(statusCodeHeaders.get(200) + "\r\n");
            writer.write("Content-Type: " + contentType + "\r\n");
            writer.write("\r\n");
            writer.write(filePath);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
