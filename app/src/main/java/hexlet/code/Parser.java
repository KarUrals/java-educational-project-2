package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import hexlet.code.enums.Extensions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static final String ERROR_MESSAGE = "Unexpected file extension: %s";

    public static Map<String, Object> parse(String fileExtension, String fileContent) throws IOException {
        try {
            return switch (Extensions.valueOf(fileExtension.toUpperCase())) {
                case JSON -> parseJson(fileContent);
                case YAML, YML -> parseYaml(fileContent);
            };
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IOException(String.format(ERROR_MESSAGE, fileExtension));
        }
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
