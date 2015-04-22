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
        HashMap<String, Object> attributes = new HashMap<>();
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
        HashMap<String, Object> attributes = new HashMap<>();
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
        HashMap<String, String> headers = new HashMap<>();

        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "POST");
        attributes.put("path", "/");
        attributes.put("params", "");
        attributes.put("headers", headers);

        Request request = new Request(attributes);

        RouteDataBuilder routeDataBuilder = new RouteDataBuilder();
        RouteData routeData = routeDataBuilder.assembleRouteData(request);

        assertEquals(true, routeData.methodNotAllowed());
    }

    @Test
    public void issuesAFlagIfTheRequestHasARouteThatIsNotFound() throws Exception {
        HashMap<String, Object> attributes = new HashMap<>();
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
        HashMap<String, String> headers = new HashMap<>();

        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "GET");
        attributes.put("path", "/");
        attributes.put("params", "");
        attributes.put("headers", headers);

        Request request = new Request(attributes);

        RouteDataBuilder routeDataBuilder = new RouteDataBuilder();
        RouteData routeData = routeDataBuilder.assembleRouteData(request);

        assertEquals(true, routeData.isDirectory());
    }

    @Test
    public void issuesAFlagWithTheContentTypeOfDirectoryIfItRedirects() throws Exception {

        HashMap<String, Object> attributes = new HashMap<>();
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
        HashMap<String, String> headers = new HashMap<>();

        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "GET");
        attributes.put("path", "/file1");
        attributes.put("params", "");
        attributes.put("headers", headers);

        Request request = new Request(attributes);

        RouteDataBuilder routeDataBuilder = new RouteDataBuilder();
        RouteData routeData = routeDataBuilder.assembleRouteData(request);

        assertEquals(true, routeData.isFile());
    }

    @Test
    public void issuesAFlagIfRequestMethodIsOptions() throws Exception {
        HashMap<String, Object> attributes = new HashMap<>();
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

        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "OPTIONS");
        attributes.put("path", "/method_options");
        attributes.put("params", "");

        Request request = new Request(attributes);

        RouteDataBuilder routeDataBuilder = new RouteDataBuilder();
        RouteData routeData = routeDataBuilder.assembleRouteData(request);

        assertEquals(allowedMethods, routeData.allowedMethods());
    }

    @Test
    public void issuesAFlagIfAPatch() throws Exception {
        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "PATCH");
        attributes.put("path", "/patch-content.txt");
        attributes.put("params", "patched content");

        Request request = new Request(attributes);

        RouteDataBuilder routeDataBuilder = new RouteDataBuilder();
        RouteData routeData = routeDataBuilder.assembleRouteData(request);

        assertEquals(true, routeData.isPatch());
    }

    @Test
    public void issuesAFlagIfPartialContent() throws Exception {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Range", "bytes=0-4");

        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "GET");
        attributes.put("path", "/patch-content.txt");
        attributes.put("params", "patched content");
        attributes.put("headers", headers);

        Request request = new Request(attributes);

        RouteDataBuilder routeDataBuilder = new RouteDataBuilder();
        RouteData routeData = routeDataBuilder.assembleRouteData(request);

        assertEquals(true, routeData.isPartialContent());
    }

    @Test
    public void issuesAFlagIfPathHasQueryString() throws Exception {
        HashMap<String, String> headers = new HashMap<>();

        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "GET");
        attributes.put("path", "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff");
        attributes.put("params", "");
        attributes.put("headers", headers);

        Request request = new Request(attributes);

        RouteDataBuilder routeDataBuilder = new RouteDataBuilder();
        RouteData routeData = routeDataBuilder.assembleRouteData(request);

        assertEquals(true, routeData.hasQueryString());
    }

    @Test
    public void issuesParamsIfPathHasQueryString() throws Exception {
        HashMap<String, String> headers = new HashMap<>();

        HashMap<String, Object> attributes = new HashMap<>();
        attributes.put("method", "GET");
        attributes.put("path", "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff");
        attributes.put("params", "");
        attributes.put("headers", headers);

        Request request = new Request(attributes);

        RouteDataBuilder routeDataBuilder = new RouteDataBuilder();
        RouteData routeData = routeDataBuilder.assembleRouteData(request);

        assertEquals("variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff",
                routeData.queryString());
    }
}
