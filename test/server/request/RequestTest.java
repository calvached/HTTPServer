package server.request;

import main.java.server.request.Request;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class RequestTest {
    @Test
    public void itTakesASetOfAttributes() throws Exception {
        HashMap attributes = new HashMap();
        attributes.put("requestMethod", "GET");
        attributes.put("url", "/form");
        attributes.put("data", "hello");

        Request request = new Request(attributes);

        assertEquals("GET", request.method());
        assertEquals("/form", request.url());
        assertEquals("hello", request.data());
    }
}
