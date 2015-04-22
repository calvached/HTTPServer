package server;

import main.java.server.HttpServer;
import main.java.server.router.Router;
import mocks.MockBadServerSocket;
import mocks.MockServerSocket;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HttpServerTest {

    @Test
    public void itRespondsToARequest() throws Exception {
        MockServerSocket serverSocket = new MockServerSocket(3000);

        HttpServer server = new HttpServer(serverSocket);
        server.start();

        String outputResponse = serverSocket.getOutputResponse();

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
        MockBadServerSocket serverSocket = new MockBadServerSocket(3000);

        HttpServer server = new HttpServer(serverSocket);
        server.start();

        String outputResponse = serverSocket.getOutputResponse();

        assertEquals("HTTP/1.1 400 Bad Request\r\n\r\n", outputResponse);
    }
}
