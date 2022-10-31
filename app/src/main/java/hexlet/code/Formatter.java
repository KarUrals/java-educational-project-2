package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static final String STYLISH = "stylish";
    public static final String PLAIN = "plain";
    public static final String JSON = "json";
    public static final String ERROR_MESSAGE = "Unexpected format: ";

    public static String genResult(String format, List<Map<String, Object>> dataDifferences) throws IOException {
        return switch (format) {
            case STYLISH -> Stylish.genResultString(dataDifferences);
            case PLAIN -> Plain.genResultString(dataDifferences);
            case JSON -> Json.genResultString(dataDifferences);
            default -> throw new IOException(ERROR_MESSAGE + format);
        };
    }
}
