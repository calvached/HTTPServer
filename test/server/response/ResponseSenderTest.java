package server.response;

import main.java.server.response.ResponseSender;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ResponseSenderTest {
    @Test
    public void itSendsAResponse() throws Exception {
        String string = "response body data";
        byte[] bodyData = string.getBytes(Charset.forName("UTF-8"));

        HashMap<String, Object> response = new HashMap<>();
        response.put("statusHeader", "HTTP/1.1 200 OK");
        response.put("body", bodyData);

        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        ResponseSender sender = new ResponseSender(mockOutputStream);
        sender.send(response);

        assertEquals("HTTP/1.1 200 OK\r\n\r\nresponse body data", mockOutputStream.toString().trim());
    }
}
