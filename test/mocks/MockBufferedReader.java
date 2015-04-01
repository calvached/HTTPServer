package mocks;

import java.io.BufferedReader;
import java.io.Reader;

public class MockBufferedReader extends BufferedReader{
    private String[] requestLines;

    public MockBufferedReader(Reader in, String[]...a) {
        super(in);
        requestLines = a[0];
    }

    public boolean ready() {
        return stringsInRequest();
    }

    public String readLine() {
        String foundLine = findNextLine();

        return foundLine;
    }

    private boolean stringsInRequest() {
       Boolean status = false;

       for (String line : requestLines) {
          if (stringsExistInRequest(line)) {
              status = true;
          }
       }

       return status;
    }

    private boolean stringsExistInRequest(String line) {
        return notNull(line) && isAString(line);
    }

    private boolean notNull(String line) {
        return line != null;
    }

    private boolean isAString(String line) {
        return line.getClass().equals(String.class);
    }

    private String findNextLine() {
        String[] copiedRequestLines = requestLines;
        String foundLine = null;

        for(int i = 0; i < copiedRequestLines.length; i++) {
            if (requestLines[i] != null) {
                foundLine = requestLines[i];
                requestLines[i] = null;

                break;
            }
        }

        return foundLine;
    }
}