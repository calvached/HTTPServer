package main.java.server.response;

public class DirectoryTemplate {
    private final String[] strings;

    public DirectoryTemplate(String[] templateStrings) {
        strings = templateStrings;
    }

    public String getTemplate() {
        String template = "";

        for (String line : strings) {
            template += line + "\r\n";
        }

        return template;
    }
}
