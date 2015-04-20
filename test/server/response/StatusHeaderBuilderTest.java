package server.response;

import main.java.server.response.Response;
import main.java.server.response.StatusHeaderBuilder;
import main.java.server.routeData.RouteData;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatusHeaderBuilderTest {

    @Test
    public void itBuildsA200StatusHeader() throws Exception {
        RouteData routeData = new RouteData();

        Response response = new Response();

        StatusHeaderBuilder builder = new StatusHeaderBuilder(routeData, response);

        builder.assembleStatusHeader();

        assertEquals("HTTP/1.1 200 OK\r\n", response.statusHeader());
    }

    @Test
    public void itBuildsA302StatusHeader() throws Exception {
        RouteData routeData = new RouteData();
        routeData.setIsRedirect(true);

        Response response = new Response();

        StatusHeaderBuilder builder = new StatusHeaderBuilder(routeData, response);

        builder.assembleStatusHeader();

        assertEquals("HTTP/1.1 302 Found\r\n", response.statusHeader());
    }

    @Test
    public void itBuildsA404StatusHeader() throws Exception {
        RouteData routeData = new RouteData();
        routeData.setNotFound(true);

        Response response = new Response();

        StatusHeaderBuilder builder = new StatusHeaderBuilder(routeData, response);

        builder.assembleStatusHeader();

        assertEquals("HTTP/1.1 404 Not Found\r\n", response.statusHeader());
    }

    @Test
    public void itBuildsA405StatusHeader() throws Exception {
        RouteData routeData = new RouteData();
        routeData.setMethodNotAllowed(true);

        Response response = new Response();

        StatusHeaderBuilder builder = new StatusHeaderBuilder(routeData, response);

        builder.assembleStatusHeader();

        assertEquals("HTTP/1.1 405 Method Not Allowed\r\n", response.statusHeader());
    }
}
