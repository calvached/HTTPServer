package main.java.server.response;

import java.io.*;
import java.util.HashMap;

public class ParamProcessor {
    private final HashMap<String, String> request;

    public ParamProcessor(HashMap<String, String> clientRequest) {
        request = clientRequest;
    }

    public void process() {
        if (!request.containsKey("notFound") && !request.containsKey("methodNotAllowed")) {
            if (methodIs("POST")) {
                add();
            }
            else if (methodIs("PUT")) {
                update();
            }
            else if (methodIs("DELETE")) {
                delete();
            }
        }
    }

    private void delete() {
        File file = new File(request.get("content"));

        FileWriter writer = null;
        try {
            writer = new FileWriter(file, false);
            writer.write(" ");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void update() {
        File file = new File(request.get("content"));

        FileWriter writer = null;

        try {
            writer = new FileWriter(file, false);
            writer.write(request.get("params"));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void add() {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(request.get("content"), "UTF-8");
            writer.println(request.get("params"));
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private boolean methodIs(String method) {
        return request.get("method").equals(method);
    }
}
