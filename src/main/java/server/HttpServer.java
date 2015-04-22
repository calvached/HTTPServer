package main.java.server;

import main.java.server.router.Router;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private ServerSocket serverSocket;

    public HttpServer(ServerSocket boundSocket) {
        serverSocket = boundSocket;
    }

    public void start() throws IOException {
        while (true){
            Socket clientSocket = getIncomingClientSocket();

            if (!clientSocket.isClosed()) {
                handle(clientSocket);
                clientSocket.close();
            } else {
                break;
            }
        }
    }

    private Socket getIncomingClientSocket() {
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

            Router router = new Router(in, out);
            router.directTrafficFor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
