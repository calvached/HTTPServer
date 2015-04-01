package main.java.server.request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParser {
    private String requestString;

    public RequestParser(String string) {
        requestString = string;
    }

    public String getRequestMethod() {
        Pattern method= Pattern.compile("^(\\w+).+");
        Matcher methodMatcher = method.matcher(requestString);

        methodMatcher.find();

        return methodMatcher.group(1);
    }

    public String getPostedData() {
        String data = null;

        Pattern blankLine = Pattern.compile("\\s\\s(.+)");
        Matcher requestMatcher = blankLine.matcher(requestString);

        if (requestMatcher.find()) {
            data = requestMatcher.group(1);
        }

        return data;
    }

    public String getUrl() {
        Pattern url = Pattern.compile("^\\w+\\s(/\\w*\\.?\\w*).+");
        Matcher urlMatcher = url.matcher(requestString);

        urlMatcher.find();

        return urlMatcher.group(1);
    }
}