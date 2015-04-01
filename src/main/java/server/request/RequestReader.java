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
            while (reader.ready()) {
                request += reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return request;
    }
}
