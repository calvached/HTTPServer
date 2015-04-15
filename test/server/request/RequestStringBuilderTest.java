package server.request;

import main.java.server.request.RequestStringBuilder;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class RequestStringBuilderTest {

    @Test
    public void itBuildsARequestObjectWithAttributes() throws Exception {
        String testString = "POST /form HTTP/1.1\nContent-Length: 32\n\n{ name=diana }\n";
        InputStream mockInputStream =
                new ByteArrayInputStream(
                        testString.getBytes(StandardCharsets.UTF_8));

        RequestStringBuilder builder = new RequestStringBuilder(mockInputStream);

        HashMap request = builder.getRequest();

        assertEquals("POST", request.get("method"));
        assertEquals("/form", request.get("path"));
        assertEquals("{ name=diana }\n", request.get("params"));
    }
}
