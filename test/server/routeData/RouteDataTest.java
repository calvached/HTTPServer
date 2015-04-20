package server.routeData;

import main.java.server.routeData.RouteData;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RouteDataTest {
    @Test
    public void setsAFlagIfNotFound() throws Exception {
        RouteData routeData = new RouteData();

        assertEquals(false, routeData.notFound());

        routeData.setNotFound(true);

        assertEquals(true, routeData.notFound());
    }

    @Test
    public void setsAFlagIfMethodNotAllowed() throws Exception {
        RouteData routeData = new RouteData();

        assertEquals(false, routeData.methodNotAllowed());

        routeData.setMethodNotAllowed(true);

        assertEquals(true, routeData.methodNotAllowed());
    }

    @Test
    public void setsAFlagIfIsDirectory() throws Exception {
        RouteData routeData = new RouteData();

        assertEquals(false, routeData.isDirectory());

        routeData.setIsDirectory(true);

        assertEquals(true, routeData.isDirectory());
    }

    @Test
    public void setsAFlagIfIsFile() throws Exception {
        RouteData routeData = new RouteData();

        assertEquals(false, routeData.isFile());

        routeData.setIsFile(true);

        assertEquals(true, routeData.isFile());
    }

    @Test
    public void setsAFlagIfIsRedirect() throws Exception {
        RouteData routeData = new RouteData();

        assertEquals(false, routeData.isRedirect());

        routeData.setIsRedirect(true);

        assertEquals(true, routeData.isRedirect());
    }

    @Test
    public void setsARedirectPathDestination() throws Exception {
        RouteData routeData = new RouteData();

        assertEquals("", routeData.redirectPath());

        routeData.setRedirectPath("/");

        assertEquals("/", routeData.redirectPath());
    }

    @Test
    public void setsAContentPath() throws Exception {
        RouteData routeData = new RouteData();

        assertEquals("", routeData.contentPath());

        routeData.setContentPath("public/");

        assertEquals("public/", routeData.contentPath());
    }

    @Test
    public void setsAFlagIfOptions() throws Exception {
        RouteData routeData = new RouteData();

        assertEquals(false, routeData.isOptions());

        routeData.setIsOptions(true);

        assertEquals(true, routeData.isOptions());
    }

    @Test
    public void setsAllowedMethods() throws Exception {
        RouteData routeData = new RouteData();

        assertEquals("", routeData.allowedMethods());

        routeData.setAllowedMethods("GET,POST,DELETE");

        assertEquals("GET,POST,DELETE", routeData.allowedMethods());
    }
}
