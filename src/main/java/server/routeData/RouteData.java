package main.java.server.routeData;

public class RouteData {
    private boolean notFound = false;
    private boolean methodNotAllowed = false;
    private boolean isDirectory = false;
    private boolean isFile = false;
    private boolean isRedirect = false;
    private boolean isOptions = false;
    private boolean isPatch = false;
    private boolean isPartialContent = false;
    private boolean hasQueryString = false;
    private boolean requireAuthentication = false;
    private boolean authorization = false;
    private String redirectPath = "";
    private String contentPath = "";
    private String queryString = "";
    private String allowedMethods = "";

    public boolean notFound() {
        return notFound;
    }

    public void setNotFound(boolean flag) {
        notFound = flag;
    }

    public boolean methodNotAllowed() {
        return methodNotAllowed;
    }

    public void setMethodNotAllowed(boolean flag) {
        methodNotAllowed = flag;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setIsDirectory(boolean flag) {
        isDirectory = flag;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setIsFile(boolean flag) {
        isFile = flag;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public void setIsRedirect(boolean flag) {
        isRedirect = flag;
    }

    public boolean isOptions() {
        return isOptions;
    }

    public void setIsOptions(boolean flag) {
        isOptions = flag;
    }

    public boolean isPatch() {
        return isPatch;
    }

    public void setIsPatch(boolean flag) {
        isPatch = flag;
    }

    public boolean isPartialContent() {
        return isPartialContent;
    }

    public void setIsPartialContent(boolean flag) {
        isPartialContent = flag;
    }

    public boolean hasQueryString() {
        return hasQueryString;
    }

    public void setHasQueryString(boolean flag) {
        hasQueryString = flag;
    }

    public boolean requireAuthentication() {
        return requireAuthentication;
    }

    public void setRequireAuthentication(boolean flag) {
        requireAuthentication = flag;
    }

    public boolean authorization() {
        return authorization;
    }

    public void setAuthorization(boolean flag) {
        authorization = flag;
    }

    public String queryString() {
        return queryString;
    }

    public void setQueryString(String string) {
        queryString = string;
    }

    public String redirectPath() {
        return redirectPath;
    }

    public void setRedirectPath(String path) {
        redirectPath = path;
    }

    public String contentPath() {
        return contentPath;
    }

    public void setContentPath(String path) {
        contentPath = path;
    }

    public String allowedMethods() {
        return allowedMethods;
    }

    public void setAllowedMethods(String methods) {
        allowedMethods = methods;
    }
}
