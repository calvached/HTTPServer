package server.response;

import main.java.server.request.Request;
import main.java.server.response.ContentBuilder;
import main.java.server.response.Response;
import main.java.server.routeData.RouteData;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ContentBuilderTest {

    @Test
    public void itFindsContentForAGetRequest() throws Exception {
        Path filePath = Paths.get("public/file.txt");
        String textToString = new String(Files.readAllBytes(filePath));

        HashMap <String, String> headers = new HashMap<>();

        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "GET");
        attributes.put("path", "/file.txt");
        attributes.put("params", "");
        attributes.put("headers", headers);

        Request request = new Request(attributes);

        RouteData routeData = new RouteData();
        routeData.setIsFile(true);
        routeData.setContentPath("public/file.txt");

        Response response = new Response();

        ContentBuilder contentBuilder = new ContentBuilder(request, routeData, response);
        contentBuilder.assembleContent();

        String bodyToString = new String(response.body());

        assertEquals(textToString, bodyToString);
    }

    @Test
    public void itFindsContentForADirectory() throws Exception {
        File directoryFile = new File("public/");

        HashMap <String, String> headers = new HashMap<>();

        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "GET");
        attributes.put("path", "/");
        attributes.put("params", "");
        attributes.put("headers", headers);

        Request request = new Request(attributes);

        RouteData routeData = new RouteData();
        routeData.setIsDirectory(true);
        routeData.setContentPath("public/");

        Response response = new Response();

        ContentBuilder contentBuilder = new ContentBuilder(request, routeData, response);
        contentBuilder.assembleContent();

        String bodyToString = new String(response.body());

        assertEquals(true, bodyToString.contains(directoryFile.list()[0]));
    }

    @Test
    public void itDoesNotAssignContentIfNotAGetRequest() throws Exception {
        HashMap <String, String> headers = new HashMap<>();

        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "POST");
        attributes.put("path", "/form");
        attributes.put("params", "");
        attributes.put("headers", headers);

        Request request = new Request(attributes);

        RouteData routeData = new RouteData();

        Response response = new Response();

        ContentBuilder contentBuilder = new ContentBuilder(request, routeData, response);
        contentBuilder.assembleContent();

        assertEquals(null, response.body());
    }

    @Test
    public void itDoesNotGetContentIf404() throws Exception {
        HashMap <String, String> headers = new HashMap<>();

        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "GET");
        attributes.put("path", "/noRoute");
        attributes.put("params", "");
        attributes.put("headers", headers);

        Request request = new Request(attributes);

        RouteData routeData = new RouteData();
        routeData.setNotFound(true);

        Response response = new Response();

        ContentBuilder contentBuilder = new ContentBuilder(request, routeData, response);
        contentBuilder.assembleContent();

        assertEquals(null, response.body());
    }

    @Test
    public void itFindsPartialContentForRangeWithStartAndEnd() throws Exception {
        Path filePath = Paths.get("public/partial_content.txt");
        byte[] partialContent = Arrays.copyOfRange(
                Files.readAllBytes(filePath), 0, 5);

        HashMap <String, String> headers = new HashMap<>();
        headers.put("Range", "bytes=0-4");

        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "GET");
        attributes.put("path", "/");
        attributes.put("params", "");
        attributes.put("headers", headers);

        Request request = new Request(attributes);

        RouteData routeData = new RouteData();
        routeData.setIsPartialContent(true);
        routeData.setContentPath("public/partial_content.txt");

        Response response = new Response();

        ContentBuilder contentBuilder = new ContentBuilder(request, routeData, response);
        contentBuilder.assembleContent();

        String bodyToString = new String(response.body());
        String textToString = new String(partialContent);

        assertEquals(textToString, bodyToString);
    }

    @Test
    public void itFindsPartialContentForRangeWithStartOnly() throws Exception {
        Path filePath = Paths.get("public/partial_content.txt");
        byte[] fileBytes = Files.readAllBytes(filePath);
        byte[] partialContent = Arrays.copyOfRange(
                fileBytes, 4, fileBytes.length);

        HashMap <String, String> headers = new HashMap<>();
        headers.put("Range", "bytes=4-");

        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "GET");
        attributes.put("path", "/");
        attributes.put("params", "");
        attributes.put("headers", headers);

        Request request = new Request(attributes);

        RouteData routeData = new RouteData();
        routeData.setIsPartialContent(true);
        routeData.setContentPath("public/partial_content.txt");

        Response response = new Response();

        ContentBuilder contentBuilder = new ContentBuilder(request, routeData, response);
        contentBuilder.assembleContent();

        String bodyToString = new String(response.body());
        String textToString = new String(partialContent);

        assertEquals(textToString, bodyToString);
    }

    @Test
    public void itFindsPartialContentForRangeWithEndOnly() throws Exception {
        Path filePath = Paths.get("public/partial_content.txt");

        byte[] fileBytes = Files.readAllBytes(filePath);
        byte[] partialContent = Arrays.copyOfRange(
                fileBytes, fileBytes.length - 6, fileBytes.length);

        HashMap <String, String> headers = new HashMap<>();
        headers.put("Range", "bytes=-6");

        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "GET");
        attributes.put("path", "/");
        attributes.put("params", "");
        attributes.put("headers", headers);

        Request request = new Request(attributes);

        RouteData routeData = new RouteData();
        routeData.setIsPartialContent(true);
        routeData.setContentPath("public/partial_content.txt");

        Response response = new Response();

        ContentBuilder contentBuilder = new ContentBuilder(request, routeData, response);
        contentBuilder.assembleContent();

        String bodyToString = new String(response.body());
        String textToString = new String(partialContent);

        assertEquals(textToString, bodyToString);
    }

    @Test
    public void itBuildsContentFromAQueryString() throws Exception {
        HashMap<String, String> headers = new HashMap<>();

        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "GET");
        attributes.put("path", "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff");
        attributes.put("params", "");
        attributes.put("headers", headers);
        Request request = new Request(attributes);

        RouteData routeData = new RouteData();
        routeData.setHasQueryString(true);
        routeData.setQueryString("variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff");

        Response response = new Response();

        ContentBuilder contentBuilder = new ContentBuilder(request, routeData, response);
        contentBuilder.assembleContent();

        String bodyToString = new String(response.body());

        assertEquals("variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?\r\n" +
                "variable_2 = stuff\r\n", bodyToString);
    }
}
