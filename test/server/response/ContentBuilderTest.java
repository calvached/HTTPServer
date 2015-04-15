package server.response;

import main.java.server.response.ContentBuilder;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ContentBuilderTest {

    @Test
    public void itFindsContentForAGetRequest() throws Exception {
        HashMap<String, String> request = new HashMap<>();
        request.put("method", "GET");
        request.put("path", "/");
        request.put("params", "");
        request.put("content", "public/file.txt");
        request.put("isFile", "true");

        HashMap<String, Object> response = new HashMap<>();
        response.put("statusHeader", "HTTP/1.1 200 OK");

        ContentBuilder contentBuilder = new ContentBuilder(request, response);
        contentBuilder.assembleContent();

        assertEquals(true, response.containsKey("body"));
    }

    @Test
    public void itFindsContentForADirectory() throws Exception {
        HashMap<String, String> request = new HashMap<>();
        request.put("method", "GET");
        request.put("path", "/");
        request.put("params", "");
        request.put("content", "public/");
        request.put("isDirectory", "true");

        HashMap<String, Object> response = new HashMap<>();
        response.put("statusHeader", "HTTP/1.1 200 OK");

        ContentBuilder contentBuilder = new ContentBuilder(request, response);
        contentBuilder.assembleContent();

        assertEquals(true, response.containsKey("body"));
    }

    @Test
    public void itDoesNotAssignContentIfNotAGetRequest() throws Exception {
        HashMap<String, String> request = new HashMap<>();
        request.put("method", "POST");
        request.put("path", "/");
        request.put("params", "");
        request.put("content", "public/file.txt");

        HashMap<String, Object> response = new HashMap<>();
        response.put("statusHeader", "HTTP/1.1 200 OK");

        ContentBuilder contentBuilder = new ContentBuilder(request, response);
        contentBuilder.assembleContent();

        assertEquals(false, response.containsKey("body"));
    }

    @Test
    public void itDoesNotGetContentIf404() throws Exception {
        HashMap<String, String> request = new HashMap<>();
        request.put("method", "POST");
        request.put("path", "/noRoute");
        request.put("params", "");
        request.put("content", "public/file.txt");
        request.put("notFound", "true");

        HashMap<String, Object> response = new HashMap<>();

        ContentBuilder contentBuilder = new ContentBuilder(request, response);
        contentBuilder.assembleContent();

        assertEquals(false, response.containsKey("body"));
    }
}
