package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String genResult(String format, List<Map<String, Object>> dataDifferences) throws IOException {
        return switch (format) {
            case "stylish" -> Stylish.genResultString(dataDifferences);
            case "plain" -> Plain.genResultString(dataDifferences);
            case "json" -> Json.genResultString(dataDifferences);
            default -> throw new IOException("Unexpected format: " + format);
        };
    }
}
