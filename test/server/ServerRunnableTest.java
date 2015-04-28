package server;

import main.java.server.ServerRunnable;
import mocks.MockBadSocket;
import mocks.MockSocket;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServerRunnableTest {
    @Test
    public void itRespondsToARequest() throws Exception {
        MockSocket socket = new MockSocket();
        ServerRunnable runner = new ServerRunnable(socket);

        runner.run();

        String outputResponse = socket.getOutputStream().toString();

        assertEquals("HTTP/1.1 200 OK\r\n" +
                "\r\n" +
                "<a href='/file1'>file1</a>\r\n" +
                "<a href='/file2'>file2</a>\r\n" +
                "<a href='/image.gif'>image.gif</a>\r\n" +
                "<a href='/image.jpeg'>image.jpeg</a>\r\n" +
                "<a href='/image.png'>image.png</a>\r\n" +
                "<a href='/partial_content.txt'>partial_content.txt</a>\r\n" +
                "<a href='/patch-content.txt'>patch-content.txt</a>\r\n" +
                "<a href='/postData.txt'>postData.txt</a>\r\n" +
                "<a href='/text-file.txt'>text-file.txt</a>\r\n", outputResponse);
    }

    @Test
    public void itRespondsToABadRequest() throws Exception {
        MockBadSocket socket = new MockBadSocket();
        ServerRunnable runner = new ServerRunnable(socket);

        runner.run();

        String outputResponse = socket.getOutputStream().toString();

        assertEquals("HTTP/1.1 400 Bad Request\r\n\r\n", outputResponse);
    }
}
