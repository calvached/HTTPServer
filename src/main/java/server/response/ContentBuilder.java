package main.java.server.response;

import main.java.server.routeData.RouteData;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ContentBuilder {
    private final String requestMethod;
    private final RouteData routeData;
    private final Response response;

    public ContentBuilder(String method, RouteData clientRouteData, Response serverResponse) {
        requestMethod = method;
        routeData = clientRouteData;
        response = serverResponse;
    }

    public void assembleContent() throws IOException {
        if (isGET() && not404()) {
            getContent();
        }
    }

    private void getContent() throws IOException {
        if (routeData.isDirectory()) {
            String template = createTemplate(new File(path()));
            assignTemplateToResponse(template);

        } else {
            assignFilePathToResponse();
        }

    }

    private String createTemplate(File file) {
        String[] fileList = file.list();
        DirectoryTemplate templateMaker = new DirectoryTemplate(fileList);

        return templateMaker.getTemplate();
    }

    private void assignTemplateToResponse(String template) {
        byte[] templateBytes = convertToBytes(template);

        response.setBody(templateBytes);
    }

    private byte[] convertToBytes(String template) {
        return template.getBytes(Charset.forName("UTF-8"));
    }

    private byte[] convertToBytes(Path path) throws IOException {
        return Files.readAllBytes(path);
    }

    private void assignFilePathToResponse() throws IOException {
        Path filePath = Paths.get(path());

        response.setBody(convertToBytes(filePath));
    }

    private String path() {
        return routeData.contentPath();
    }

    private boolean isGET() {
        return requestMethod.equals("GET");
    }

    private boolean not404() {
        return !routeData.notFound();
    }
}
