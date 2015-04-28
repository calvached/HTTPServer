package mocks;

import java.io.IOException;
import java.io.InputStream;

public class ErrorInputStream extends InputStream{

    @Override
    public int read() throws IOException {
        throw new IOException();
    }
}
