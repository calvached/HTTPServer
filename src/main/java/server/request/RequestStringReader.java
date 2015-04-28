package main.java.server.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RequestStringReader {
    private BufferedReader reader;

    public RequestStringReader(InputStream inputStream) {
        reader =
                new BufferedReader(
                        new InputStreamReader(
                                inputStream));
    }

    public String getConcatenatedRequest() {
        String request = concatenateRequest();

        return request;
    }

    private String concatenateRequest() {
        String request = "";

        try {
            String line = reader.readLine();

            if (reader.ready()) {
                request += line + "\n";

                while (reader.ready()) {
                    int content = reader.read();
                    request += ((char) content);
                }
            }
        } catch (IOException e) {
        }

        return request;
    }
}