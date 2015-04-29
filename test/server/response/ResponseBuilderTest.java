package server.response;

import main.java.server.request.Request;
import main.java.server.response.Response;
import main.java.server.response.ResponseBuilder;
import main.java.server.routeData.RouteData;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ResponseBuilderTest {

    @Test
    public void itReturnsAResponseToAGet() throws Exception {
        Path filePath = Paths.get("test/public/file.txt");
        String textToString = new String(Files.readAllBytes(filePath));

        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "GET");
        attributes.put("path", "/file.txt");
        attributes.put("params", "");
        Request request = new Request(attributes);

        RouteData routeData = new RouteData();
        routeData.setIsFile(true);
        routeData.setContentPath("test/public/file.txt");

        ResponseBuilder responseBuilder = new ResponseBuilder(request, routeData);
        Response response = responseBuilder.getResponse();

        String bodyToString = new String(response.body());

        assertEquals("HTTP/1.1 200 OK\r\n", response.statusHeader());
        assertEquals(textToString, bodyToString);
    }

    @Test
    public void itReturnsAResponseToAPost() throws Exception {
        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "POST");
        attributes.put("path", "/form");
        attributes.put("params", "some data");
        Request request = new Request(attributes);

        RouteData routeData = new RouteData();
        routeData.setContentPath("test/public/postData.txt");

        ResponseBuilder responseBuilder = new ResponseBuilder(request, routeData);
        Response response = responseBuilder.getResponse();

        assertEquals("HTTP/1.1 200 OK\r\n", response.statusHeader());
        assertEquals(null, response.body());
    }

    @Test
    public void itReturnsAResponseToAPut() throws Exception {
        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "PUT");
        attributes.put("path", "/form");
        attributes.put("params", "different data");
        Request request = new Request(attributes);

        RouteData routeData = new RouteData();
        routeData.setContentPath("test/public/postData.txt");

        ResponseBuilder responseBuilder = new ResponseBuilder(request, routeData);
        Response response = responseBuilder.getResponse();

        assertEquals("HTTP/1.1 200 OK\r\n", response.statusHeader());
        assertEquals(null, response.body());
    }

    @Test
    public void itReturnsAResponseToADelete() throws Exception {
        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "DELETE");
        attributes.put("path", "/form");
        attributes.put("params", "");
        Request request = new Request(attributes);

        RouteData routeData = new RouteData();
        routeData.setContentPath("test/public/postData.txt");

        ResponseBuilder responseBuilder = new ResponseBuilder(request, routeData);
        Response response = responseBuilder.getResponse();

        assertEquals("HTTP/1.1 200 OK\r\n", response.statusHeader());
        assertEquals(null, response.body());
    }

    @Test
    public void itReturnsAResponseWithALocationHeader() throws Exception {
        File directoryFile = new File("test/public/");

        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "GET");
        attributes.put("path", "/redirect");
        attributes.put("params", "");
        Request request = new Request(attributes);

        RouteData routeData = new RouteData();
        routeData.setContentPath("test/public/");
        routeData.setIsDirectory(true);
        routeData.setIsRedirect(true);
        routeData.setRedirectPath("/");

        ResponseBuilder responseBuilder = new ResponseBuilder(request, routeData);
        Response response = responseBuilder.getResponse();
        String bodyToString = new String(response.body());

        assertEquals("HTTP/1.1 302 Found\r\n", response.statusHeader());
        assertEquals(true, bodyToString.contains(directoryFile.list()[0]));
        assertEquals("Location: http://localhost:5000/\r\n", response.headers());
    }
}
