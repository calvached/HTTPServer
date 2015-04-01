package main.java.server.request;

import java.util.HashMap;

public class Request {
    public HashMap attributes;

    public Request(HashMap parsedAttributes) {
        attributes = parsedAttributes;
    }

    public String method() {
        return String.valueOf(attributes.get("requestMethod"));
    }

    public String url() {
        return String.valueOf(attributes.get("url"));
    }

    public String data() {
        return String.valueOf(attributes.get("data"));
    }
}
