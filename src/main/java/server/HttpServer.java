package main.java.server;

import main.java.server.router.Router;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private ServerSocket serverSocket;
    private Router router;

    public HttpServer(ServerSocket boundSocket, Router methodRouter) {
        serverSocket = boundSocket;
        router = methodRouter;
    }

    public void start() throws IOException {
        while (true){
            Socket clientSocket = getIncomingClientSocket();
            handle(clientSocket);
            clientSocket.close();
        }
    }

    private Socket getIncomingClientSocket() {
        System.out.println("LISTENING...");
        Socket incoming = null;

        try {
            incoming = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return incoming;
    }

    private void handle(Socket clientSocket) {
        try {
            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();

            router.handleTrafficFor(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
