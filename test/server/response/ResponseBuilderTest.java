package server.response;

import main.java.server.response.ResponseBuilder;
import main.java.server.response.ResponseWriter;
import mocks.MockOutputStream;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseBuilderTest {
    @Test
    public void itWritesAStringToTheStream() throws Exception {
        MockOutputStream out = new MockOutputStream();
        ResponseWriter writer = new ResponseWriter(out);

        ResponseBuilder builder = new ResponseBuilder(writer);

        assertEquals(false, out.successfulWrite);

        builder.createSuccessfulResponse();

        assertEquals(true, out.successfulWrite);
    }
}
