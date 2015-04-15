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
                "file1\r\n" +
                "file2\r\n" +
                "image.gif\r\n" +
                "image.jpeg\r\n" +
                "image.png\r\n" +
                "partial_content.txt\r\n" +
                "patch-content.txt\r\n" +
                "postData.txt\r\n" +
                "text-file.txt\r\n", outputResponse);
    }
}
