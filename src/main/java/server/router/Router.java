package main.java.server.router;

import main.java.server.request.Request;
import main.java.server.request.RequestStringBuilder;
import main.java.server.response.Response;
import main.java.server.response.ResponseBuilder;
import main.java.server.response.ResponseSender;
import main.java.server.routeData.RouteData;
import main.java.server.routeData.RouteDataBuilder;

import java.io.*;

public class Router {
    private final InputStream in;
    private final OutputStream out;

    public Router(InputStream in, OutputStream out) {
        this.in = in;
        this.out = out;
    }
    public void directTrafficFor() throws IOException {
        Request request = getRequest();
        RouteData routeData = getRouteData(request);
        Response response = getResponse(request, routeData);

        send(response);
    }

    private Request getRequest() {
        RequestStringBuilder requestBuilder = new RequestStringBuilder(in);

        return requestBuilder.getRequest();
    }

    private RouteData getRouteData(Request request) {
        RouteDataBuilder routeDataBuilder = new RouteDataBuilder();

        return routeDataBuilder.assembleRouteData(request);
    }

    private Response getResponse(Request request, RouteData routeData) throws IOException {
        ResponseBuilder responseBuilder = new ResponseBuilder(request, routeData);

        return responseBuilder.getResponse();
    }

    private void send(Response response) throws IOException {
        ResponseSender sender = new ResponseSender(out);

        sender.send(response);
    }
}