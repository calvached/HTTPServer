package main.java.server.routeData;

import java.util.HashMap;
import java.util.Map;

public class ValidRoutes {
    public final Map<String, HashMap> routes = new HashMap<>();

    {
        routes.put("/",                    indexPage());
        routes.put("/logs",                authenticatePage());
        routes.put("/form",                formPage());
        routes.put("/parameters",          decodeQueryString());
        routes.put("/patch-content.txt",   patchEtagPage("patch-content.txt"));
        routes.put("/method_options",      methodOptionsPage());
        routes.put("/redirect",            redirectTo("/"));
        routes.put("/postData.txt",        loadFile("postData.txt"));
        routes.put("/partial_content.txt", loadFile("partial_content.txt"));
        routes.put("/image.jpeg",          loadFile("image.jpeg"));
        routes.put("/image.png",           loadFile("image.png"));
        routes.put("/image.gif",           loadFile("image.gif"));
        routes.put("/file1",               loadFile("file1"));
        routes.put("/file2",               loadFile("file1"));
        routes.put("/text-file.txt",       loadFile("text-file.txt"));
    }

    private HashMap authenticatePage() {
        String[] methods = {
                "GET"
        };

        HashMap<String, Object> data = new HashMap<>();
        data.put("allowedMethods", methods);
        data.put("requireAuthentication", true);

        return data;
    }

    private HashMap decodeQueryString() {
        String[] methods = {
                "GET"
        };

        HashMap<String, Object> data = new HashMap<>();
        data.put("allowedMethods", methods);

        return data;
    }

    private HashMap patchEtagPage(String file) {
        String[] methods = {
                "GET",
                "PATCH"
        };

        HashMap<String, Object> data = new HashMap<>();
        data.put("allowedMethods", methods);
        data.put("content", "../cob_spec/public/" + file);

        return data;
    }

    private HashMap loadFile(String file) {
        String[] methods = {
                "GET"
        };

        HashMap<String, Object> data = new HashMap<>();
        data.put("allowedMethods", methods);
        data.put("content", "../cob_spec/public/" +file);

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
