package mocks;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MockServerSocket extends ServerSocket {
    private boolean boundStatus = true;
    private boolean closedStatus = false;
    private boolean acceptStatus = false;
    private int localPort;

    public MockServerSocket(int port) throws IOException {
        localPort = port;
    }

    public boolean isBound() {
        return boundStatus;
    }

    public int getLocalPort() {
       return localPort;
    }

    public Socket accept() {
        acceptStatus = true;

        return null;
    }

    public boolean isAccepted() {
       return acceptStatus;
    }

    public void close() {
       toggleClose();
    }

    public boolean isClosed() {
        return closedStatus;
    }

    private void toggleClose() {
        if (closedStatus == true) {
            closedStatus = false;
        } else if (closedStatus == false) {
           closedStatus = true;
        }
    }
}
