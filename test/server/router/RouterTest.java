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

        Router router = new Router();
        router.directTrafficFor(mockInputStream, mockOutputStream);

        assertEquals(
                "HTTP/1.1 200 OK\r\n" +
                "\r\n" +
                "<a href='http://localhost:5000/file1'>file1</a>\r\n" +
                "<a href='http://localhost:5000/file2'>file2</a>\r\n" +
                "<a href='http://localhost:5000/image.gif'>image.gif</a>\r\n" +
                "<a href='http://localhost:5000/image.jpeg'>image.jpeg</a>\r\n" +
                "<a href='http://localhost:5000/image.png'>image.png</a>\r\n" +
                "<a href='http://localhost:5000/partial_content.txt'>partial_content.txt</a>\r\n" +
                "<a href='http://localhost:5000/patch-content.txt'>patch-content.txt</a>\r\n" +
                "<a href='http://localhost:5000/postData.txt'>postData.txt</a>\r\n" +
                "<a href='http://localhost:5000/text-file.txt'>text-file.txt</a>", mockOutputStream.toString().trim());
    }

    @Test
    public void itWritesA404ResponseToTheStreamIfMethodDoesNotExist() throws Exception {
        String testString = "GET /fakeRoute HTTP/1.1\r\n" + "Content-Length: 32";
        InputStream mockInputStream =
                new ByteArrayInputStream(
                        testString.getBytes(StandardCharsets.UTF_8));

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        Router router = new Router();
        router.directTrafficFor(mockInputStream, mockOutputStream);

        assertEquals("HTTP/1.1 404 Not Found", mockOutputStream.toString().trim());
    }
}