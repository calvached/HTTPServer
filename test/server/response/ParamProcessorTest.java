package server.response;

import main.java.server.request.Request;
import main.java.server.response.ParamProcessor;
import main.java.server.routeData.RouteData;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ParamProcessorTest {
    @Test
    public void itPostsData() throws Exception {
        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "POST");
        attributes.put("path", "/form");
        attributes.put("params", "param data here");
        Request request = new Request(attributes);

        RouteData routeData = new RouteData();
        routeData.setContentPath("test/public/postData.txt");

        ParamProcessor processor = new ParamProcessor(request, routeData);
        processor.process();

        FileReader inputFile = new FileReader("test/public/postData.txt");
        BufferedReader reader = new BufferedReader(inputFile);

        String line = reader.readLine();

        assertEquals("param data here", line);
    }

    @Test
    public void itUpdatesData() throws Exception {
        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "PUT");
        attributes.put("path", "/form");
        attributes.put("params", "different data here");
        Request request = new Request(attributes);

        RouteData routeData = new RouteData();
        routeData.setContentPath("test/public/postData.txt");

        ParamProcessor processor = new ParamProcessor(request, routeData);
        processor.process();

        FileReader inputFile = new FileReader("test/public/postData.txt");
        BufferedReader reader = new BufferedReader(inputFile);

        String line = reader.readLine();

        assertEquals("different data here", line);
    }

    @Test
    public void itDeletesData() throws Exception {
        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "DELETE");
        attributes.put("path", "/form");
        attributes.put("params", "");
        Request request = new Request(attributes);

        RouteData routeData = new RouteData();
        routeData.setContentPath("test/public/postData.txt");

        ParamProcessor processor = new ParamProcessor(request, routeData);
        processor.process();

        FileReader inputFile = new FileReader("test/public/postData.txt");
        BufferedReader reader = new BufferedReader(inputFile);

        String line = reader.readLine();

        assertEquals(" ", line);
    }

    @Test
    public void itUpdatesDataIfPatch() throws Exception {
        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "PATCH");
        attributes.put("path", "/form");
        attributes.put("params", "different data here");
        Request request = new Request(attributes);

        RouteData routeData = new RouteData();
        routeData.setContentPath("test/public/postData.txt");

        ParamProcessor processor = new ParamProcessor(request, routeData);
        processor.process();

        FileReader inputFile = new FileReader("test/public/postData.txt");
        BufferedReader reader = new BufferedReader(inputFile);

        String line = reader.readLine();

        assertEquals("different data here", line);
    }
}
