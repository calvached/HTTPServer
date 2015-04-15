package server.response;

import main.java.server.response.ParamProcessor;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ParamProcessorTest {
    @Test
    public void itPostsData() throws Exception {
        HashMap<String, String> request = new HashMap<>();
        request.put("method", "POST");
        request.put("path", "/form");
        request.put("params", "param data here");
        request.put("content", "public/postData.txt");

        ParamProcessor processor = new ParamProcessor(request);
        processor.process();

        FileReader inputFile = new FileReader("public/postData.txt");
        BufferedReader reader = new BufferedReader(inputFile);

        String line = reader.readLine();

        assertEquals("param data here", line);
    }

    @Test
    public void itUpdatesData() throws Exception {
        HashMap<String, String> request = new HashMap<>();
        request.put("method", "PUT");
        request.put("path", "/form");
        request.put("params", "different data here");
        request.put("content", "public/postData.txt");

        ParamProcessor processor = new ParamProcessor(request);
        processor.process();

        FileReader inputFile = new FileReader("public/postData.txt");
        BufferedReader reader = new BufferedReader(inputFile);

        String line = reader.readLine();

        assertEquals("different data here", line);
    }

    @Test
    public void itDeletesData() throws Exception {
        HashMap<String, String> request = new HashMap<>();
        request.put("method", "DELETE");
        request.put("path", "/form");
        request.put("params", "");
        request.put("content", "public/postData.txt");

        ParamProcessor processor = new ParamProcessor(request);
        processor.process();

        FileReader inputFile = new FileReader("public/postData.txt");
        BufferedReader reader = new BufferedReader(inputFile);

        String line = reader.readLine();

        assertEquals(" ", line);
    }
}
