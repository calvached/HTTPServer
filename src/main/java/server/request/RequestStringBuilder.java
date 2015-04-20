package main.java.server.request;

import java.io.InputStream;
import java.util.HashMap;

public class RequestStringBuilder {
    private InputStream in;

    public RequestStringBuilder(InputStream inputStream) {
        in = inputStream;
    }

    public Request getRequest() {
       return new Request(getAttributes(requestString()));
    }

    private HashMap getAttributes(String requestString) {
        HashMap<String, String> attributes = new HashMap<>();
        RequestParser parser = new RequestParser(requestString);

        attributes.put("method", parser.getRequestMethod());
        attributes.put("path", parser.getUrl());
        attributes.put("params", parser.getPostedData());

        return attributes;
    }

    private String requestString() {
        return reader().getConcatenatedRequest();
    }

    private RequestStringReader reader() {
        return new RequestStringReader(in);
    }
}
