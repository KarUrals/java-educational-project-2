package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DifferTest {

    private static String expectedPlain;
    private static String expectedStylish;
    private static String expectedJson;
    private final String nestedJsonFilePath1 = "./src/test/resources/nestedFile1.json";
    private final String nestedJsonFilePath2 = "./src/test/resources/nestedFile2.json";
    private final String nestedYmlFilePath1 = "./src/test/resources/nestedFile1.yml";
    private final String nestedYmlFilePath2 = "./src/test/resources/nestedFile2.yml";
    private final String stylishFormat = "stylish";
    private final String plainFormat = "plain";

    @BeforeAll
    static void beforeAll() throws IOException {
        expectedPlain = Files.readString(Paths.get("./src/test/resources/expected/plain.txt"));
        expectedStylish = Files.readString(Paths.get("./src/test/resources/expected/nestedStylish.txt"));
        expectedJson = Files.readString(Paths.get("./src/test/resources/expected/json.json"));
    }

    @Test
    void jsonStyleTest() throws Exception {
        String format = "json";
        String actualJson = Differ.generate(nestedJsonFilePath1, nestedJsonFilePath2, format);
        assertEquals(actualJson, expectedJson);

        String actualYml = Differ.generate(nestedYmlFilePath1, nestedYmlFilePath2, format);
        assertEquals(actualYml, expectedJson);
    }

    @Test
    void plainStyleTest() throws Exception {
        String format = "plain";
        String actualJson = Differ.generate(nestedJsonFilePath1, nestedJsonFilePath2, format);
        assertEquals(actualJson, expectedPlain);

        String actualYml = Differ.generate(nestedYmlFilePath1, nestedYmlFilePath2, format);
        assertEquals(actualYml, expectedPlain);
    }

    @Test
    void stylishStyleTest() throws Exception {
        String format = "stylish";
        String actualJson = Differ.generate(nestedJsonFilePath1, nestedJsonFilePath2, format);
        assertEquals(actualJson, expectedStylish);

        String actualYml = Differ.generate(nestedYmlFilePath1, nestedYmlFilePath2, format);
        assertEquals(actualYml, expectedStylish);
    }

    @Test
    void exceptionTest() {
        String fileWithWrongExtension = "./src/test/resources/expected/plain.txt";
        String errorMessage1 = "Unexpected file extension: .txt";
        Throwable thrown1 = assertThrows(IOException.class, () ->
                Differ.generate(fileWithWrongExtension, nestedYmlFilePath2)
        );
        assertEquals(thrown1.getMessage(), errorMessage1);

        String fileWithoutExtension = "someFile";
        String errorMessage2 = "One of the files without extension";
        Throwable thrown2 = assertThrows(IOException.class, () ->
                Differ.generate(fileWithoutExtension, nestedYmlFilePath2)
        );
        assertEquals(thrown2.getMessage(), errorMessage2);

        String wrongFormat = "yaml";
        String errorMessage3 = "Unexpected format: " + wrongFormat;
        Throwable thrown3 = assertThrows(IOException.class, () ->
                Differ.generate(nestedYmlFilePath1, nestedYmlFilePath2, wrongFormat)
        );
        assertEquals(thrown3.getMessage(), errorMessage3);
    }
}
