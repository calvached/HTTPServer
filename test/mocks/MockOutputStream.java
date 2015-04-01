package mocks;

import java.io.IOException;
import java.io.OutputStream;

public class MockOutputStream extends OutputStream{
    public boolean successfulWrite = false;

    @Override
    public void write(int b) throws IOException {
        successfulWrite = true;
    }
}
