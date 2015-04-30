package server.router;

import main.java.server.router.Router;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class RouterTest {

    @Test
    public void itRespondsToAGetRequestWithA200() throws Exception {
        String testString = "GET / HTTP/1.1\r\n" + "Content-Length: 32";
        InputStream mockInputStream =
                new ByteArrayInputStream(
                        testString.getBytes(StandardCharsets.UTF_8));

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        Router router = new Router(mockInputStream, mockOutputStream);
        router.directTraffic();

        assertEquals(
                "HTTP/1.1 200 OK\r\n" +
                "\r\n" +
                "<a href='/file1'>file1</a>\r\n" +
                "<a href='/file2'>file2</a>\r\n" +
                "<a href='/image.gif'>image.gif</a>\r\n" +
                "<a href='/image.jpeg'>image.jpeg</a>\r\n" +
                "<a href='/image.png'>image.png</a>\r\n" +
                "<a href='/partial_content.txt'>partial_content.txt</a>\r\n" +
                "<a href='/patch-content.txt'>patch-content.txt</a>\r\n" +
                "<a href='/postData.txt'>postData.txt</a>\r\n" +
                "<a href='/text-file.txt'>text-file.txt</a>", mockOutputStream.toString().trim());
    }

    @Test
    public void itRespondsToAPostRequestWithA200() throws Exception {
        String testString = "POST /form HTTP/1.1\r\n"
                + "Content-Length: 32\r\n\r\n"
                + "param data here";
        InputStream mockInputStream =
                new ByteArrayInputStream(
                        testString.getBytes(StandardCharsets.UTF_8));

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        Router router = new Router(mockInputStream, mockOutputStream);
        router.directTraffic();

        assertEquals(
                "HTTP/1.1 200 OK",
                mockOutputStream.toString().trim());
    }

    @Test
    public void itRespondsToARequestWithA404() throws Exception {
        String testString = "GET /fakeRoute HTTP/1.1\r\n" + "Content-Length: 32";
        InputStream mockInputStream =
                new ByteArrayInputStream(
                        testString.getBytes(StandardCharsets.UTF_8));

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        Router router = new Router(mockInputStream, mockOutputStream);
        router.directTraffic();

        assertEquals("HTTP/1.1 404 Not Found",
                mockOutputStream.toString().trim());
    }

    @Test
    public void itRespondsToARequestWithA405() throws Exception {
        String testString = "POST / HTTP/1.1\r\n"
                + "Content-Length: 32\r\n\r\n"
                + "param data here";
        InputStream mockInputStream =
                new ByteArrayInputStream(
                        testString.getBytes(StandardCharsets.UTF_8));

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        Router router = new Router(mockInputStream, mockOutputStream);
        router.directTraffic();

        assertEquals(
                "HTTP/1.1 405 Method Not Allowed",
                mockOutputStream.toString().trim());
    }
}