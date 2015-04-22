package server.request;

import main.java.server.request.RequestStringReader;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class RequestStringReaderTest {

    @Test
    public void concatenatesARequestIntoAString() throws Exception {
        String testString = "POST /form HTTP/1.1\nContent-Length: 32\n\n{ name=diana }\n";
        InputStream mockInputStream =
                new ByteArrayInputStream(
                        testString.getBytes(StandardCharsets.UTF_8));

        RequestStringReader reader =
                new RequestStringReader(
                        mockInputStream);

        String request = reader.getConcatenatedRequest();
        String expectedRequest =
                "POST /form HTTP/1.1\n" +
                "Content-Length: 32\n" +
                "\n" +
                "{ name=diana }\n";

        assertEquals(expectedRequest, request);
    }

    @Test
    public void returnsAnEmptyStringIfEmptyRequest() throws Exception {
        String testString = "";
        InputStream mockInputStream =
                new ByteArrayInputStream(
                        testString.getBytes(StandardCharsets.UTF_8));

        RequestStringReader reader =
                new RequestStringReader(mockInputStream);

        String request = reader.getConcatenatedRequest();

        assertEquals("", request);
    }
}