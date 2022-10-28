package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws IOException {

        Map<String, Object> map1 = Parser.parse(getFileExtension(filePath1), getFileContent(filePath1));
        Map<String, Object> map2 = Parser.parse(getFileExtension(filePath2), getFileContent(filePath2));

        return Formatter.genResult(format, genDiff(map1, map2));
    }

    public static String generate(String filePath1, String filePath2) throws IOException {

        return generate(filePath1, filePath2, "stylish");
    }

    private static List<Map<String, Object>> genDiff(Map<String, Object> map1, Map<String, Object> map2) {

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        List<Map<String, Object>> dataDifferences = new ArrayList<>();

        for (String key : allKeys) {
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
            dataDifferences.add(diffMap);
        }

        return dataDifferences;
    }

    private static String getFileContent(String filePath) throws IOException {
        if (getFileExtension(filePath) == null) {
            throw new IOException("Invalid parameter: file '" + filePath + "' without extension");
        }

        Path absolutePath = Paths.get(filePath).toAbsolutePath().normalize();
        File file = new File(absolutePath.toString());

        if (!file.exists()) {
            throw new IOException("Invalid parameter: file '" + filePath + "' does not exist");
        }

        if (file.length() == 0) {
            throw new IOException("Invalid parameter: file '" + filePath + "' is empty");
        }

        return Files.readString(absolutePath);
    }

    private static String getFileExtension(String filePath) {
        String absolutFilePath = Paths.get(filePath).toAbsolutePath().normalize().toString();
        int index = absolutFilePath.lastIndexOf('.');

        return index == -1 ? null : absolutFilePath.substring(index);
    }
}
