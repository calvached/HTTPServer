package main.java.server;

import main.java.server.router.Router;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerRunnable implements Runnable{
    private Socket socket;

    public ServerRunnable(Socket clientSocket) {
        socket = clientSocket;
    }

    public void run() {
        if (!socket.isClosed()) {
            try {
                router().directTrafficFor();
                socket.close();
            } catch (IOException e) {
            }
        }
    }

    private Router router() throws IOException {
        return new Router(in(), out());
    }

    private OutputStream out() throws IOException {
        return socket.getOutputStream();
    }

    private InputStream in() throws IOException {
        return socket.getInputStream();
    }
}
