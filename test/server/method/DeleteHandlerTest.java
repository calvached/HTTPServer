package server.method;

import main.java.server.method.DeleteHandler;
import main.java.server.request.Request;
import main.java.server.response.ResponseBuilder;
import main.java.server.response.ResponseWriter;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class DeleteHandlerTest {

    @Test
    public void respondsWithA200IfDeleteRouteExists() throws Exception {
        HashMap attributes = new HashMap();
        attributes.put("requestMethod", "PUT");
        attributes.put("url", "/form");
        attributes.put("data", "My=data");

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        Request request = new Request(attributes);

        ResponseBuilder builder =
                new ResponseBuilder(
                        new ResponseWriter(mockOutputStream));

        DeleteHandler delete = new DeleteHandler(builder);
        delete.handle(request);

        assertEquals("HTTP/1.1 200 OK", mockOutputStream.toString().trim());
    }

    @Test
    public void respondsWithA404IfRouteDoesNotExist() throws Exception {
        HashMap attributes = new HashMap();
        attributes.put("requestMethod", "PUT");
        attributes.put("url", "/cat");
        attributes.put("data", "My=data");

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        Request request = new Request(attributes);

        ResponseBuilder builder =
                new ResponseBuilder(
                        new ResponseWriter(mockOutputStream));

        DeleteHandler delete = new DeleteHandler(builder);
        delete.handle(request);

        assertEquals("HTTP/1.1 404 Not Found", mockOutputStream.toString().trim());
    }
}
