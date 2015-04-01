package main.java.main;

import main.java.server.HttpServer;
import main.java.server.router.Router;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException {
        //Integer port = toInt(args[0]);

        HttpServer server =
                new HttpServer(
                        new ServerSocket(5000), new Router());

        server.start();
    }

    private static Integer toInt(String numString) {
        int num = Integer.parseInt(numString);

        return num;
    }
}
