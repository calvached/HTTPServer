package main.java.server.response;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResponseWriter {
    private OutputStream out;

    public ResponseWriter(OutputStream outputStream) {
        out = outputStream;
    }

    public void write(String string) throws IOException {
        byte[] data = string.getBytes(Charset.forName("UTF-8"));

        out.write(data);
    }

    public void write(Path path) throws IOException {
        byte[] data = Files.readAllBytes(path);

        out.write(data);
    }

    public void flush() throws IOException {
       out.flush();
    }
}
