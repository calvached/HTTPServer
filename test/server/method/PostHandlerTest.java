package server.method;

import main.java.server.method.PostHandler;
import main.java.server.request.Request;
import main.java.server.response.ResponseBuilder;
import main.java.server.response.ResponseWriter;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class PostHandlerTest {

    @Test
    public void respondsWithA200IfPOSTRouteExists() throws Exception { HashMap attributes = new HashMap();
        attributes.put("requestMethod", "POST");
        attributes.put("url", "/form");
        attributes.put("data", "My=data");

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        Request request = new Request(attributes);

        ResponseBuilder builder =
                new ResponseBuilder(
                        new ResponseWriter(mockOutputStream));

        PostHandler post = new PostHandler(builder);
        post.handle(request);

        assertEquals("HTTP/1.1 200 OK", mockOutputStream.toString().trim());
    }

    @Test
    public void savesPostDataToAFile() throws Exception { HashMap attributes = new HashMap();
        attributes.put("requestMethod", "POST");
        attributes.put("url", "/form");
        attributes.put("data", "My=data");

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        Request request = new Request(attributes);

        ResponseBuilder builder =
                new ResponseBuilder(
                        new ResponseWriter(mockOutputStream));

        PostHandler post = new PostHandler(builder);
        post.handle(request);

        FileReader inputFile = new FileReader("public/postData.txt");
        BufferedReader reader = new BufferedReader(inputFile);

        String line = reader.readLine();

        assertEquals("My=data", line);
    }

    @Test
    public void respondsWithA404IfRouteDoesNotExist() throws Exception { HashMap attributes = new HashMap();
        attributes.put("requestMethod", "POST");
        attributes.put("url", "/cat");
        attributes.put("data", "My=data");

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        Request request = new Request(attributes);

        ResponseBuilder builder =
                new ResponseBuilder(
                        new ResponseWriter(mockOutputStream));

        PostHandler post = new PostHandler(builder);
        post.handle(request);

        assertEquals("HTTP/1.1 404 Not Found", mockOutputStream.toString().trim());
    }
}
