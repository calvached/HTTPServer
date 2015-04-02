package server.method;

import main.java.server.method.ConnectionHandler;
import main.java.server.request.Request;
import main.java.server.response.ResponseBuilder;
import main.java.server.response.ResponseWriter;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ConnectionHandlerTest {
    @Test
    public void itExecutesTheGetHandler() throws Exception {
        HashMap attributes = new HashMap();
        attributes.put("requestMethod", "GET");
        attributes.put("url", "/");
        attributes.put("data", null);

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        Request request = new Request(attributes);
        ConnectionHandler connection =
                new ConnectionHandler(
                        request, new ResponseBuilder(
                                    new ResponseWriter(mockOutputStream)));

        Map<String, Runnable> methods = connection.methods;
        methods.get("GET").run();

        assertEquals("HTTP/1.1 200 OK", mockOutputStream.toString().trim());
    }

    @Test
    public void itExecutesThePostHandler() throws Exception {
        HashMap attributes = new HashMap();
        attributes.put("requestMethod", "POST");
        attributes.put("url", "/form");
        attributes.put("data", "hello");

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        Request request = new Request(attributes);
        ConnectionHandler connection =
                new ConnectionHandler(
                        request, new ResponseBuilder(
                        new ResponseWriter(mockOutputStream)));

        Map<String, Runnable> methods = connection.methods;
        methods.get("POST").run();

        assertEquals("HTTP/1.1 200 OK", mockOutputStream.toString().trim());
    }

    @Test
    public void itExecutesThePutHandler() throws Exception {
        HashMap attributes = new HashMap();
        attributes.put("requestMethod", "PUT");
        attributes.put("url", "/form");
        attributes.put("data", "World");

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        Request request = new Request(attributes);
        ConnectionHandler connection =
                new ConnectionHandler(
                        request, new ResponseBuilder(
                        new ResponseWriter(mockOutputStream)));

        Map<String, Runnable> methods = connection.methods;
        methods.get("PUT").run();

        assertEquals("HTTP/1.1 200 OK", mockOutputStream.toString().trim());
    }

    @Test
    public void itExecutesTheDeleteHandler() throws Exception {
        HashMap attributes = new HashMap();
        attributes.put("requestMethod", "DELETE");
        attributes.put("url", "/form");
        attributes.put("data", null);

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        Request request = new Request(attributes);
        ConnectionHandler connection =
                new ConnectionHandler(
                        request, new ResponseBuilder(
                        new ResponseWriter(mockOutputStream)));

        Map<String, Runnable> methods = connection.methods;
        methods.get("DELETE").run();

        assertEquals("HTTP/1.1 200 OK", mockOutputStream.toString().trim());
    }
}
