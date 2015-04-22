package main.java.main;

import main.java.server.HttpServer;
import main.java.server.router.Router;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpServer server =
                new HttpServer(new ServerSocket(5000));

        server.start();
    }
}
