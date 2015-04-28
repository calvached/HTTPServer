package main.java.server.response;

import main.java.server.request.Request;
import main.java.server.routeData.RouteData;

import java.io.IOException;

public class ResponseBuilder {
    private final Request request;
    private final RouteData routeData;
    private final Response response = new Response();

    public ResponseBuilder(Request clientRequest, RouteData clientRouteData) {
        request = clientRequest;
        routeData = clientRouteData;
    }

    public Response getResponse() throws IOException {
        statusBuilder().assembleStatusHeader();
        contentBuilder().assembleContent();
        headerBuilder().assembleHeaders();
        paramProcessor().process();

        return response;
    }

    private StatusHeaderBuilder statusBuilder() {
        return new StatusHeaderBuilder(routeData, response);
    }

    private ContentBuilder contentBuilder() {
        return new ContentBuilder(request, routeData, response);
    }

    private HeaderBuilder headerBuilder() {
        return new HeaderBuilder(routeData, response);
    }

    private ParamProcessor paramProcessor() {
        return new ParamProcessor(request, routeData);
    }
}
