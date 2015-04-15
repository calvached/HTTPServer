package main.java.server.router;

import main.java.server.request.RequestStringBuilder;
import main.java.server.response.ResponseBuilder;
import main.java.server.response.ResponseSender;

import java.io.*;
import java.util.HashMap;

public class Router {
    public void directTrafficFor(InputStream in, OutputStream out) throws IOException {
        RequestStringBuilder requestBuilder = new RequestStringBuilder(in);

        HashMap request = requestBuilder.getRequest();

        ResponseBuilder responseBuilder = new ResponseBuilder(request);
        HashMap response = responseBuilder.getResponse();

        ResponseSender sender = new ResponseSender(out);
        sender.send(response);
    }
}