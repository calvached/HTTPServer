package main.java.server.response;

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
        if (routeData.isRedirect()) {
            assignStatusHeader(302);
        }
        else if (routeData.badRequest()) {
            assignStatusHeader(400);
        }
        else if (routeData.notFound()) {
            assignStatusHeader(404);
        }
        else if (routeData.methodNotAllowed()) {
            assignStatusHeader(405);
        }
        else if (routeData.isPartialContent()) {
            assignStatusHeader(206);
        }
        else if (routeData.isPatch()) {
            assignStatusHeader(204);
        }
        else if (unauthorized()) {
            assignStatusHeader(401);
        }
        else {
            assignStatusHeader(200);
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
