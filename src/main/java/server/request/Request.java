package main.java.server.request;

import java.util.HashMap;

public class Request {
    private final String method;
    private final String path;
    private final String params;

    public Request(HashMap<String, String> attributes) {
        method = attributes.get("method");
        path = attributes.get("path");
        params = attributes.get("params");
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
}
