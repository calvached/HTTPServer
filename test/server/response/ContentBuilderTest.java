package server.response;

import main.java.server.response.ContentBuilder;
import main.java.server.response.Response;
import main.java.server.routeData.RouteData;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class ContentBuilderTest {

    @Test
    public void itFindsContentForAGetRequest() throws Exception {
        Path filePath = Paths.get("public/file.txt");
        String textToString = new String(Files.readAllBytes(filePath));

        RouteData routeData = new RouteData();
        routeData.setIsFile(true);
        routeData.setContentPath("public/file.txt");

        Response response = new Response();

        ContentBuilder contentBuilder = new ContentBuilder("GET", routeData, response);
        contentBuilder.assembleContent();

        String bodyToString = new String(response.body());

        assertEquals(textToString, bodyToString);
    }

    @Test
    public void itFindsContentForADirectory() throws Exception {
        File directoryFile = new File("public/");

        RouteData routeData = new RouteData();
        routeData.setIsDirectory(true);
        routeData.setContentPath("public/");

        Response response = new Response();

        ContentBuilder contentBuilder = new ContentBuilder("GET", routeData, response);
        contentBuilder.assembleContent();

        String bodyToString = new String(response.body());

        assertEquals(true, bodyToString.contains(directoryFile.list()[0]));
    }

    @Test
    public void itDoesNotAssignContentIfNotAGetRequest() throws Exception {
        RouteData routeData = new RouteData();

        Response response = new Response();

        ContentBuilder contentBuilder = new ContentBuilder("POST", routeData, response);
        contentBuilder.assembleContent();

        assertEquals(null, response.body());
    }

    @Test
    public void itDoesNotGetContentIf404() throws Exception {
        RouteData routeData = new RouteData();
        routeData.setNotFound(true);

        Response response = new Response();

        ContentBuilder contentBuilder = new ContentBuilder("POST", routeData, response);
        contentBuilder.assembleContent();

        assertEquals(null, response.body());
    }
}
