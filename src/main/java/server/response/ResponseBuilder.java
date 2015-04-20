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
        StatusHeaderBuilder statusBuilder = new StatusHeaderBuilder(routeData, response);
        statusBuilder.assembleStatusHeader();

        ContentBuilder contentBuilder = new ContentBuilder(request.method(), routeData, response);
        contentBuilder.assembleContent();

        HeaderBuilder headerBuilder = new HeaderBuilder(routeData, response);
        headerBuilder.assembleHeaders();

        ParamProcessor paramProcessor = new ParamProcessor(request, routeData);
        paramProcessor.process();

        return response;
    }
}
