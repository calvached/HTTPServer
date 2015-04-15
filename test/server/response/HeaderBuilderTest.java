package server.response;

import main.java.server.response.HeaderBuilder;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class HeaderBuilderTest {
    @Test
    public void itAssemblesALocationHeaderForARedirect() throws Exception {
        HashMap<String, String> request = new HashMap<>();
        request.put("method", "GET");
        request.put("path", "/");
        request.put("params", "");
        request.put("content", "public/");
        request.put("redirect", "true");

        HashMap<String, Object> response = new HashMap<>();

        HeaderBuilder headerBuilder = new HeaderBuilder(request, response);
        headerBuilder.assembleHeaders();

        assertEquals("Location: http://localhost:5000/", response.get("header"));
    }
}
