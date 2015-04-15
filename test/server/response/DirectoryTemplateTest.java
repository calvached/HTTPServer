package server.response;

import main.java.server.response.DirectoryTemplate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DirectoryTemplateTest {
    @Test
    public void itReturnsAFormattedTemplate() throws Exception {
        String[] strings = {
                "Page 1",
                "Page 2",
                "Page 3",
                "Page 4"
        };

        DirectoryTemplate templateMaker = new DirectoryTemplate(strings);
        String template = templateMaker.getTemplate();

        assertEquals("<a href='http://localhost:5000/Page 1'>Page 1</a>\r\n" +
                "<a href='http://localhost:5000/Page 2'>Page 2</a>\r\n" +
                "<a href='http://localhost:5000/Page 3'>Page 3</a>\r\n" +
                "<a href='http://localhost:5000/Page 4'>Page 4</a>\r\n", template);
    }
}
