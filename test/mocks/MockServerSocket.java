package mocks;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MockServerSocket extends ServerSocket {
    private boolean boundStatus = true;
    private boolean closedStatus = false;
    private int localPort;
    private Socket socket;

    public MockServerSocket(int port) throws IOException {
        socket = new MockSocket();
        localPort = port;
    }

    public boolean isBound() {
        return boundStatus;
    }

    public int getLocalPort() {
       return localPort;
    }

    public Socket accept() {
        return socket;
    }

    public void close() {
       toggleClose();
    }

    public boolean isClosed() {
        return closedStatus;
    }

    public String getOutputResponse() throws IOException {
        return socket.getOutputStream().toString();
    }

    private void toggleClose() {
        if (closedStatus == true) {
            closedStatus = false;
        } else if (closedStatus == false) {
           closedStatus = true;
        }
    }
}
