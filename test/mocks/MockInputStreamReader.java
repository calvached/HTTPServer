package mocks;

import java.io.InputStream;
import java.io.InputStreamReader;

public class MockInputStreamReader extends InputStreamReader{
    public MockInputStreamReader(InputStream in) {
        super(in);
    }
}
