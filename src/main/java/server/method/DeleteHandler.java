package main.java.server.method;

import main.java.server.request.Request;
import main.java.server.response.ResponseBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DeleteHandler {
    private ResponseBuilder builder;
    
    public DeleteHandler(ResponseBuilder responseBuilder) {
        builder = responseBuilder;
    }

    public void handle(Request request) throws IOException {
        if (request.url().equals("/form")) {
            deletePostData();

            builder.createSuccessfulResponse();
        } else {

            builder.createFourOhFour();
        }
    }

    private void deletePostData() throws IOException {
        File file = new File("public/postData.txt");

        FileWriter writer = new FileWriter(file, false);
        writer.write(" ");
        writer.close();
    }
}
