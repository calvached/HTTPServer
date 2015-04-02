package main.java.server.response;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StatusCodes {
    public static final Map<Integer, String> headerLines;

    static {
        Map<Integer, String> httpCodes = new HashMap<>();
        httpCodes.put(200, "HTTP/1.1 200 OK");
        httpCodes.put(404, "HTTP/1.1 404 Not Found");

        headerLines = Collections.unmodifiableMap(httpCodes);
    }
}
