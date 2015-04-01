package server.response;

import main.java.server.response.ResponseBuilder;
import main.java.server.response.ResponseWriter;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class ResponseBuilderTest {
    @Test
    public void itWritesASuccessfulResponseToTheStream() throws Exception {
        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        ResponseWriter writer = new ResponseWriter(mockOutputStream);
        ResponseBuilder builder = new ResponseBuilder(writer);

        builder.createSuccessfulResponse();

        assertEquals("HTTP/1.1 200 OK", mockOutputStream.toString().trim());
    }

    @Test
    public void itWritesAFourOhFourResponseToTheStream() throws Exception {
        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        ResponseWriter writer = new ResponseWriter(mockOutputStream);
        ResponseBuilder builder = new ResponseBuilder(writer);

        builder.createFourOhFour();

        assertEquals("HTTP/1.1 404 Not Found", mockOutputStream.toString().trim());
    }
}
