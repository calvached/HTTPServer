package server.response;

import main.java.server.response.Response;
import org.junit.Test;

import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;

public class ResponseTest {
    @Test
    public void itSetsAStatusHeader() throws Exception {
        Response response = new Response();

        assertEquals("", response.statusHeader());

        response.setStatusHeader("HTTP/1.1 200 OK");

        assertEquals("HTTP/1.1 200 OK", response.statusHeader());
    }

    @Test
    public void itSetsBodyContent() throws Exception {
        String string = "hello";
        byte[] bytes = string.getBytes(Charset.forName("UTF-8"));

        Response response = new Response();

        assertEquals(null, response.body());

        response.setBody(bytes);

        assertEquals(bytes, response.body());
    }

    @Test
    public void itSetsHeaders() throws Exception {
        Response response = new Response();

        assertEquals("", response.headers());

        response.setHeader("Header: First Header\r\n");
        response.setHeader("AnotherHeader: Second Header\r\n");

        assertEquals(
                "Header: First Header\r\n" +
                "AnotherHeader: Second Header\r\n", response.headers());
    }
}
