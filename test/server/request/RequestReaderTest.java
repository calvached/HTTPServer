package server.request;

import main.java.server.request.RequestReader;
import mocks.MockBufferedReader;
import mocks.MockInputStream;

import mocks.MockInputStreamReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestReaderTest {
    @Test
    public void concatenatesARequestIntoAString() throws Exception {
        String[] requestLines = {
                "GET /localhost:5000/hi HTTP/1.0",
                "Content-Length: 32",
                "",
                "{ name=diana }"};

        RequestReader reader =
                new RequestReader(
                        new MockBufferedReader(
                                new MockInputStreamReader(
                                        new MockInputStream()),
                                requestLines));

        String request = reader.getConcatenatedRequest();
        String expectedRequest =
                "GET /localhost:5000/hi HTTP/1.0\n" +
                "Content-Length: 32\n" +
                "\n" +
                "{ name=diana }\n";

        assertEquals(expectedRequest, request);
    }
}
