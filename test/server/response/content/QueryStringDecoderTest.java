package server.response.content;

import main.java.server.response.content.QueryStringDecoder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueryStringDecoderTest {
    @Test
    public void itDecodesALongParameterString() throws Exception {
        String encodedParams = "variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";

        QueryStringDecoder decoder = new QueryStringDecoder();
        String decodedParams = decoder.decode(encodedParams);

        String expectedParams = "variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?\r\n" +
                "variable_2 = stuff\r\n";

        assertEquals(expectedParams, decodedParams);
    }
}

