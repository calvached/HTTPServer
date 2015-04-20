package server.response;

import main.java.server.response.Response;
import main.java.server.response.ResponseSender;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;

public class ResponseSenderTest {
    @Test
    public void itSendsAResponseWithHeaders() throws Exception {
        String string = "response body data";
        byte[] bodyData = string.getBytes(Charset.forName("UTF-8"));

        Response response = new Response();
        response.setStatusHeader("HTTP/1.1 200 OK\r\n");
        response.setHeader("Header1: First Header\r\n");
        response.setBody(bodyData);

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        ResponseSender sender = new ResponseSender(mockOutputStream);
        sender.send(response);

        assertEquals("HTTP/1.1 200 OK\r\nHeader1: First Header\r\n\r\nresponse body data", mockOutputStream.toString().trim());
    }

    @Test
    public void itSendsAResponseWithoutHeaders() throws Exception {
        String string = "response body data";
        byte[] bodyData = string.getBytes(Charset.forName("UTF-8"));

        Response response = new Response();
        response.setStatusHeader("HTTP/1.1 200 OK\r\n");
        response.setBody(bodyData);

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        ResponseSender sender = new ResponseSender(mockOutputStream);
        sender.send(response);

        assertEquals("HTTP/1.1 200 OK\r\n\r\nresponse body data", mockOutputStream.toString().trim());
    }
}
