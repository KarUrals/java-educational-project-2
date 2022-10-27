package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String fileExtension, String fileContent) throws IOException {

        ObjectMapper objectMapper = switch (fileExtension) {
            case ".json" -> new ObjectMapper();
            case ".yaml", ".yml" -> new ObjectMapper(new YAMLFactory());
            default -> throw new IOException("Unexpected file extension: " + fileExtension);
        };

        return objectMapper.readValue(fileContent, new TypeReference<>() { });
    }
}
