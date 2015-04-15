package main.java.server.response;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;

public class ResponseSender {
    private final OutputStream out;

    public ResponseSender(OutputStream outputStream) {
        out = outputStream;
    }

    public void send(HashMap<String, Object> response) throws IOException {
        byte[] statusHeader = convertToByteArray((String) response.get("statusHeader"));
        byte[] blankLine = convertToByteArray("\r\n");

        out.flush();
        out.write(statusHeader);
        out.write(blankLine);

        if (response.containsKey("header")) {
            byte[] header = convertToByteArray((String) response.get("header"));
            out.write(header);
        }

        out.write(blankLine);

        if (response.containsKey("body")) {
            out.write((byte[]) response.get("body"));
        }
        out.flush();
    }

    private byte[] convertToByteArray(String string) {
        return string.getBytes(Charset.forName("UTF-8"));
    }

}
