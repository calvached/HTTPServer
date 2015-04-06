package server.request;

import main.java.server.request.Request;
import main.java.server.request.RequestBuilder;
import main.java.server.request.RequestStringReader;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class RequestBuilderTest {

    @Test
    public void itBuildsARequestObjectWithAttributes() throws Exception {
        String testString = "POST /form HTTP/1.1\nContent-Length: 32\n\n{ name=diana }\n";
        InputStream mockInputStream =
                new ByteArrayInputStream(
                        testString.getBytes(StandardCharsets.UTF_8));

        RequestStringReader reader =
                new RequestStringReader(
                        new BufferedReader(
                                new InputStreamReader(
                                        mockInputStream)));

        RequestBuilder builder = new RequestBuilder(reader);

        Request request = builder.getRequest();

        assertEquals(Request.class, request.getClass());
        assertEquals("POST", request.method());
        assertEquals("/form", request.url());
        assertEquals("{ name=diana }", request.data());
    }
}
