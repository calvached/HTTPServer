package main.java.server.response;

import java.io.IOException;

public class ResponseBuilder {
    private ResponseWriter writer;

    public ResponseBuilder(ResponseWriter responseWriter) {
        writer = responseWriter;
    }

    public void createSuccessfulResponse() {
        try {
            writer.write("HTTP/1.1 200 OK");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
