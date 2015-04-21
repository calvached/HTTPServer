package main.java.server.response;

import main.java.server.request.Request;
import main.java.server.routeData.RouteData;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentBuilder {
    private final Request request;
    private final RouteData routeData;
    private final Response response;

    public ContentBuilder(Request clientRequest, RouteData clientRouteData, Response serverResponse) {
        request = clientRequest;
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
        }
        else if (routeData.isPartialContent()) {
            buildPartialContent();
        } else {
            assignFilePathToResponse();
        }
    }

    private void buildPartialContent() throws IOException {
        byte[] partialContent;
        Path filePath = Paths.get(path());

        if (getRange().length() > 2) {
            partialContent = getPartialContentWithRangeBoundariesPresent(filePath);
        } else {
            partialContent = getPartialContentWithMissingRangeBoundary(filePath);
        }

        response.setBody(partialContent);
    }

    private byte[] getPartialContentWithMissingRangeBoundary(Path filePath) throws IOException {
        Pattern method = Pattern.compile("-\\d+");
        Matcher methodMatcher = method.matcher(getRange());
        byte[] partialContent;
        byte[] fileBytes = convertToBytes(filePath);

        if (methodMatcher.find()) {
            partialContent = getLastNBytes(fileBytes);
        } else {
            partialContent = getBytesFromStartingPoint(fileBytes);
        }

        return partialContent;
    }

    private byte[] getBytesFromStartingPoint(byte[] fileBytes) {
        return Arrays.copyOfRange(
                fileBytes,
                getFirstBoundary(),
                fileBytes.length);
    }

    private byte[] getLastNBytes(byte[] fileBytes) {
        return Arrays.copyOfRange(
                fileBytes,
                findFirstBoundary(fileBytes),
                fileBytes.length);
    }

    private int findFirstBoundary(byte[] fileBytes) {
        return fileBytes.length + Integer.parseInt(getRange());
    }

    private int getFirstBoundary() {
        return Integer.parseInt(getRange().split("-")[0]);
    }

    private byte[] getPartialContentWithRangeBoundariesPresent(Path filePath) throws IOException {
        String[] boundaries = getRange().split("-");

        return Arrays.copyOfRange(
                convertToBytes(filePath),
                Integer.parseInt(boundaries[0]),
                Integer.parseInt(boundaries[1]) + 1);
    }

    private String getRange() {
        return request.headers().get("Range").toString().split("=")[1];
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
        return request.method().equals("GET");
    }

    private boolean not404() {
        return !routeData.notFound();
    }
}
