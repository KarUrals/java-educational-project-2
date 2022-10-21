package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Path absolutePath1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path absolutePath2 = Paths.get(filePath2).toAbsolutePath().normalize();

        String content1 = Files.readString(absolutePath1);
        String content2 = Files.readString(absolutePath2);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> map1 = objectMapper.readValue(content1, new TypeReference<>() { });
        Map<String, Object> map2 = objectMapper.readValue(content2, new TypeReference<>() { });

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
