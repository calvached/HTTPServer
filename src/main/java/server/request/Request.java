package main.java.server.request;

import java.util.HashMap;

public class Request {
    private final String method;
    private final String path;
    private final String params;
    private final HashMap headers;

    public Request(HashMap<String, Object> attributes) {
        method = (String) attributes.get("method");
        path = (String) attributes.get("path");
        params = (String) attributes.get("params");
        headers = (HashMap) attributes.get("headers");
    }

    public String method() {
        return method;
    }

    public String path() {
        return path;
    }

    public String params() {
        return params;
    }

    public HashMap headers() {
        return headers;
    }
}
