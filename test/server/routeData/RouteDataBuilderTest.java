package server.routeData;

import main.java.server.request.Request;
import main.java.server.routeData.RouteData;
import main.java.server.routeData.RouteDataBuilder;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class RouteDataBuilderTest {

    @Test
    public void createsRouteDataWithNewRedirectPath() throws Exception {
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("method", "GET");
        attributes.put("path", "/redirect");
        attributes.put("params", "");

        Request request = new Request(attributes);

        RouteDataBuilder routeDataBuilder = new RouteDataBuilder();
        RouteData routeData = routeDataBuilder.assembleRouteData(request);

        assertEquals("/", routeData.redirectPath());
    }

    @Test
    public void issuesAFlagIfPathRedirects() throws Exception {
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("method", "GET");
        attributes.put("path", "/redirect");
        attributes.put("params", "");

        Request request = new Request(attributes);

        RouteDataBuilder routeDataBuilder = new RouteDataBuilder();
        RouteData routeData = routeDataBuilder.assembleRouteData(request);

        assertEquals(true, routeData.isRedirect());
    }

    @Test
    public void issuesAFlagIfTheRequestHasAMethodNotAllowed() throws Exception {
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("method", "POST");
        attributes.put("path", "/");
        attributes.put("params", "");

        Request request = new Request(attributes);

        RouteDataBuilder routeDataBuilder = new RouteDataBuilder();
        RouteData routeData = routeDataBuilder.assembleRouteData(request);

        assertEquals(true, routeData.methodNotAllowed());
    }

    @Test
    public void issuesAFlagIfTheRequestHasARouteThatIsNotFound() throws Exception {
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("method", "GET");
        attributes.put("path", "/noRoute");
        attributes.put("params", "");

        Request request = new Request(attributes);

        RouteDataBuilder routeDataBuilder = new RouteDataBuilder();
        RouteData routeData = routeDataBuilder.assembleRouteData(request);

        assertEquals(true, routeData.notFound());
    }

    @Test
    public void issuesAFlagWithTheContentTypeOfDirectoryIfItExists() throws Exception {
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("method", "GET");
        attributes.put("path", "/");
        attributes.put("params", "");

        Request request = new Request(attributes);

        RouteDataBuilder routeDataBuilder = new RouteDataBuilder();
        RouteData routeData = routeDataBuilder.assembleRouteData(request);

        assertEquals(true, routeData.isDirectory());
    }

    @Test
    public void issuesAFlagWithTheContentTypeOfDirectoryIfItRedirects() throws Exception {
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("method", "GET");
        attributes.put("path", "/redirect");
        attributes.put("params", "");

        Request request = new Request(attributes);

        RouteDataBuilder routeDataBuilder = new RouteDataBuilder();
        RouteData routeData = routeDataBuilder.assembleRouteData(request);

        assertEquals(true, routeData.isDirectory());
    }

    @Test
    public void issuesAFlagWithTheContentTypeOfFileIfItExists() throws Exception {
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("method", "GET");
        attributes.put("path", "/file1");
        attributes.put("params", "");

        Request request = new Request(attributes);

        RouteDataBuilder routeDataBuilder = new RouteDataBuilder();
        RouteData routeData = routeDataBuilder.assembleRouteData(request);

        assertEquals(true, routeData.isFile());
    }

    @Test
    public void issuesAFlagIfRequestMethodIsOptions() throws Exception {
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("method", "OPTIONS");
        attributes.put("path", "/method_options");
        attributes.put("params", "");

        Request request = new Request(attributes);

        RouteDataBuilder routeDataBuilder = new RouteDataBuilder();
        RouteData routeData = routeDataBuilder.assembleRouteData(request);

        assertEquals(true, routeData.isOptions());
    }

    @Test
    public void issuesAllowedMethodsIfRequestMethodIsOptions() throws Exception {
        String allowedMethods = "GET,HEAD,POST,OPTIONS,PUT";

        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("method", "OPTIONS");
        attributes.put("path", "/method_options");
        attributes.put("params", "");

        Request request = new Request(attributes);

        RouteDataBuilder routeDataBuilder = new RouteDataBuilder();
        RouteData routeData = routeDataBuilder.assembleRouteData(request);

        assertEquals(allowedMethods, routeData.allowedMethods());
    }
}
