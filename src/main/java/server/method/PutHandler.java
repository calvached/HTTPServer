package main.java.server.method;

import main.java.server.request.Request;
import main.java.server.response.ResponseBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PutHandler {
    private ResponseBuilder builder;

    public PutHandler(ResponseBuilder responseBuilder) {
        builder = responseBuilder;
    }

    public void handle(Request request) throws IOException {
        if (request.url().equals("/form")) {
            updatePostData(request.data());

            builder.createSuccessfulResponse();
        } else {

            builder.createFourOhFour();
        }
    }

    private void updatePostData(String data) throws IOException {
        File file = new File("public/postData.txt");

        FileWriter writer = new FileWriter(file, false);
        writer.write(data);
        writer.close();

    }
}
