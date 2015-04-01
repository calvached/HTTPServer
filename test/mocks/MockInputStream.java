package mocks;

import java.io.IOException;
import java.io.InputStream;

public class MockInputStream extends InputStream{
    @Override
    public int read() throws IOException {
        return 0;
    }
}
