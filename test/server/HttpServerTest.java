package server;

import main.java.server.HttpServer;
import main.java.server.router.Router;
import mocks.MockServerSocket;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HttpServerTest {

    @Test
    public void itRespondsToARequest() throws Exception {
        MockServerSocket serverSocket = new MockServerSocket(3000);
        Router router = new Router();

        HttpServer server = new HttpServer(serverSocket, router);
        server.start();

        String outputResponse = serverSocket.getOutputResponse();

        assertEquals("HTTP/1.1 200 OK\r\n" +
                "\r\n" +
                "<a href='http://localhost:5000/file1'>file1</a>\r\n" +
                "<a href='http://localhost:5000/file2'>file2</a>\r\n" +
                "<a href='http://localhost:5000/image.gif'>image.gif</a>\r\n" +
                "<a href='http://localhost:5000/image.jpeg'>image.jpeg</a>\r\n" +
                "<a href='http://localhost:5000/image.png'>image.png</a>\r\n" +
                "<a href='http://localhost:5000/partial_content.txt'>partial_content.txt</a>\r\n" +
                "<a href='http://localhost:5000/patch-content.txt'>patch-content.txt</a>\r\n" +
                "<a href='http://localhost:5000/postData.txt'>postData.txt</a>\r\n" +
                "<a href='http://localhost:5000/text-file.txt'>text-file.txt</a>\r\n", outputResponse);
    }
}
