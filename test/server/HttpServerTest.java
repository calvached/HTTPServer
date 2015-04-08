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

        assertEquals("HTTP/1.1 200 OK", outputResponse);
    }
}
