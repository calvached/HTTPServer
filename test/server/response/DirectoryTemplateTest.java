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

        assertEquals("Page 1\r\nPage 2\r\nPage 3\r\nPage 4\r\n", template);
    }
}
