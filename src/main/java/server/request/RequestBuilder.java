package main.java.server.request;

import java.util.HashMap;

public class RequestBuilder {
    private RequestReader reader;

    public RequestBuilder(RequestReader requestReader) {
        reader = requestReader;
    }

    public Request getRequest() {
        String requestString = reader.getConcatenatedRequest();
        HashMap attributes = parseAttributes(requestString);
        Request request = createRequest(attributes);

        return request;
    }

    private Request createRequest(HashMap attributes) {
        Request request = new Request(attributes);

        return request;
    }

    private HashMap parseAttributes(String requestString) {
        HashMap<String, String> attributes = new HashMap<String, String>();
        RequestParser parser = new RequestParser(requestString);

        attributes.put("requestMethod", parser.getRequestMethod());
        attributes.put("url", parser.getUrl());
        attributes.put("data", parser.getPostedData());

        return attributes;
    }
}
