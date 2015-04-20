package main.java.server.response;

public class Response {
    private String statusHeader = "";
    private String headers = "";
    private byte[] body = null;

    public String statusHeader() {
        return statusHeader;
    }

    public void setStatusHeader(String statusLine) {
        statusHeader = statusLine;
    }

    public String headers() {
        return headers;
    }

    public void setHeader(String headerLine) {
        headers += headerLine;
    }

    public byte[] body() {
        return body;
    }

    public void setBody(byte[] content) {
        body = content;
    }
}
