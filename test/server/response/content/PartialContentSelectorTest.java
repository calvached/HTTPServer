package server.response.content;

import main.java.server.response.content.PartialContentSelector;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

public class PartialContentSelectorTest {
    @Test
    public void itReturnsPartialContentForRangeWithStartAndEnd() throws Exception {
        Path filePath = Paths.get("test/public/partial_content.txt");
        byte[] expectedContent = Arrays.copyOfRange(
                Files.readAllBytes(filePath), 0, 5);

        PartialContentSelector selector = new PartialContentSelector();
        byte[] content = selector.getPartialContent("0-4", filePath);

        String textToString = new String(content);
        String expectedToString = new String(expectedContent);

        assertEquals(expectedToString, textToString);
    }

    @Test
    public void itReturnsPartialContentForRangeWithStartOnly() throws Exception {
        Path filePath = Paths.get("test/public/partial_content.txt");
        byte[] fileBytes = Files.readAllBytes(filePath);
        byte[] expectedContent = Arrays.copyOfRange(
                fileBytes, 4, fileBytes.length);

        PartialContentSelector selector = new PartialContentSelector();
        byte[] content = selector.getPartialContent("4-", filePath);

        String textToString = new String(content);
        String expectedToString = new String(expectedContent);

        assertEquals(expectedToString, textToString);
    }

    @Test
    public void itReturnsPartialContentForRangeWithEndOnly() throws Exception {
        Path filePath = Paths.get("test/public/partial_content.txt");
        byte[] fileBytes = Files.readAllBytes(filePath);
        byte[] expectedContent = Arrays.copyOfRange(
                fileBytes, fileBytes.length - 6, fileBytes.length);

        PartialContentSelector selector = new PartialContentSelector();
        byte[] content = selector.getPartialContent("-6", filePath);

        String textToString = new String(content);
        String expectedToString = new String(expectedContent);

        assertEquals(expectedToString, textToString);
    }
}