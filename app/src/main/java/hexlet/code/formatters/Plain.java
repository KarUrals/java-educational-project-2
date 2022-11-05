package hexlet.code.formatters;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static hexlet.code.TreeDifferenceFinder.ADDED_STATE;
import static hexlet.code.TreeDifferenceFinder.CHANGED_STATE;
import static hexlet.code.TreeDifferenceFinder.DELETED_STATE;
import static hexlet.code.TreeDifferenceFinder.KEY;
import static hexlet.code.TreeDifferenceFinder.NEW_VALUE;
import static hexlet.code.TreeDifferenceFinder.OLD_VALUE;
import static hexlet.code.TreeDifferenceFinder.STATE;
import static hexlet.code.TreeDifferenceFinder.VALUE;
import static hexlet.code.Formatter.NEXT_LINE_TRANSFER;

public final class Plain {
    private static final String COMPLEX_VALUE = "[complex value]";
    private static final String STRING_OBJECT_MARKER = "'";
    private static final String START_OF_LINE = "Property '";
    private static final String ADDED_LINE_END = "' was added with value: ";
    private static final String DELETED_LINE_END = "' was removed";
    private static final String CHANGED_LINE_MIDL = "' was updated. From ";
    private static final String CHANGED_LINE_END = " to ";

    public String render(List<Map<String, Object>> dataDifferences) {
        StringBuilder result = new StringBuilder();
        for (Map<String, Object> record : dataDifferences) {
            switch (record.get(STATE).toString()) {
                case ADDED_STATE -> result.append(START_OF_LINE)
                        .append(record.get(KEY))
                        .append(ADDED_LINE_END)
                        .append(prepareObject(record.get(VALUE)))
                        .append(NEXT_LINE_TRANSFER);
                case DELETED_STATE -> result.append(START_OF_LINE)
                        .append(record.get(KEY))
                        .append(DELETED_LINE_END)
                        .append(NEXT_LINE_TRANSFER);
                case CHANGED_STATE -> result.append(START_OF_LINE)
                        .append(record.get(KEY))
                        .append(CHANGED_LINE_MIDL)
                        .append(prepareObject(record.get(OLD_VALUE)))
                        .append(CHANGED_LINE_END)
                        .append(prepareObject(record.get(NEW_VALUE)))
                        .append(NEXT_LINE_TRANSFER);
                default -> { }
            }
        }

        return result.toString().trim();
    }

    private static String prepareObject(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Collection || value instanceof Map) {
            return COMPLEX_VALUE;
        }

        if (value instanceof String) {
            StringBuilder preparedObject = new StringBuilder();
            preparedObject.append(STRING_OBJECT_MARKER)
                    .append(value)
                    .append(STRING_OBJECT_MARKER);
            return preparedObject.toString();
        }

        return value.toString();
    }
}
