package main.java.server.response.statusHeader;

import java.util.HashMap;
import java.util.Map;

public class StatusCodes {
    public final Map<Integer, String> headers = new HashMap<>();

    {
        headers.put(200, "200 OK");
        headers.put(204, "204 No Content");
        headers.put(206, "206 Partial Content");
        headers.put(302, "302 Found");
        headers.put(400, "400 Bad Request");
        headers.put(401, "401 Unauthorized");
        headers.put(404, "404 Not Found");
        headers.put(405, "405 Method Not Allowed");
    }
}
