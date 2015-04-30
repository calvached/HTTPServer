package main.java.server.response.content;

public class DirectoryTemplate {
    private final String[] strings;

    public DirectoryTemplate(String[] strings) {
        this.strings = strings;
    }

    public String getTemplate() {
        String template = "";

        for (String line : strings) {
            template += "<a href='/" + line +"'>" + line + "</a>" + "\r\n";
        }

        return template;
    }
}
