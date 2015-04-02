package main.java.server.method;

import main.java.server.request.Request;
import main.java.server.response.ResponseBuilder;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class PostHandler {
    private ResponseBuilder builder;

    public PostHandler(ResponseBuilder responseBuilder) {
        builder = responseBuilder;
    }

    public void handle(Request request) {
        if (request.url().equals("/form")) {
            savePostData(request.data());

            builder.createSuccessfulResponse();
        } else {

            builder.createFourOhFour();
        }
    }

    private void savePostData(String data) {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter("public/postData.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        writer.println(data);
        writer.close();
    }
}
