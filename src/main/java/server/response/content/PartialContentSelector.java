package main.java.server.response.content;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartialContentSelector {
    private String range;

    public byte[] getPartialContent(String range, Path filePath) throws IOException {
        this.range = range;

        byte[] partialContent;

        if (range.length() > 2) {
            partialContent = getPartialContentWithRangeBoundariesPresent(filePath);
        } else {
            partialContent = getPartialContentWithMissingRangeBoundary(filePath);
        }

        return partialContent;
    }

    private byte[] getPartialContentWithRangeBoundariesPresent(Path filePath) throws IOException {
        String[] boundaries = range.split("-");

        return Arrays.copyOfRange(
                convertToBytes(filePath),
                Integer.parseInt(boundaries[0]),
                Integer.parseInt(boundaries[1]) + 1);
    }

    private byte[] convertToBytes(Path path) throws IOException {
        return Files.readAllBytes(path);
    }

    private byte[] getPartialContentWithMissingRangeBoundary(Path filePath) throws IOException {
        Pattern method = Pattern.compile("-\\d+");
        Matcher methodMatcher = method.matcher(range);
        byte[] partialContent;
        byte[] fileBytes = convertToBytes(filePath);

        if (methodMatcher.find()) {
            partialContent = getLastNBytes(fileBytes);
        } else {
            partialContent = getBytesFromStartingPoint(fileBytes);
        }

        return partialContent;
    }

    private byte[] getLastNBytes(byte[] fileBytes) {
        return Arrays.copyOfRange(
                fileBytes,
                findFirstBoundary(fileBytes),
                fileBytes.length);
    }

    private byte[] getBytesFromStartingPoint(byte[] fileBytes) {
        return Arrays.copyOfRange(
                fileBytes,
                getFirstBoundary(),
                fileBytes.length);
    }

    private int findFirstBoundary(byte[] fileBytes) {
        return fileBytes.length + Integer.parseInt(range);
    }

    private int getFirstBoundary() {
        return Integer.parseInt(range.split("-")[0]);
    }
}
