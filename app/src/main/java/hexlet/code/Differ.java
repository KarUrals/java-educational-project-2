package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {

        Map<String, Object> map1 = Parser.parse(filePath1);
        Map<String, Object> map2 = Parser.parse(filePath2);


        return Formatter.genResult(genDiff(map1, map2));
    }

    public static List<Map<String, Object>> genDiff(Map<String, Object> map1, Map<String, Object> map2) {

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        List<Map<String, Object>> dataDifferences = new ArrayList<>();

        for (String key : allKeys) {
            Map<String, Object> diffMap = new HashMap<>();
            if (!map1.containsKey(key)) {
                diffMap.put("state", "added");
                diffMap.put("key", key);
                diffMap.put("value", map2.get(key));
            } else if (!map2.containsKey(key)) {
                diffMap.put("state", "deleted");
                diffMap.put("key", key);
                diffMap.put("value", map1.get(key));
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                diffMap.put("state", "unchanged");
                diffMap.put("key", key);
                diffMap.put("value", map1.get(key));
            } else {
                diffMap.put("state", "changed");
                diffMap.put("key", key);
                diffMap.put("value", map1.get(key));
                diffMap.put("newValue", map2.get(key));
            }
            dataDifferences.add(diffMap);
        }

        return dataDifferences;
    }
}
