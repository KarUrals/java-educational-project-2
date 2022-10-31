package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static final String JSON_EXT = "json";
    public static final String YAML_EXT = "yaml";
    public static final String YML_EXT = "yml";
    public static final String ERROR_MESSAGE = "Unexpected file extension: ";

    public static Map<String, Object> parse(String fileExtension, String fileContent) throws IOException {

        return switch (fileExtension) {
            case JSON_EXT -> parseJson(fileContent);
            case YML_EXT, YAML_EXT -> parseYaml(fileContent);
            default -> throw new IOException(ERROR_MESSAGE + fileExtension);
        };
    }

    private static Map<String, Object> parseJson(String fileContent) {
        try {
            return new ObjectMapper().readValue(fileContent, new TypeReference<>() { });
        } catch (JsonProcessingException e) {
            return new HashMap<>();
        }
    }

    private static Map<String, Object> parseYaml(String fileContent) {
        try {
            return new ObjectMapper(new YAMLFactory()).readValue(fileContent, new TypeReference<>() { });
        } catch (JsonProcessingException e) {
            return new HashMap<>();
        }
    }
}
