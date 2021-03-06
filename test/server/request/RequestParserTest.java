package server.request;

import main.java.server.request.RequestParser;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {
    @Test
    public void itReturnsARequestMethod() throws Exception {
        String requestString =
                "GET /form HTTP/1.0\r\n"+
                "From: frog@jmarshall.com\r\n"+
                "User-Agent: HTTPTool/1.0\r\n"+
                "Content-Type: application/x-www-form-urlencoded\r\n"+
                "Content-Length: 32";

        RequestParser parser = new RequestParser(requestString);
        String method = parser.getRequestMethod();

        assertEquals("GET", method);
    }

    @Test
    public void itReturnsALongUrl() throws Exception {
        String requestString =
                "GET /form HTTP/1.0\r\n"+
                "From: frog@jmarshall.com\r\n"+
                "User-Agent: HTTPTool/1.0\r\n"+
                "Content-Type: application/x-www-form-urlencoded\r\n"+
                "Content-Length: 32";

        RequestParser parser = new RequestParser(requestString);
        String url = parser.getUrl();

        assertEquals("/form", url);
    }

    @Test
    public void itReturnsAnIndexUrl() throws Exception {
        String requestString =
                "GET / HTTP/1.0\r\n"+
                "From: frog@jmarshall.com\r\n"+
                "User-Agent: HTTPTool/1.0\r\n"+
                "Content-Type: application/x-www-form-urlencoded\r\n"+
                "Content-Length: 32";

        RequestParser parser = new RequestParser(requestString);
        String url = parser.getUrl();

        assertEquals("/", url);
    }

    @Test
    public void itReturnsAUrlWithAFileExtension() throws Exception {
        String requestString =
                "GET /sample.pdf HTTP/1.0\r\n"+
                "From: frog@jmarshall.com\r\n"+
                "User-Agent: HTTPTool/1.0\r\n"+
                "Content-Type: application/x-www-form-urlencoded\r\n"+
                "Content-Length: 32";

        RequestParser parser = new RequestParser(requestString);
        String url = parser.getUrl();

        assertEquals("/sample.pdf", url);
    }

    @Test
    public void itReturnsPostedData() throws Exception {
        String requestString =
                "POST /form HTTP/1.0\n"+
                "From: frog@jmarshall.com\n"+
                "User-Agent: HTTPTool/1.0\n"+
                "Content-Type: application/x-www-form-urlencoded\n"+
                "Content-Length: 32\n"+
                "\n"+
                "name=diana";

        RequestParser parser = new RequestParser(requestString);
        String data = parser.getPostedData();

        assertEquals("name=diana", data);
    }

    @Test
    public void itReturnsPostedDataIfAReturnCharacterExists() throws Exception {
        String requestString =
                "POST /form HTTP/1.0\r\n"+
                "From: frog@jmarshall.com\r\n"+
                "User-Agent: HTTPTool/1.0\r\n"+
                "Content-Type: application/x-www-form-urlencoded\r\n"+
                "Content-Length: 32\r\n"+
                "\r\n"+
                "name=diana";

        RequestParser parser = new RequestParser(requestString);
        String data = parser.getPostedData();

        assertEquals("name=diana", data);
    }

    @Test
    public void returnsAnEmptyStringIfDataDoesNotExist() throws Exception {
        String requestString =
                "GET /form HTTP/1.0\n"+
                "From: frog@jmarshall.com\n"+
                "User-Agent: HTTPTool/1.0\n"+
                "Content-Type: application/x-www-form-urlencoded\n"+
                "Content-Length: 32";

        RequestParser parser = new RequestParser(requestString);
        String data = parser.getPostedData();

        assertEquals("", data);
    }

    @Test
    public void returnsAUrlWithADash() throws Exception {
        String requestString =
                "GET /text-file.txt HTTP/1.0\n"+
                "From: frog@jmarshall.com\n"+
                "User-Agent: HTTPTool/1.0\n"+
                "Content-Type: application/x-www-form-urlencoded\n"+
                "Content-Length: 32";

        RequestParser parser = new RequestParser(requestString);
        String url = parser.getUrl();

        assertEquals("/text-file.txt", url);
    }

    @Test
    public void returnsHeaders() throws Exception {
        String requestString =
                "GET /partial_content.txt HTTP/1.1\r\n" +
                "Range: bytes=0-4\r\n" +
                "User-Agent: HTTPTool/1.0\r\n" +
                "Content-Type: application/x-www-form-urlencoded\r\n" +
                "Content-Length: 32\r\n" +
                "\r\n" +
                "some data";

        RequestParser parser = new RequestParser(requestString);
        HashMap parsedHeaders = parser.getHeaders();

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Range", "bytes=0-4");
        headers.put("User-Agent", "HTTPTool/1.0");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("Content-Length", "32");

        assertEquals(headers, parsedHeaders);
    }

    @Test
    public void itReturnsAnEmptyStringIfNoMethod() throws Exception {
        String requestString =
                "/form HTTP/1.0\r\n"+
                        "From: frog@jmarshall.com\r\n"+
                        "User-Agent: HTTPTool/1.0\r\n"+
                        "Content-Type: application/x-www-form-urlencoded\r\n"+
                        "Content-Length: 32";

        RequestParser parser = new RequestParser(requestString);
        String method = parser.getRequestMethod();

        assertEquals("", method);
    }

    @Test
    public void itReturnsAnEmptyStringIfNoUrl() throws Exception {
        String requestString =
                "GET\r\n";

        RequestParser parser = new RequestParser(requestString);
        String url = parser.getUrl();

        assertEquals("", url);
    }
}