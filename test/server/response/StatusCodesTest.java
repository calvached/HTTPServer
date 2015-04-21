package server.response;

import main.java.server.response.StatusCodes;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatusCodesTest {

    @Test
    public void itReturnsA200HeaderLine() throws Exception {
        StatusCodes codes = new StatusCodes();

        assertEquals("200 OK", codes.headers.get(200));
    }

    @Test
    public void itReturnsA204HeaderLine() throws Exception {
        StatusCodes codes = new StatusCodes();

        assertEquals("204 No Content", codes.headers.get(204));
    }

    @Test
    public void itReturnsA302HeaderLine() throws Exception {
        StatusCodes codes = new StatusCodes();

        assertEquals("302 Found", codes.headers.get(302));
    }

    @Test
    public void itReturnsA404HeaderLine() throws Exception {
        StatusCodes codes = new StatusCodes();

        assertEquals("404 Not Found", codes.headers.get(404));
    }

    @Test
    public void itReturnsA405HeaderLine() throws Exception {
        StatusCodes codes = new StatusCodes();

        assertEquals("405 Method Not Allowed", codes.headers.get(405));
    }
}
