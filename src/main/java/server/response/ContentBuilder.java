package main.java.server.response;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class ContentBuilder {
    private final HashMap<String, String> request;
    private final HashMap<String, Object> response;

    public ContentBuilder(HashMap<String, String> clientRequest, HashMap<String, Object> serverResponse) {
        request = clientRequest;
        response = serverResponse;
    }

    public void assembleContent() throws IOException {
        if (isGET() && not404()) {
            getContent();
        }
    }

    private void getContent() throws IOException {
        if (request.containsKey("isDirectory")) {
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

        response.put("body", templateBytes);
    }

    private byte[] convertToBytes(String template) {
        return template.getBytes(Charset.forName("UTF-8"));
    }

    private byte[] convertToBytes(Path path) throws IOException {
        return Files.readAllBytes(path);
    }

    private void assignFilePathToResponse() throws IOException {
        Path filePath = Paths.get(path());

        response.put("body", convertToBytes(filePath));
    }

    private String path() {
        return request.get("content");
    }

    private boolean isGET() {
        return request.get("method").equals("GET");
    }

    private boolean not404() {
        return !request.containsKey("notFound");
    }
}
