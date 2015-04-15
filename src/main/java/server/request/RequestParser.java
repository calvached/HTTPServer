package main.java.server.request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParser {
    private String requestString;

    public RequestParser(String string) {
        requestString = string;
    }

    public String getRequestMethod() {
        Pattern method = Pattern.compile("^(\\w+).+");
        Matcher methodMatcher = method.matcher(requestString);

        methodMatcher.find();

        return methodMatcher.group(1);
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
        String[] lines = requestString.split(" ");

        return lines[1];
    }

    private String blankLine() {
        return "\\r?\\n\\r?\\n";
    }
}