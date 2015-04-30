package main.java.server.response;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class ResponseSender {
    private final OutputStream out;

    public ResponseSender(OutputStream outputStream) {
        out = outputStream;
    }

    public void send(Response response) throws IOException {
        byte[] statusHeader = convertToByteArray(response.statusHeader());
        byte[] blankLine = convertToByteArray("\r\n");
        byte[] headers = convertToByteArray(response.headers());

        out.flush();
        out.write(statusHeader);
        out.write(headers);
        out.write(blankLine);

        if (response.body() != null) {
            out.write(response.body());
        }

        out.flush();
    }

    private byte[] convertToByteArray(String string) {
        return string.getBytes(Charset.forName("UTF-8"));
    }
}
