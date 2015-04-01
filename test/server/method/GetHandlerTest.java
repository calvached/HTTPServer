package server.method;

import main.java.server.method.GetHandler;
import main.java.server.request.Request;
import main.java.server.response.ResponseBuilder;
import main.java.server.response.ResponseWriter;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class GetHandlerTest {

    @Test
    public void respondsWithA200IfGETRouteExists() throws Exception {
        HashMap attributes = new HashMap();
        attributes.put("requestMethod", "GET");
        attributes.put("url", "/");
        attributes.put("data", null);

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        Request request = new Request(attributes);

        ResponseBuilder builder =
                new ResponseBuilder(
                        new ResponseWriter(mockOutputStream));

        GetHandler get = new GetHandler(builder);
        get.handle(request);

        assertEquals("HTTP/1.1 200 OK", mockOutputStream.toString().trim());
    }

    @Test
    public void servesAFile() throws Exception {
        HashMap attributes = new HashMap();
        attributes.put("requestMethod", "GET");
        attributes.put("url", "/file.txt");
        attributes.put("data", null);

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        Request request = new Request(attributes);

        ResponseBuilder builder =
                new ResponseBuilder(
                        new ResponseWriter(mockOutputStream));

        GetHandler get = new GetHandler(builder);
        assertEquals(0, mockOutputStream.size());

        get.handle(request);

        assertEquals("File Content", mockOutputStream.toString().trim());
    }

    @Test
    public void respondsWith404IfFileDoesNotExist() throws Exception {
        HashMap attributes = new HashMap();
        attributes.put("requestMethod", "GET");
        attributes.put("url", "/fakeFile.txt");
        attributes.put("data", null);

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        Request request = new Request(attributes);

        ResponseBuilder builder =
                new ResponseBuilder(
                        new ResponseWriter(mockOutputStream));

        GetHandler get = new GetHandler(builder);
        assertEquals(0, mockOutputStream.size());

        get.handle(request);

        assertEquals("HTTP/1.1 404 Not Found", mockOutputStream.toString().trim());
    }

    @Test
    public void respondsWithA404IfGETRouteDoesNotExist() throws Exception {
        HashMap attributes = new HashMap();
        attributes.put("requestMethod", "GET");
        attributes.put("url", "/nonExistentPath");
        attributes.put("data", null);

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        Request request = new Request(attributes);

        ResponseBuilder builder =
                new ResponseBuilder(
                        new ResponseWriter(mockOutputStream));

        GetHandler get = new GetHandler(builder);
        get.handle(request);

        assertEquals("HTTP/1.1 404 Not Found", mockOutputStream.toString().trim());
    }
}