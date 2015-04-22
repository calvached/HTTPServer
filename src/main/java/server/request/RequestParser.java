package main.java.server.request;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParser {
    private String requestString;

    public RequestParser(String string) {
        requestString = string;
    }

    public String getRequestMethod() {
        String requestMethod = "";
        Pattern method = Pattern.compile("^(\\w+).+");
        Matcher methodMatcher = method.matcher(requestString);

        if (methodMatcher.find()) {
           requestMethod = methodMatcher.group(1);
        }

        return requestMethod;
    }

    public String getPostedData() {
        String data = "";

        String[] lines = requestString.split(blankLine());

        if (lines.length > 1) {
            data += lines[1];
        }

        return data;
    }

    public String getUrl() {
        String url = "";
        String[] lines = requestString.split(" ");

        if (lines.length > 1) {
            url = lines[1];
        }

        return url;
    }

    public HashMap getHeaders() {
        HashMap<String, String> headers = new HashMap<>();

        for (String header : getAllHeaderLines()) {
            if (header.contains(": ")) {
                String[] splitHeader = header.split(": ");
                headers.put(splitHeader[0], splitHeader[1].trim());
            }
        }

        return headers;
    }

    private String[] getAllHeaderLines() {
        return getHeaderChunk().split("\n");
    }

    private String getHeaderChunk() {
        return requestString.split("\r\n\r\n")[0];
    }

    private String blankLine() {
        return "\\r?\\n\\r?\\n";
    }
}