package server.response.statusHeader;

import main.java.server.response.statusHeader.StatusCodes;
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
    public void itReturnsA206HeaderLine() throws Exception {
        StatusCodes codes = new StatusCodes();

        assertEquals("206 Partial Content", codes.headers.get(206));
    }

    @Test
    public void itReturnsA302HeaderLine() throws Exception {
        StatusCodes codes = new StatusCodes();

        assertEquals("302 Found", codes.headers.get(302));
    }

    @Test
    public void itReturnsA400HeaderLine() throws Exception {
        StatusCodes codes = new StatusCodes();

        assertEquals("400 Bad Request", codes.headers.get(400));
    }

    @Test
    public void itReturnsA401HeaderLine() throws Exception {
        StatusCodes codes = new StatusCodes();

        assertEquals("401 Unauthorized", codes.headers.get(401));
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
