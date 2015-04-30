package main.java.server.response.statusHeader;

import main.java.server.response.Response;
import main.java.server.routeData.RouteData;

public class StatusHeaderBuilder {
    private final String httpVersion = "HTTP/1.1 ";
    private final String CRLF = "\r\n";
    private final StatusCodes statusCodes = new StatusCodes();
    private final RouteData routeData;
    private final Response response;

    public StatusHeaderBuilder(RouteData clientRouteData, Response serverResponse) {
        routeData = clientRouteData;
        response = serverResponse;
    }

    public void assembleStatusHeader() {
        assignStatusHeader(getStatusCodeNumber());
    }

    private int getStatusCodeNumber() {
        if (routeData.isRedirect()) {
            return 302;
        }
        else if (routeData.badRequest()) {
            return 400;
        }
        else if (routeData.notFound()) {
            return 404;
        }
        else if (routeData.methodNotAllowed()) {
            return 405;
        }
        else if (routeData.isPartialContent()) {
            return 206;
        }
        else if (routeData.isPatch()) {
            return 204;
        }
        else if (unauthorized()) {
            return 401;
        }
        else {
            return 200;
        }
    }

    private boolean unauthorized() {
        return routeData.requireAuthentication() && !routeData.authorization();
    }

    private void assignStatusHeader(int statusCode) {
        response.setStatusHeader(getStatusHeaderLine(statusCode));
    }

    private String getStatusHeaderLine(int statusCode) {
        return httpVersion + statusCodes.headers.get(statusCode) + CRLF;
    }
}
