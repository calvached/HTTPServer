package main.java.server.response;

import java.util.HashMap;
import java.util.Map;

public class StatusCodes {
    public final Map<Integer, String> headers = new HashMap<>();

    {
        headers.put(200, "200 OK");
        headers.put(302, "302 Found");
        headers.put(404, "404 Not Found");
        headers.put(405, "405 Method Not Allowed");
    }
}
