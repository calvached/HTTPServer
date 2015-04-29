package server.response;

import main.java.server.response.HeaderBuilder;
import main.java.server.response.Response;
import main.java.server.routeData.RouteData;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeaderBuilderTest {

    @Test
    public void itAssemblesALocationHeaderForARedirect() throws Exception {
        RouteData routeData = new RouteData();
        routeData.setIsRedirect(true);
        routeData.setRedirectPath("/");
        routeData.setContentPath("test/public/");

        Response response = new Response();

        HeaderBuilder headerBuilder = new HeaderBuilder(routeData, response);
        headerBuilder.assembleHeaders();

        assertEquals("Location: http://localhost:5000/\r\n", response.headers());
    }

    @Test
    public void itAssemblesAnAllowHeaderForOptions() throws Exception {
        RouteData routeData = new RouteData();
        routeData.setContentPath("test/public/postData.txt");
        routeData.setIsOptions(true);
        routeData.setAllowedMethods("GET, HEAD, POST, OPTIONS, PUT");

        Response response = new Response();

        HeaderBuilder headerBuilder = new HeaderBuilder(routeData, response);
        headerBuilder.assembleHeaders();

        assertEquals("Allow: GET, HEAD, POST, OPTIONS, PUT\r\n", response.headers());
    }
}
