package server.method;

import main.java.server.method.PostHandler;
import main.java.server.method.PutHandler;
import main.java.server.request.Request;
import main.java.server.response.ResponseBuilder;
import main.java.server.response.ResponseWriter;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class PutHandlerTest {

    @Test
    public void respondsWithA200IfPUTRouteExists() throws Exception {
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

        PutHandler put = new PutHandler(builder);
        put.handle(request);

        assertEquals("HTTP/1.1 200 OK", mockOutputStream.toString().trim());
    }

    @Test
    public void updatesDataInAFile() throws Exception {
        HashMap postRequest = new HashMap();
        postRequest.put("requestMethod", "POST");
        postRequest.put("url", "/form");
        postRequest.put("data", "My=data");

        HashMap putRequest = new HashMap();
        putRequest.put("requestMethod", "PUT");
        putRequest.put("url", "/form");
        putRequest.put("data", "Your=newData");

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        Request firstRequest = new Request(postRequest);

        ResponseBuilder builder =
                new ResponseBuilder(
                        new ResponseWriter(mockOutputStream));

        PostHandler post = new PostHandler(builder);
        post.handle(firstRequest);

        FileReader inputFile = new FileReader("public/postData.txt");
        BufferedReader reader = new BufferedReader(inputFile);

        String line = reader.readLine();

        assertEquals("My=data", line);

        Request secondRequest = new Request(putRequest);

        FileReader inputFile2 = new FileReader("public/postData.txt");
        BufferedReader reader2 = new BufferedReader(inputFile2);

        PutHandler put = new PutHandler(builder);
        put.handle(secondRequest);

        String updatedLine = reader2.readLine();

        assertEquals("Your=newData", updatedLine);
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

        PutHandler put = new PutHandler(builder);
        put.handle(request);

        assertEquals("HTTP/1.1 404 Not Found", mockOutputStream.toString().trim());
    }
}
