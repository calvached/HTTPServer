package main.java.server.response;

public class DirectoryTemplate {
    private final String[] strings;

    public DirectoryTemplate(String[] templateStrings) {
        strings = templateStrings;
    }

    public String getTemplate() {
        String template = "";

        for (String line : strings) {
            template += "<a href='http://localhost:5000/"+ line +"'>" + line + "</a>" + "\r\n";
        }

        return template;
    }
}
