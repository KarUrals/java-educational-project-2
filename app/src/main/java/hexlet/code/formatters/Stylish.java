package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String genResultString(List<Map<String, Object>> dataDifferences) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        for (Map<String, Object> record : dataDifferences) {
            result.append(genTwoSpaces());
            switch (record.get("state").toString()) {
                case "added" -> result.append("+ ")
                        .append(genKeyValueStr(record.get("key"), record.get("value")));
                case "deleted" -> result.append("- ")
                        .append(genKeyValueStr(record.get("key"), record.get("value")));
                case "unchanged" -> result.append(genTwoSpaces())
                        .append(genKeyValueStr(record.get("key"), record.get("value")));
                default -> result.append("- ")
                        .append(genKeyValueStr(record.get("key"), record.get("oldValue")))
                        .append(genTwoSpaces())
                        .append("+ ")
                        .append(genKeyValueStr(record.get("key"), record.get("newValue")));
            }
        }
        result.append("}");

        return result.toString();
    }

    private static String genKeyValueStr(Object key, Object value) {
        StringBuilder result = new StringBuilder();
        result.append(key)
                .append(": ")
                .append(value)
                .append("\n");
        return result.toString();
    }

    private static String genTwoSpaces() {
        String space = " ";
        return space.repeat(2);
    }
}
