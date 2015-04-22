package main.java.server.routeData;

import main.java.server.request.Request;

import java.io.File;
import java.util.Arrays;

public class RouteDataBuilder {
    private final ValidRoutes validRoutes = new ValidRoutes();
    private final RouteData routeData = new RouteData();

    public RouteData assembleRouteData(Request request) {
        validatePath(request);

        return routeData;
    }

    private void validatePath(Request request) {
        if (existingPathIsValid(request.path())) {
            determinePathType(request);
            assignRouteDataContent(request);
        }
        else if (validPathWithQueryString(request)) {
            routeData.setHasQueryString(true);
            routeData.setQueryString(splitPath(request)[1]);
        } else {
            routeData.setNotFound(true);
        }
    }

    private boolean validPathWithQueryString(Request request) {
        String requestPath = splitPath(request)[0];

        return hasQueryString(request) && existingPathIsValid(requestPath);
    }

    private String[] splitPath(Request request) {
        return request.path().split("\\?");
    }

    private boolean hasQueryString(Request request) {
        int splitPathSize = splitPath(request).length;

        return splitPathSize == 2;
    }

    private void determinePathType(Request request) {
        if (isRedirectPath(request.path())) {
            routeData.setRedirectPath(getRedirectionPath(request));
            routeData.setIsRedirect(true);
        }
        else if (request.method().equals("OPTIONS")) {
            routeData.setAllowedMethods(allowedMethods(request));
            routeData.setIsOptions(true);
        }
        else if (request.method().equals("PATCH")) {
            routeData.setIsPatch(true);
        }
        else if (isPartialContent(request)) {
            routeData.setIsPartialContent(true);
        }
        else if (methodNotAllowed(request.path(), request.method())) {
            routeData.setMethodNotAllowed(true);
        }
    }

    private void assignRouteDataContent(Request request) {
        if (contentExists(request)) {
            routeData.setContentPath(getContentPath(request));

            File contentFile = new File(getContentPath(request));

            determineContentType(contentFile);
        } else if (routeData.isRedirect()) {
            routeData.setContentPath(getRedirectContent());

            File contentFile = new File(getRedirectContent());

            determineContentType(contentFile);
        }
    }

    private String getRedirectContent() {
        return (String) validRoutes.routes.get(routeData.redirectPath()).get("content");
    }

    private void determineContentType(File contentFile) {
        if (contentFile.isDirectory()) {
            routeData.setIsDirectory(true);
        } else {
            routeData.setIsFile(true);
        }
    }

    private boolean contentExists(Request request) {
        return validRoutes.routes.get(request.path()).containsKey("content");
    }

    private String getContentPath(Request request) {
        return (String) validRoutes.routes.get(request.path()).get("content");
    }

    private String allowedMethods(Request request) {
        String allMethods = "";

        String[] allowedMethods = (String[]) validRoutes.routes.get(request.path()).get("allowedMethods");

        allMethods += allowedMethods[0] +
                "," + allowedMethods[1] +
                "," + allowedMethods[2] +
                "," + allowedMethods[3] +
                "," + allowedMethods[4];

        return allMethods;
    }

    private boolean methodNotAllowed(String path, String method) {
        String[] allowedMethods = (String[]) validRoutes.routes.get(path).get("allowedMethods");

        return !Arrays.asList(allowedMethods).contains(method);
    }

    private boolean isPartialContent(Request request) {
        return request.headers().containsKey("Range");
    }

    private String getRedirectionPath(Request request) {
        return (String) validRoutes.routes.get(request.path()).get("redirectPath");
    }

    private boolean isRedirectPath(String path) {
        return validRoutes.routes.get(path).containsKey("redirectPath");
    }

    private boolean existingPathIsValid(String path) {
        return validRoutes.routes.containsKey(path);
    }
}
