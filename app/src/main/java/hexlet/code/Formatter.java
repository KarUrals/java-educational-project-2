package hexlet.code;

import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String genResult(List<Map<String, Object>> dataDifferences) {
        return Stylish.genResultString(dataDifferences);
    }
}
