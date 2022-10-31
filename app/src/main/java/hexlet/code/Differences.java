package hexlet.code;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Differences {

    public static List<Map<String, Object>> genListOfDiffMaps(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        List<Map<String, Object>> listDiff = new ArrayList<>();

        for (String key : allKeys) {
            listDiff.add(genMapOfDiff(key, map1, map2));
        }

        return listDiff;
    }

    private static Map<String, Object> genMapOfDiff(String key, Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, Object> diffMap = new LinkedHashMap<>();

        diffMap.put("key", key);
        if (!map1.containsKey(key)) {
            diffMap.put("state", "added");
            diffMap.put("value", map2.get(key));
        } else if (!map2.containsKey(key)) {
            diffMap.put("state", "deleted");
            diffMap.put("value", map1.get(key));
        } else if (Objects.equals(map1.get(key), map2.get(key))) {
            diffMap.put("state", "unchanged");
            diffMap.put("value", map1.get(key));
        } else {
            diffMap.put("state", "changed");
            diffMap.put("oldValue", map1.get(key));
            diffMap.put("newValue", map2.get(key));
        }

        return diffMap;
    }
}
