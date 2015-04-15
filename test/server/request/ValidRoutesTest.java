package server.request;

import main.java.server.request.ValidRoutes;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ValidRoutesTest {
    @Test
    public void itReturnsARedirectRouteIfRedirecting() throws Exception {
        ValidRoutes validRoutes = new ValidRoutes();
        HashMap pathOptions = validRoutes.routes.get("/redirect");

        assertEquals(true, pathOptions.containsKey("redirectPath"));
    }

    @Test
    public void itReturnsAllowedMethodsForIndexPage() throws Exception {
        ValidRoutes validRoutes = new ValidRoutes();

        HashMap data = validRoutes.routes.get("/");
        String[] methods = (String[]) data.get("allowedMethods");

        assertEquals(true, Arrays.asList(methods).contains("GET"));
    }

    @Test
    public void itReturnsContentIfItExists() throws Exception {
        ValidRoutes validRoutes = new ValidRoutes();

        HashMap data = validRoutes.routes.get("/");
        String content = (String) data.get("content");

        assertEquals("../cob_spec/public", content);
    }

    @Test
    public void itReturnsAllowedMethodsForMethodOptionsPage() throws Exception {
        ValidRoutes validRoutes = new ValidRoutes();

        HashMap data = validRoutes.routes.get("/method_options");
        String[] methods = (String[]) data.get("allowedMethods");

        assertEquals(true, Arrays.asList(methods).contains("GET"));
        assertEquals(true, Arrays.asList(methods).contains("HEAD"));
        assertEquals(true, Arrays.asList(methods).contains("POST"));
        assertEquals(true, Arrays.asList(methods).contains("OPTIONS"));
        assertEquals(true, Arrays.asList(methods).contains("PUT"));
    }
}
