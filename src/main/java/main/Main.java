package main.java.main;

import main.java.server.HttpServer;
import main.java.server.router.Router;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        HttpServer server =
                new HttpServer(
                        new ServerSocket(5000),
                        executorService);

        server.start();
    }
}
