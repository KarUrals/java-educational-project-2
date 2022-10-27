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

    @BeforeAll
    static void beforeAll() throws IOException {
        expectedPlain = Files.readString(Paths.get("./src/test/resources/expected/plain.txt"));
        expectedStylish = Files.readString(Paths.get("./src/test/resources/expected/nestedStylish"));
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

        String fileWithoutExtension = "./src/test/resources/expected/nestedStylish";
        String errorMessage2 = "Invalid parameter: file '" + fileWithoutExtension + "' without extension";
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

        String emptyFile = "./src/test/resources/expected/emptyFile.yml";
        String errorMessage4 = "Invalid parameter: file '" + emptyFile + "' is empty";
        Throwable thrown4 = assertThrows(IOException.class, () ->
                Differ.generate(nestedYmlFilePath1, emptyFile)
        );
        assertEquals(thrown4.getMessage(), errorMessage4);

        String nonExistingFile = "someFile.json";
        String errorMessage5 = "Invalid parameter: file '" + nonExistingFile + "' does not exist";
        Throwable thrown5 = assertThrows(IOException.class, () ->
                Differ.generate(nestedYmlFilePath1, nonExistingFile)
        );
        assertEquals(thrown5.getMessage(), errorMessage5);
    }
}
