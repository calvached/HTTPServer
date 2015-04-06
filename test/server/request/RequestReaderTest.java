package server.request;

import main.java.server.request.RequestReader;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class RequestReaderTest {
    @Test
    public void concatenatesARequestIntoAString() throws Exception {
        String testString = "POST /form HTTP/1.1\nContent-Length: 32\n\n{ name=diana }\n";
        InputStream mockInputStream =
                new ByteArrayInputStream(
                        testString.getBytes(StandardCharsets.UTF_8));

        RequestReader reader =
                new RequestReader(
                        new BufferedReader(
                                new InputStreamReader(
                                        mockInputStream)));

        String request = reader.getConcatenatedRequest();
        String expectedRequest =
                "POST /form HTTP/1.1\n" +
                "Content-Length: 32\n" +
                "\n" +
                "{ name=diana }\n";

        assertEquals(expectedRequest, request);
    }
}
