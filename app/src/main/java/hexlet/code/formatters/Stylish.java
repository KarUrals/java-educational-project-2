package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

import static hexlet.code.Differences.ADDED_STATE;
import static hexlet.code.Differences.DELETED_STATE;
import static hexlet.code.Differences.KEY;
import static hexlet.code.Differences.NEW_VALUE;
import static hexlet.code.Differences.OLD_VALUE;
import static hexlet.code.Differences.STATE;
import static hexlet.code.Differences.UNCHANGED_STATE;
import static hexlet.code.Differences.VALUE;
import static hexlet.code.Formatter.NEXT_LINE_TRANSFER;

public class Stylish {
    private static final String SPACE = " ";
    private static final String START_OF_FILE = "{";
    private static final String END_OF_FILE = "}";
    private static final String PLUS_MARKER = "+ ";
    private static final String MINUS_MARKER = "- ";
    private static final String KEY_VALUE_SEP = ": ";
    public static String genResultString(List<Map<String, Object>> dataDifferences) {
        StringBuilder result = new StringBuilder();
        result.append(START_OF_FILE).append(NEXT_LINE_TRANSFER);
        for (Map<String, Object> record : dataDifferences) {
            result.append(genTwoSpaces());
            switch (record.get(STATE).toString()) {
                case ADDED_STATE -> result.append(PLUS_MARKER)
                        .append(genKeyValueStr(record.get(KEY), record.get(VALUE)));
                case DELETED_STATE -> result.append(MINUS_MARKER)
                        .append(genKeyValueStr(record.get(KEY), record.get(VALUE)));
                case UNCHANGED_STATE -> result.append(genTwoSpaces())
                        .append(genKeyValueStr(record.get(KEY), record.get(VALUE)));
                default -> result.append(MINUS_MARKER)
                        .append(genKeyValueStr(record.get(KEY), record.get(OLD_VALUE)))
                        .append(genTwoSpaces())
                        .append(PLUS_MARKER)
                        .append(genKeyValueStr(record.get(KEY), record.get(NEW_VALUE)));
            }
        }
        result.append(END_OF_FILE);

        return result.toString();
    }

    private static String genKeyValueStr(Object key, Object value) {
        StringBuilder result = new StringBuilder();
        result.append(key)
                .append(KEY_VALUE_SEP)
                .append(value)
                .append(NEXT_LINE_TRANSFER);
        return result.toString();
    }

    private static String genTwoSpaces() {
        return SPACE.repeat(2);
    }
}
