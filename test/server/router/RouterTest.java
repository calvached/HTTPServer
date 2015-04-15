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
                "file1\r\n" +
                "file2\r\n" +
                "image.gif\r\n" +
                "image.jpeg\r\n" +
                "image.png\r\n" +
                "partial_content.txt\r\n" +
                "patch-content.txt\r\n" +
                "postData.txt\r\n" +
                "text-file.txt", mockOutputStream.toString().trim());
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