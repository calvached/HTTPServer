package mocks;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MockSocket extends Socket{
    private InputStream in;
    private OutputStream out;

    public MockSocket() {
        in = createInputStream();
        out = createOutputStream();
    }

    private OutputStream createOutputStream() {
        ByteArrayOutputStream mockOutputStream =
                new ByteArrayOutputStream();

        return mockOutputStream;
    }

    private InputStream createInputStream() {
        String testString = "GET / HTTP/1.1\r\nContent-type: text/plain\r\n";

        InputStream mockInputStream =
                new ByteArrayInputStream(
                        testString.getBytes(StandardCharsets.UTF_8));

        return mockInputStream;
    }

    public InputStream getInputStream() {
        return in;
    }

    public OutputStream getOutputStream() {
        return out;
    }
}
