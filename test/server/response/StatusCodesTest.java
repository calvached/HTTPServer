package server.response;

import main.java.server.response.StatusCodes;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatusCodesTest {

    @Test
    public void itReturnsA200HeaderLine() throws Exception {
        StatusCodes codes = new StatusCodes();

        assertEquals("HTTP/1.1 200 OK", codes.headerLines.get(200));
    }

    @Test
    public void itReturnsA404HeaderLine() throws Exception {
        StatusCodes codes = new StatusCodes();

        assertEquals("HTTP/1.1 404 Not Found", codes.headerLines.get(404));
    }
}
