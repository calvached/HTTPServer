package server.router;

import main.java.server.router.Router;
import mocks.MockOutputStream;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class RouterTest {
    @Test
    public void itRespondsToAGetRequest() throws Exception {
        String testString = "GET / HTTP/1.1";
        InputStream mockInputStream =
                new ByteArrayInputStream(
                        testString.getBytes(StandardCharsets.UTF_8));

        MockOutputStream mockOutputStream = new MockOutputStream();

        Router router = new Router();
        router.handleTrafficFor(mockInputStream, mockOutputStream);

        assertEquals(true, mockOutputStream.successfulWrite);
    }
}