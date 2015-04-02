package server.request;

import main.java.server.request.Request;
import main.java.server.request.RequestBuilder;
import main.java.server.request.RequestReader;
import mocks.MockBufferedReader;
import mocks.MockInputStream;
import mocks.MockInputStreamReader;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class RequestBuilderTest {

    @Test
    public void itBuildsARequestObjectWithAttributes() throws Exception {
        String[] inputRequest = {
                "POST /hi HTTP/1.0\n",
                "From: frog@jmarshall.com\n",
                "User-Agent: HTTPTool/1.0\n",
                "Content-Type: application/x-www-form-urlencoded\n",
                "Content-Length: 32",
                "\r\n\r\n",
                "name=diana"
        };

        RequestBuilder builder =
                new RequestBuilder(
                        new RequestReader(
                                new MockBufferedReader(
                                        new MockInputStreamReader(
                                                new MockInputStream()),
                                        inputRequest)));

        Request request = builder.getRequest();

        assertEquals(Request.class, request.getClass());
        assertEquals("POST", request.method());
        assertEquals("/hi", request.url());
        assertEquals("name=diana", request.data());
    }
}