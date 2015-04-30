package main.java.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class HttpServer {
    private ServerSocket serverSocket;
    private ExecutorService executorService;

    public HttpServer(ServerSocket serverSocket, ExecutorService executorService) {
        this.serverSocket = serverSocket;
        this.executorService = executorService;
    }

    public void start() throws IOException {
        while (threadPoolIsRunning()) {
            executorService.execute(new ServerRunnable(clientSocket()));
        }
    }

    private boolean threadPoolIsRunning() {
       return !executorService.isShutdown();
    }

    private Socket clientSocket() throws IOException {
        return serverSocket.accept();
    }
}
