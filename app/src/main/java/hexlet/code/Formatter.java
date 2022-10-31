package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static hexlet.code.Differences.STATE;
import static hexlet.code.Differences.UNCHANGED_STATE;

public class Formatter {
    public static final String STYLISH = "stylish";
    public static final String PLAIN = "plain";
    public static final String JSON = "json";
    public static final String NEXT_LINE_TRANSFER = "\n";
    public static final String ERROR_MESSAGE = "Unexpected format: ";
    public static final String EMPTY_FILES_MESSAGE = "Empty files sent for comparison";
    public static final String IDENTICAL_FILES_MESSAGE = "The two files are semantically identical";

    public static String genResult(String format, List<Map<String, Object>> dataDifferences) throws IOException {
        if (dataDifferences.isEmpty()) {
            return EMPTY_FILES_MESSAGE;
        }

        if (isFilesAreSame(dataDifferences)) {
            return IDENTICAL_FILES_MESSAGE;
        }

        return switch (format) {
            case STYLISH -> Stylish.genResultString(dataDifferences);
            case PLAIN -> Plain.genResultString(dataDifferences);
            case JSON -> Json.genResultString(dataDifferences);
            default -> throw new IOException(ERROR_MESSAGE + format);
        };
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
