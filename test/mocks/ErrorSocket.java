package mocks;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ErrorSocket extends Socket {
    private InputStream in;
    private OutputStream out;
    private boolean IOExceptionThrown = false;

    public ErrorSocket() {
        in = createInputStream();
        out = createOutputStream();
    }

    public void close() throws IOException {
        IOExceptionThrown = true;
        throw new IOException("Close");
    }

    public boolean isIOExceptionThrown() {
        return IOExceptionThrown;
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
