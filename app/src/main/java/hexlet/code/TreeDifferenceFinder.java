package hexlet.code;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class TreeDifferenceFinder {
    public static final String KEY = "key";
    public static final String STATE = "state";
    public static final String VALUE = "value";
    public static final String OLD_VALUE = "oldValue";
    public static final String NEW_VALUE = "newValue";
    public static final String ADDED_STATE = "added";
    public static final String DELETED_STATE = "deleted";
    public static final String UNCHANGED_STATE = "unchanged";
    public static final String CHANGED_STATE = "changed";

    public static List<Map<String, Object>> findDifferenceBetweenTwoMaps(
            Map<String, Object> map1,
            Map<String, Object> map2) {

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        List<Map<String, Object>> listDiff = new ArrayList<>();

        for (String key : allKeys) {
            listDiff.add(analyzeKeyIsInBothMaps(key, map1, map2));
        }

        return listDiff;
    }


    private static Map<String, Object> analyzeKeyIsInBothMaps(
            String key,
            Map<String, Object> map1,
            Map<String, Object> map2) {

        Map<String, Object> diffMap = new LinkedHashMap<>();

        diffMap.put(KEY, key);
        if (!map1.containsKey(key)) {
            diffMap.put(STATE, ADDED_STATE);
            diffMap.put(VALUE, map2.get(key));
        } else if (!map2.containsKey(key)) {
            diffMap.put(STATE, DELETED_STATE);
            diffMap.put(VALUE, map1.get(key));
        } else if (Objects.equals(map1.get(key), map2.get(key))) {
            diffMap.put(STATE, UNCHANGED_STATE);
            diffMap.put(VALUE, map1.get(key));
        } else {
            diffMap.put(STATE, CHANGED_STATE);
            diffMap.put(OLD_VALUE, map1.get(key));
            diffMap.put(NEW_VALUE, map2.get(key));
        }

        return diffMap;
    }
}
