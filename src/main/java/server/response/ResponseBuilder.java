package main.java.server.response;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ResponseBuilder {
    private ResponseWriter writer;
    private static final Map<Integer, String> statusCodes;

    static {
        Map<Integer, String> statusMap = new HashMap<>();
        statusMap.put(200, "HTTP/1.1 200 OK");
        statusMap.put(404, "HTTP/1.1 404 Not Found");

        statusCodes = Collections.unmodifiableMap(statusMap);
    }

    public ResponseBuilder(ResponseWriter responseWriter) {
        writer = responseWriter;
    }

    public void createSuccessfulResponse() {
        try {
            writer.write(statusCodes.get(200));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFourOhFour() {
        try {
            writer.write(statusCodes.get(404));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

