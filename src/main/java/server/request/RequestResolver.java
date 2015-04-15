package main.java.server.request;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

public class RequestResolver {
    private final ValidRoutes validRoutes = new ValidRoutes();

    public HashMap resolve(HashMap<String, String> request) {
        determineIfPathExists(request);

        return request;
    }

    private void determineIfPathExists(HashMap<String, String> request) {
        if (existingPathIsValid(request.get("path"))) {
            determinePathType(request);
            determineIfContentExists(request);
        } else {
            request.put("notFound", "true");
        }
    }

    private void determineIfContentExists(HashMap<String, String> request) {
        if (contentExists(request)) {
            request.put("content", getContentPath(request));

            File contentFile = new File(getContentPath(request));

            determineContentType(contentFile, request);
        }
    }

    private void determineContentType(File contentFile, HashMap<String, String> request) {
        if (contentFile.isDirectory()) {
            request.put("isDirectory", "true");
        } else {
            request.put("isFile", "true");
        }
    }

    private boolean contentExists(HashMap<String, String> request) {
        return validRoutes.routes.get(request.get("path")).containsKey("content");
    }

    private String getContentPath(HashMap<String, String> request) {

        return (String) validRoutes.routes.get(request.get("path")).get("content");
    }

    private void determinePathType(HashMap<String, String> request) {
        if (isRedirectPath(request.get("path"))) {
            request.put("path", getRedirectionPath(request));
            request.put("redirect", "true");
        }
        else if (request.get("method").equals("OPTIONS")) {
            allowedMethods(request);

            request.put("options", allowedMethods(request));

        }
        else if (methodNotAllowed(request.get("path"), request.get("method"))) {
            request.put("methodNotAllowed", "true");
        }
    }

    private String allowedMethods(HashMap<String, String> request) {
        String allMethods = "";

        String[] allowedMethods = (String[]) validRoutes.routes.get(request.get("path")).get("allowedMethods");

        allMethods += allowedMethods[0] +
                ", " + allowedMethods[1] +
                ", " + allowedMethods[2] +
                ", " + allowedMethods[3] +
                ", " + allowedMethods[4];

        return allMethods;
    }

    private boolean methodNotAllowed(String path, String method) {
        String[] allowedMethods = (String[]) validRoutes.routes.get(path).get("allowedMethods");

        return !Arrays.asList(allowedMethods).contains(method);
    }

    private String getRedirectionPath(HashMap<String, String> request) {
        return (String) validRoutes.routes.get(request.get("path")).get("redirectPath");
    }

    private boolean isRedirectPath(String path) {
        return validRoutes.routes.get(path).containsKey("redirectPath");
    }

    private boolean existingPathIsValid(String path) {
        return validRoutes.routes.containsKey(path);
    }
}
