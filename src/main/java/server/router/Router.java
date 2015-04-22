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
        RequestStringBuilder requestBuilder = new RequestStringBuilder(in);
        Request request = requestBuilder.getRequest();

        RouteDataBuilder routeDataBuilder = new RouteDataBuilder();
        RouteData routeData = routeDataBuilder.assembleRouteData(request);

        ResponseBuilder responseBuilder = new ResponseBuilder(request, routeData);
        Response response = responseBuilder.getResponse();

        ResponseSender sender = new ResponseSender(out);
        sender.send(response);
    }
}