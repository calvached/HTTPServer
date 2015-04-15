package main.java.server.response;

import java.io.IOException;
import java.util.HashMap;

public class ResponseBuilder {
    private final HashMap request;
    private final HashMap<String, Object> response = new HashMap<>();

    public ResponseBuilder(HashMap clientRequest) {
        request = clientRequest;
    }

    public HashMap getResponse() throws IOException {
        StatusHeaderBuilder statusBuilder = new StatusHeaderBuilder(request, response);
        statusBuilder.assembleStatusHeader();

        ContentBuilder contentBuilder = new ContentBuilder(request, response);
        contentBuilder.assembleContent();

        ParamProcessor paramProcessor = new ParamProcessor(request);
        paramProcessor.process();

        return response;
    }
}
