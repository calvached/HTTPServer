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
    public void setsAFlagIfPatch() throws Exception {
        RouteData routeData = new RouteData();

        assertEquals(false, routeData.isPatch());

        routeData.setIsPatch(true);

        assertEquals(true, routeData.isPatch());
    }

    @Test
    public void setsAFlagIfPartialContent() throws Exception {
        RouteData routeData = new RouteData();

        assertEquals(false, routeData.isPartialContent());

        routeData.setIsPartialContent(true);

        assertEquals(true, routeData.isPartialContent());
    }

    @Test
    public void setsAFlagIfQueryStringExists() throws Exception {
        RouteData routeData = new RouteData();

        assertEquals(false, routeData.hasQueryString());

        routeData.setHasQueryString(true);

        assertEquals(true, routeData.hasQueryString());
    }

    @Test
    public void setsAQueryString() throws Exception {
        RouteData routeData = new RouteData();

        assertEquals("", routeData.queryString());

        routeData.setQueryString("variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff");

        assertEquals("variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff",
                routeData.queryString());
    }

    @Test
    public void setsAllowedMethods() throws Exception {
        RouteData routeData = new RouteData();

        assertEquals("", routeData.allowedMethods());

        routeData.setAllowedMethods("GET,POST,DELETE");

        assertEquals("GET,POST,DELETE", routeData.allowedMethods());
    }

    @Test
    public void setsAFlagIfAuthenticationIsRequired() throws Exception {
        RouteData routeData = new RouteData();

        assertEquals(false, routeData.requireAuthentication());

        routeData.setRequireAuthentication(true);

        assertEquals(true, routeData.requireAuthentication());
    }

    @Test
    public void setsAFlagIfAuthorizationIsGranted() throws Exception {
        RouteData routeData = new RouteData();

        assertEquals(false, routeData.authorization());

        routeData.setAuthorization(true);

        assertEquals(true, routeData.authorization());
    }

    @Test
    public void setsAFlagBadRequest() throws Exception {
        RouteData routeData = new RouteData();

        assertEquals(false, routeData.badRequest());

        routeData.setBadRequest(true);

        assertEquals(true, routeData.badRequest());
    }
}
