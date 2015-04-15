package server.request;

import main.java.server.request.RequestResolver;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class RequestResolverTest {

    @Test
    public void issuesANewPathToARedirectPath() throws Exception {
        HashMap<String, String> unresolvedRequest = new HashMap<>();
        unresolvedRequest.put("method", "GET");
        unresolvedRequest.put("path", "/redirect");
        unresolvedRequest.put("params", "");

        RequestResolver resolver = new RequestResolver();
        HashMap resolvedRequest = resolver.resolve(unresolvedRequest);

        assertEquals("/", resolvedRequest.get("path"));
    }

    @Test
    public void issuesAFlagIfTheRequestIsToBeRedirected() throws Exception {
        HashMap<String, String> unresolvedRequest = new HashMap<>();
        unresolvedRequest.put("method", "GET");
        unresolvedRequest.put("path", "/redirect");
        unresolvedRequest.put("params", "");

        RequestResolver resolver = new RequestResolver();
        HashMap resolvedRequest = resolver.resolve(unresolvedRequest);

        assertEquals(true, resolvedRequest.containsKey("redirect"));
    }

    @Test
    public void issuesAFlagIfTheRequestHasAMethodNotAllowed() throws Exception {
        HashMap<String, String> unresolvedRequest = new HashMap<>();
        unresolvedRequest.put("method", "POST");
        unresolvedRequest.put("path", "/");
        unresolvedRequest.put("params", "");

        RequestResolver resolver = new RequestResolver();
        HashMap resolvedRequest = resolver.resolve(unresolvedRequest);

        assertEquals(true, resolvedRequest.containsKey("methodNotAllowed"));
    }

    @Test
    public void issuesAFlagIfTheRequestHasARouteThatIsNotFound() throws Exception {
        HashMap<String, String> unresolvedRequest = new HashMap<>();
        unresolvedRequest.put("method", "GET");
        unresolvedRequest.put("path", "/noRoute");
        unresolvedRequest.put("params", "");

        RequestResolver resolver = new RequestResolver();
        HashMap resolvedRequest = resolver.resolve(unresolvedRequest);

        assertEquals(true, resolvedRequest.containsKey("notFound"));
    }

    @Test
    public void issuesAFlagWithTheContentTypeOfDirectoryIfItExists() throws Exception {
        HashMap<String, String> unresolvedRequest = new HashMap<>();
        unresolvedRequest.put("method", "GET");
        unresolvedRequest.put("path", "/");
        unresolvedRequest.put("params", "");

        RequestResolver resolver = new RequestResolver();
        HashMap resolvedRequest = resolver.resolve(unresolvedRequest);

        assertEquals(true, resolvedRequest.containsKey("isDirectory"));
    }

    @Test
    public void issuesAFlagWithTheContentTypeOfFileIfItExists() throws Exception {
        HashMap<String, String> unresolvedRequest = new HashMap<>();
        unresolvedRequest.put("method", "GET");
        unresolvedRequest.put("path", "/file1");
        unresolvedRequest.put("params", "");

        RequestResolver resolver = new RequestResolver();
        HashMap resolvedRequest = resolver.resolve(unresolvedRequest);

        assertEquals(true, resolvedRequest.containsKey("isFile"));
    }
}
