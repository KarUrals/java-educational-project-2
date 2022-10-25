package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String filePath) throws Exception {

        Path absolutePath = Paths.get(filePath).toAbsolutePath().normalize();
        String content = Files.readString(absolutePath);

        ObjectMapper objectMapper;
        String fileExtension = getFileExtention(filePath);

        if (fileExtension.equals(".json")) {
            objectMapper = new ObjectMapper();
        } else if (fileExtension.equals(".yaml") || fileExtension.equals(".yml")) {
            objectMapper = new ObjectMapper(new YAMLFactory());
        } else {
            throw new Exception("Unexpected format: " + fileExtension);
        }
//        switch (fileExtension) {
//            case ".json" -> objectMapper = new ObjectMapper();
//            case ".yaml", ".yml" -> objectMapper = new ObjectMapper(new YAMLFactory());
//            default -> throw new Exception("Unexpected format: " + fileExtension);
//        }

        return objectMapper.readValue(content, new TypeReference<>() { });
    }

    private static String getFileExtention(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index == -1 ? null : filePath.substring(index);
    }
}
