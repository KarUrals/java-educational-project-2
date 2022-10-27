package hexlet.code.formatters;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String genResultString(List<Map<String, Object>> dataDifferences) {
        StringBuilder result = new StringBuilder();
        for (Map<String, Object> record : dataDifferences) {
            switch (record.get("state").toString()) {
                case "added" -> result.append("Property '")
                        .append(record.get("key"))
                        .append("' was added with value: ")
                        .append(prepareObject(record.get("value")))
                        .append("\n");
                case "deleted" -> result.append("Property '")
                        .append(record.get("key"))
                        .append("' was removed")
                        .append("\n");
                case "changed" -> result.append("Property '")
                        .append(record.get("key"))
                        .append("' was updated. From ")
                        .append(prepareObject(record.get("value")))
                        .append(" to ")
                        .append(prepareObject(record.get("newValue")))
                        .append("\n");
                default -> result.append("");
            }
        }

        return result.toString().trim();
    }

    private static String prepareObject(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Collection || value instanceof Map) {
            return "[complex value]";
        }

        if (value instanceof String) {
            StringBuilder preparedObject = new StringBuilder();
            preparedObject.append("'")
                    .append(value)
                    .append("'");
            return preparedObject.toString();
        }

        return value.toString();
    }
}
