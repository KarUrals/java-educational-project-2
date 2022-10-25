package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {

        Map<String, Object> map1 = Parser.parse(filePath1);
        Map<String, Object> map2 = Parser.parse(filePath2);

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        StringBuilder result = new StringBuilder();

        result.append("{\n");
        for (String key : allKeys) {
            result.append("  ");
            if (!map1.containsKey(key)) {
                result.append("+ " + key + ": " + map2.get(key) + "\n");
            } else if (!map2.containsKey(key)) {
                result.append("- " + key + ": " + map1.get(key) + "\n");
            } else if (map1.get(key).equals(map2.get(key))) {
                result.append("  " + key + ": " + map1.get(key) + "\n");
            } else {
                result.append("- " + key + ": " + map1.get(key) + "\n");
                result.append("  + " + key + ": " + map2.get(key) + "\n");
            }
        }
        result.append("}");
        return result.toString();
    }
}
