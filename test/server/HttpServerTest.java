package server;

import main.java.server.HttpServer;
import mocks.MockExecutorService;
import mocks.MockServerSocket;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HttpServerTest {
    @Test
    public void itExecutesARunnable() throws Exception {
        MockServerSocket serverSocket = new MockServerSocket(3000);
        MockExecutorService executorService = new MockExecutorService();

        HttpServer server = new HttpServer(serverSocket, executorService);
        server.start();

        assertEquals(3, executorService.numOfExecutions);
    }
}
