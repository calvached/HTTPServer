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
        String testString = "GET / HTTP/1.1";
        InputStream mockInputStream =
                new ByteArrayInputStream(
                        testString.getBytes(StandardCharsets.UTF_8));

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        Router router = new Router();
        router.handleTrafficFor(mockInputStream, mockOutputStream);

        assertEquals("HTTP/1.1 200 OK", mockOutputStream.toString().trim());
    }

    @Test
    public void itWritesA404ResponseToTheStreamIfMethodDoesNotExist() throws Exception {
        String testString = "GET /cats HTTP/1.1";
        InputStream mockInputStream =
                new ByteArrayInputStream(
                        testString.getBytes(StandardCharsets.UTF_8));

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        Router router = new Router();
        router.handleTrafficFor(mockInputStream, mockOutputStream);

        assertEquals("HTTP/1.1 404 Not Found", mockOutputStream.toString().trim());
    }
}