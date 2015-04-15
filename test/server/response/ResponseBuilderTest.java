package server.response;

import main.java.server.response.ResponseBuilder;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ResponseBuilderTest {

    @Test
    public void itReturnsAResponseToAGet() throws Exception {
        HashMap<String, String> request = new HashMap<>();
        request.put("method", "GET");
        request.put("path", "/file.txt");
        request.put("params", "");
        request.put("content", "public/file.txt");


        ResponseBuilder responseBuilder = new ResponseBuilder(request);
        HashMap response = responseBuilder.getResponse();

        assertEquals(true, response.containsKey("statusHeader"));
        assertEquals(true, response.containsKey("body"));
    }

    @Test
    public void itReturnsAResponseToAPost() throws Exception {
        HashMap<String, String> request = new HashMap<>();
        request.put("method", "POST");
        request.put("path", "/form");
        request.put("params", "some data");
        request.put("content", "public/postData.txt");


        ResponseBuilder responseBuilder = new ResponseBuilder(request);
        HashMap response = responseBuilder.getResponse();

        assertEquals(true, response.containsKey("statusHeader"));
        assertEquals(false, response.containsKey("body"));
    }

    @Test
    public void itReturnsAResponseToAPut() throws Exception {
        HashMap<String, String> request = new HashMap<>();
        request.put("method", "PUT");
        request.put("path", "/form");
        request.put("params", "different data");
        request.put("content", "public/postData.txt");


        ResponseBuilder responseBuilder = new ResponseBuilder(request);
        HashMap response = responseBuilder.getResponse();

        assertEquals(true, response.containsKey("statusHeader"));
        assertEquals(false, response.containsKey("body"));
    }

    @Test
    public void itReturnsAResponseToADelete() throws Exception {
        HashMap<String, String> request = new HashMap<>();
        request.put("method", "DELETE");
        request.put("path", "/form");
        request.put("params", "");
        request.put("content", "public/postData.txt");


        ResponseBuilder responseBuilder = new ResponseBuilder(request);
        HashMap response = responseBuilder.getResponse();

        assertEquals(true, response.containsKey("statusHeader"));
        assertEquals(false, response.containsKey("body"));
    }

    @Test
    public void itReturnsAResponseWithALocationHeader() throws Exception {
        HashMap<String, String> request = new HashMap<>();
        request.put("method", "GET");
        request.put("path", "/");
        request.put("params", "");
        request.put("content", "public/");
        request.put("isDirectory", "true");
        request.put("redirect", "true");


        ResponseBuilder responseBuilder = new ResponseBuilder(request);
        HashMap response = responseBuilder.getResponse();

        assertEquals(true, response.containsKey("statusHeader"));
        assertEquals(true, response.containsKey("body"));
        assertEquals(true, response.containsKey("header"));
    }
}
