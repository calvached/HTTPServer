package main.java.server.request;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestReader {
    private BufferedReader reader;

    public RequestReader(BufferedReader bufferedReader) {
        reader = bufferedReader;
    }

    public String getConcatenatedRequest() {
        String request = concatenateRequest();

        return request;
    }

    private String concatenateRequest() {
        String request = "";

        try {
            String line = reader.readLine();
            request += line + "\n";

            if (reader.ready()) {
                while (reader.ready()) {
                    int content = reader.read();
                    request += ((char) content);
                }
            } else {
                System.out.println("READER IS NOT READY!");
                System.out.println("Client closed their socket!");
                System.out.println("Perhaps establish a new connection?");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return request;
    }
}
