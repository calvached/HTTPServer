package server.response;

import main.java.server.response.ResponseWriter;
import mocks.MockOutputStream;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class ResponseWriterTest {
    @Test
    public void itWritesAStringToTheStream() throws Exception {
        MockOutputStream out = new MockOutputStream();
        ResponseWriter writer = new ResponseWriter(out);

        assertEquals(false, out.successfulWrite);

        writer.write("Hello");

        assertEquals(true, out.successfulWrite);
    }

    @Test
    public void itWritesAFileToTheStream() throws Exception {
        MockOutputStream out = new MockOutputStream();
        ResponseWriter writer = new ResponseWriter(out);

        assertEquals(false, out.successfulWrite);

        Path filePath = Paths.get("public/sample.pdf");
        writer.write(filePath);

        assertEquals(true, out.successfulWrite);
    }

    @Test
    public void itWritesAnImageToTheStream() throws Exception {
        MockOutputStream out = new MockOutputStream();
        ResponseWriter writer = new ResponseWriter(out);

        assertEquals(false, out.successfulWrite);

        Path imgPath = Paths.get("public/mindblown.gif");
        writer.write(imgPath);

        assertEquals(true, out.successfulWrite);
    }
}
