package hexlet.code;

import hexlet.code.enums.Formats;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static hexlet.code.TreeDifferenceFinder.STATE;
import static hexlet.code.TreeDifferenceFinder.UNCHANGED_STATE;

public class Formatter {
    public static final String NEXT_LINE_TRANSFER = "\n";
    public static final String ERROR_MESSAGE = "Unexpected format: %s";
    public static final String EMPTY_FILES_MESSAGE = "Empty files sent for comparison";
    public static final String IDENTICAL_FILES_MESSAGE = "The two files are semantically identical";
    private static final Stylish STYLISH = new Stylish();
    private static final Plain PLAIN = new Plain();
    private static final Json JSON = new Json();

    public static String generateResult(String format, List<Map<String, Object>> dataDifferences) throws IOException {
        if (dataDifferences.isEmpty()) {
            return EMPTY_FILES_MESSAGE;
        }

        if (isFilesAreSame(dataDifferences)) {
            return IDENTICAL_FILES_MESSAGE;
        }

        try {
            return switch (Formats.valueOf(format.toUpperCase())) {
                case STYLISH -> STYLISH.render(dataDifferences);
                case PLAIN -> PLAIN.render(dataDifferences);
                case JSON -> JSON.render(dataDifferences);
            };
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IOException(String.format(ERROR_MESSAGE, format));
        }
    }

    private static boolean isFilesAreSame(List<Map<String, Object>> dataDifferences) {
        int count = 0;
        for (Map<String, Object> map : dataDifferences) {
            if (map.get(STATE).equals(UNCHANGED_STATE)) {
                count++;
            }
        }
        return count == dataDifferences.size();
    }
}
