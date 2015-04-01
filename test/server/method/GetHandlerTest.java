package server.method;

import main.java.server.method.GetHandler;
import main.java.server.request.Request;
import main.java.server.response.ResponseBuilder;
import main.java.server.response.ResponseWriter;
import mocks.MockOutputStream;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class GetHandlerTest {

    @Test
    public void doesSomething() throws Exception {
        HashMap attributes = new HashMap();
        attributes.put("requestMethod", "GET");
        attributes.put("url", "/");
        attributes.put("data", null);

        MockOutputStream out = new MockOutputStream();
        Request request = new Request(attributes);

        ResponseBuilder builder =
                new ResponseBuilder(
                        new ResponseWriter(out));

        GetHandler get = new GetHandler(builder);
        get.handle(request);

        assertEquals(true, out.successfulWrite);
    }
}