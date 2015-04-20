package main.java.server.response;

import main.java.server.routeData.RouteData;

public class HeaderBuilder {
    private final RouteData routeData;
    private final Response response;

    public HeaderBuilder(RouteData clientRouteData, Response serverResponse) {
        routeData = clientRouteData;
        response = serverResponse;
    }

    public void assembleHeaders() {
        if (routeData.isRedirect()) {
            response.setHeader("Location: http://localhost:5000" +
                               routeData.redirectPath() +
                               "\r\n");
        } else if (routeData.isOptions()) {
            response.setHeader("Allow: " +
                               routeData.allowedMethods() +
                               "\r\n");
        }
    }
}
