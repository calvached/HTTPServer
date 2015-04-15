package main.java.server.response;

import java.util.HashMap;

public class HeaderBuilder {
    private final HashMap<String, String> request;
    private final HashMap<String, Object> response;

    public HeaderBuilder(HashMap<String, String> clientRequest, HashMap<String, Object> serverResponse) {
        request = clientRequest;
        response = serverResponse;
    }

    public void assembleHeaders() {
        if (request.containsKey("redirect")) {
            response.put("header", "Location: http://localhost:5000" + request.get("path") + "\r\n");
        } else if (request.containsKey("options")) {
            response.put("header", "Allow: " + request.get("options") + "\r\n");
        }
    }
}
