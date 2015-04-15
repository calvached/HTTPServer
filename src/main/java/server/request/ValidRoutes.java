package main.java.server.request;

import java.util.HashMap;
import java.util.Map;

public class ValidRoutes {
    public final Map<String, HashMap> routes = new HashMap<>();

    {
        routes.put("/",               indexPage());
        routes.put("/file1",          filePage());
        routes.put("/text-file.txt",  textFilePage());
        routes.put("/form",           formPage());
        routes.put("/method_options", methodOptionsPage());
        routes.put("/redirect",       redirectTo("/"));
        routes.put("/image.jpeg",     imagePage(".jpeg"));
        routes.put("/image.png",      imagePage(".png"));
        routes.put("/image.gif",      imagePage(".gif"));
    }

    private HashMap textFilePage() {
        String[] methods = {
                "GET"
        };

        HashMap<String, Object> data = new HashMap<>();
        data.put("allowedMethods", methods);
        data.put("content", "../cob_spec/public/text-file.txt");

        return data;
    }

    private HashMap imagePage(String extension) {
        String[] methods = {
                "GET"
        };

        HashMap<String, Object> data = new HashMap<>();
        data.put("allowedMethods", methods);
        data.put("content", "../cob_spec/public/image" + extension);

        return data;
    }

    private HashMap filePage() {
        String[] methods = {
                "GET"
        };

        HashMap<String, Object> data = new HashMap<>();
        data.put("allowedMethods", methods);
        data.put("content", "../cob_spec/public/file1");

        return data;
    }


    private HashMap indexPage() {
        String[] methods = {
                "GET"
        };

        HashMap<String, Object> data = new HashMap<>();
        data.put("allowedMethods", methods);
        data.put("content", "../cob_spec/public");

        return data;
    }

    private HashMap formPage() {
        String[] methods = {
                "GET",
                "POST",
                "PUT",
                "DELETE"
        };

        HashMap<String, Object> data = new HashMap<>();
        data.put("allowedMethods", methods);
        data.put("content", "../cob_spec/public/postData.txt");

        return data;
    }

    private HashMap methodOptionsPage() {
        String[] methods = {
                "GET",
                "HEAD",
                "POST",
                "OPTIONS",
                "PUT"
        };

        HashMap<String, Object> data = new HashMap<>();
        data.put("allowedMethods", methods);
        data.put("content", "../cob_spec/public/postData.txt");

        return data;
    }

    private HashMap redirectTo(String redirectPath) {
        HashMap<String, Object> options = new HashMap<>();
        options.put("redirectPath", redirectPath);

        return options;
    }
}
