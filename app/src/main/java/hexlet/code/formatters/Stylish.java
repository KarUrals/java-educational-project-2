package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String stylish(List<Map<String, Object>> dataDifferences) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        for (Map<String, Object> record : dataDifferences) {
            result.append(genTwoSpaces());
            switch (record.get("state").toString()) {
                case "added" -> result.append("+ ")
                        .append(record.get("key"))
                        .append(": ")
                        .append(record.get("value"))
                        .append("\n");
                case "deleted" -> result.append("- ")
                        .append(record.get("key"))
                        .append(": ")
                        .append(record.get("value"))
                        .append("\n");
                case "unchanged" -> result.append(genTwoSpaces())
                        .append(record.get("key"))
                        .append(": ")
                        .append(record.get("value"))
                        .append("\n");
                default -> result.append("- ")
                        .append(record.get("key"))
                        .append(": ")
                        .append(record.get("value"))
                        .append("\n")
                        .append(genTwoSpaces())
                        .append("+ ")
                        .append(record.get("key"))
                        .append(": ")
                        .append(record.get("newValue"))
                        .append("\n");
            }
        }
        result.append("}");

        return result.toString();
    }

    private static String genTwoSpaces() {
        String space = " ";
        return space.repeat(2);
    }
}
