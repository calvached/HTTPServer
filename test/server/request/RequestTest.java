package server.request;

import main.java.server.request.Request;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class RequestTest {

    @Test
    public void itReturnsRequestAttributes() throws Exception {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Header1", "HeaderOne");

        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "GET");
        attributes.put("path", "/redirect");
        attributes.put("params", "");
        attributes.put("headers", headers);

        Request request = new Request(attributes);

        assertEquals("GET", request.method());
        assertEquals("/redirect", request.path());
        assertEquals("", request.params());
        assertEquals(headers, request.headers());
    }
}
