package server.response;

import main.java.server.response.StatusHeaderBuilder;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class StatusHeaderBuilderTest {

    @Test
    public void itBuildsA200StatusHeader() throws Exception {
        HashMap<String, String> request = new HashMap<>();
        request.put("method", "GET");
        request.put("path", "/");
        request.put("params", "");

        HashMap<String, Object> response = new HashMap<>();

        StatusHeaderBuilder builder = new StatusHeaderBuilder(request, response);

        builder.assembleStatusHeader();

        assertEquals("HTTP/1.1 200 OK", response.get("statusHeader"));
    }

    @Test
    public void itBuildsA302StatusHeader() throws Exception {
        HashMap<String, String> request = new HashMap<>();
        request.put("method", "GET");
        request.put("path", "/person");
        request.put("params", "");
        request.put("redirect", "true");

        HashMap<String, Object> response = new HashMap<>();

        StatusHeaderBuilder builder = new StatusHeaderBuilder(request, response);

        builder.assembleStatusHeader();

        assertEquals("HTTP/1.1 302 Found", response.get("statusHeader"));
    }

    @Test
    public void itBuildsA404StatusHeader() throws Exception {
        HashMap<String, String> request = new HashMap<>();
        request.put("method", "GET");
        request.put("path", "/noRoute");
        request.put("params", "");
        request.put("notFound", "true");

        HashMap<String, Object> response = new HashMap<>();

        StatusHeaderBuilder builder = new StatusHeaderBuilder(request, response);

        builder.assembleStatusHeader();

        assertEquals("HTTP/1.1 404 Not Found", response.get("statusHeader"));
    }
}
