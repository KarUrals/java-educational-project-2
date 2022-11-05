package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

import static hexlet.code.TreeDifferenceFinder.ADDED_STATE;
import static hexlet.code.TreeDifferenceFinder.DELETED_STATE;
import static hexlet.code.TreeDifferenceFinder.KEY;
import static hexlet.code.TreeDifferenceFinder.NEW_VALUE;
import static hexlet.code.TreeDifferenceFinder.OLD_VALUE;
import static hexlet.code.TreeDifferenceFinder.STATE;
import static hexlet.code.TreeDifferenceFinder.UNCHANGED_STATE;
import static hexlet.code.TreeDifferenceFinder.VALUE;
import static hexlet.code.Formatter.NEXT_LINE_TRANSFER;

public class Stylish {
    private static final String DOUBLE_SPACE = "  ";
    private static final String START_OF_FILE = "{";
    private static final String END_OF_FILE = "}";
    private static final String PLUS_MARKER = "+ ";
    private static final String MINUS_MARKER = "- ";
    private static final String KEY_VALUE_SEP = ": ";
    public String render(List<Map<String, Object>> dataDifferences) {
        StringBuilder result = new StringBuilder();
        result.append(START_OF_FILE).append(NEXT_LINE_TRANSFER);
        for (Map<String, Object> record : dataDifferences) {
            result.append(DOUBLE_SPACE);
            switch (record.get(STATE).toString()) {
                case ADDED_STATE -> result.append(PLUS_MARKER)
                        .append(generateKeyValueString(record.get(KEY), record.get(VALUE)));
                case DELETED_STATE -> result.append(MINUS_MARKER)
                        .append(generateKeyValueString(record.get(KEY), record.get(VALUE)));
                case UNCHANGED_STATE -> result.append(DOUBLE_SPACE)
                        .append(generateKeyValueString(record.get(KEY), record.get(VALUE)));
                default -> result.append(MINUS_MARKER)
                        .append(generateKeyValueString(record.get(KEY), record.get(OLD_VALUE)))
                        .append(DOUBLE_SPACE)
                        .append(PLUS_MARKER)
                        .append(generateKeyValueString(record.get(KEY), record.get(NEW_VALUE)));
            }
        }
        result.append(END_OF_FILE);

        return result.toString();
    }

    private static String generateKeyValueString(Object key, Object value) {
        StringBuilder result = new StringBuilder();
        result.append(key)
                .append(KEY_VALUE_SEP)
                .append(value)
                .append(NEXT_LINE_TRANSFER);
        return result.toString();
    }
}
