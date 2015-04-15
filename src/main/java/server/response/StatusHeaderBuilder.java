package main.java.server.response;

import java.util.HashMap;

public class StatusHeaderBuilder {
    private final String httpVersion = "HTTP/1.1 ";
    private final StatusCodes statusCodes = new StatusCodes();
    private final HashMap<String, String> request;
    private final HashMap<String, Object> response;

    public StatusHeaderBuilder(HashMap<String, String> clientRequest, HashMap<String, Object> serverResponse) {
        request = clientRequest;
        response = serverResponse;
    }

    public void assembleStatusHeader() {
        if (isFlaggedWith("redirect")) {
            assignStatusHeader(302);
        } else if (isFlaggedWith("notFound")) {
            assignStatusHeader(404);
        }
        else {
            assignStatusHeader(200);
        }
    }

    private boolean isFlaggedWith(String flagMessage) {
        return request.containsKey(flagMessage);
    }

    private void assignStatusHeader(int statusCode) {
        response.put("statusHeader", getStatusHeaderLine(statusCode));
    }

    private String getStatusHeaderLine(int statusCode) {
        return httpVersion + statusCodes.headers.get(statusCode);
    }
}
