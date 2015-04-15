package main.java.server.request;

import java.io.InputStream;
import java.util.HashMap;

public class RequestStringBuilder {
    private InputStream in;

    public RequestStringBuilder(InputStream inputStream) {
        in = inputStream;
    }

    public HashMap getRequest() {
        RequestStringReader reader = new RequestStringReader(in);
        String requestString = reader.getConcatenatedRequest();

        HashMap attributes = parseAttributes(requestString);
        RequestResolver resolver = new RequestResolver();

        HashMap request = resolver.resolve(attributes);

        return request;
    }

    private HashMap parseAttributes(String requestString) {
        HashMap<String, String> attributes = new HashMap<String, String>();
        RequestParser parser = new RequestParser(requestString);

        attributes.put("method", parser.getRequestMethod());
        attributes.put("path", parser.getUrl());
        attributes.put("params", parser.getPostedData());

        return attributes;
    }
}
