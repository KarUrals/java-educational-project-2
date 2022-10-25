package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String filePath) throws IOException {

        String fileExtension = getFileExtention(filePath);

        ObjectMapper objectMapper = switch (fileExtension) {
            case ".json" -> new ObjectMapper();
            case ".yaml", ".yml" -> new ObjectMapper(new YAMLFactory());
            default -> throw new IOException("Unexpected format: " + fileExtension);
        };

        Path absolutePath = Paths.get(filePath).toAbsolutePath().normalize();
        String content = Files.readString(absolutePath);

        return objectMapper.readValue(content, new TypeReference<>() { });
    }

    public static String getFileExtention(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index == -1 ? null : filePath.substring(index);
    }

//    public static String checkFileExtention(String fileExtension) throws IOException {
//        if (!fileExtension.equals(".json") && !fileExtension.equals(".yaml") && !fileExtension.equals(".yml")) {
//            throw new IOException("Unexpected format: " + fileExtension);
//        }
//        return fileExtension;
//    }
}
