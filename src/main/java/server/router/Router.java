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
    public void directTrafficFor(InputStream in, OutputStream out) throws IOException {
        RequestStringBuilder requestBuilder = new RequestStringBuilder(in);
        Request request = requestBuilder.getRequest();

        RouteDataBuilder routeDataBuilder = new RouteDataBuilder();
        // possibly refactor this so that the RDB is initialized with request
        RouteData routeData = routeDataBuilder.assembleRouteData(request);

        ResponseBuilder responseBuilder = new ResponseBuilder(request, routeData);
        Response response = responseBuilder.getResponse();

        ResponseSender sender = new ResponseSender(out);
        sender.send(response);
    }
}