package server.request;

import main.java.server.request.Request;
import main.java.server.request.RequestStringBuilder;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class RequestStringBuilderTest {

    @Test
    public void itBuildsARequestObjectWithAttributes() throws Exception {
        String testString =
                "POST /form HTTP/1.1\r\n" +
                "Content-Length: 32\r\n" +
                "\r\n" +
                "{ name=diana }";
        InputStream mockInputStream =
                new ByteArrayInputStream(
                        testString.getBytes(StandardCharsets.UTF_8));

        RequestStringBuilder builder = new RequestStringBuilder(mockInputStream);

        Request request = builder.getRequest();

        assertEquals("POST", request.method());
        assertEquals("/form", request.path());
        assertEquals("{ name=diana }", request.params());
        assertEquals("32", request.headers().get("Content-Length"));
    }
}
