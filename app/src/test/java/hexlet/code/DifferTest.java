package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static hexlet.code.Formatter.JSON_FORMAT;
import static hexlet.code.Formatter.PLAIN_FORMAT;
import static hexlet.code.Formatter.STYLISH_FORMAT;
import static hexlet.code.Formatter.IDENTICAL_FILES_MESSAGE;
import static hexlet.code.Formatter.EMPTY_FILES_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DifferTest {
    public static final String NESTED_JSON_FILE_PATH1 = "./src/test/resources/nestedFile1.json";
    public static final String NESTED_JSON_FILE_PATH2 = "./src/test/resources/nestedFile2.json";
    private static final String NESTED_YML_FILE_PATH1 = "./src/test/resources/nestedFile1.yml";
    private static final String NESTED_YML_FILE_PATH2 = "./src/test/resources/nestedFile2.yml";
    private static final String EMPTY_YML_FILE_PATH = "./src/test/resources/expected/emptyFile.yml";
    private static final String EMPTY_JSON_FILE_PATH = "./src/test/resources/expected/emptyFile.json";
    private static String expectedPlain;
    private static String expectedStylish;
    private static String expectedJson;
    private static Differ differ;
    private static Parser parser;
    private static Formatter formatter;

    @BeforeAll
    static void beforeAll() throws IOException {
        expectedPlain = Files.readString(Paths.get("./src/test/resources/expected/plain.txt"));
        expectedStylish = Files.readString(Paths.get("./src/test/resources/expected/nestedStylish"));
        expectedJson = Files.readString(Paths.get("./src/test/resources/expected/json.json"));
        differ = new Differ();
        parser = new Parser();
        formatter = new Formatter();
    }

    @Test
    void defaultStyleTest() throws Exception {
        String actualJson = differ.generate(NESTED_JSON_FILE_PATH1, NESTED_JSON_FILE_PATH2);
        assertEquals(actualJson, expectedStylish);

        String actualYml = differ.generate(NESTED_YML_FILE_PATH1, NESTED_YML_FILE_PATH2);
        assertEquals(actualYml, expectedStylish);
    }

    @Test
    void jsonStyleTest() throws Exception {
        String actualJson = Differ.generate(NESTED_JSON_FILE_PATH1, NESTED_JSON_FILE_PATH2, JSON_FORMAT);
        assertEquals(actualJson, expectedJson);

        String actualYml = Differ.generate(NESTED_YML_FILE_PATH1, NESTED_YML_FILE_PATH2, JSON_FORMAT);
        assertEquals(actualYml, expectedJson);

        String compareSameFiles = Differ.generate(NESTED_JSON_FILE_PATH1, NESTED_JSON_FILE_PATH1, JSON_FORMAT);
        assertEquals(compareSameFiles, IDENTICAL_FILES_MESSAGE);

        String compareEmptyFiles = Differ.generate(EMPTY_YML_FILE_PATH, EMPTY_JSON_FILE_PATH, JSON_FORMAT);
        assertEquals(compareEmptyFiles, EMPTY_FILES_MESSAGE);
    }

    @Test
    void plainStyleTest() throws Exception {
        String actualJson = Differ.generate(NESTED_JSON_FILE_PATH1, NESTED_JSON_FILE_PATH2, PLAIN_FORMAT);
        assertEquals(actualJson, expectedPlain);

        String actualYml = Differ.generate(NESTED_YML_FILE_PATH1, NESTED_YML_FILE_PATH2, PLAIN_FORMAT);
        assertEquals(actualYml, expectedPlain);

        String compareSameFiles = Differ.generate(NESTED_YML_FILE_PATH1, NESTED_YML_FILE_PATH1, PLAIN_FORMAT);
        assertEquals(compareSameFiles, IDENTICAL_FILES_MESSAGE);

        String compareEmptyFiles = Differ.generate(EMPTY_YML_FILE_PATH, EMPTY_JSON_FILE_PATH, PLAIN_FORMAT);
        assertEquals(compareEmptyFiles, EMPTY_FILES_MESSAGE);
    }

    @Test
    void stylishStyleTest() throws Exception {
        String actualJson = Differ.generate(NESTED_JSON_FILE_PATH1, NESTED_JSON_FILE_PATH2, STYLISH_FORMAT);
        assertEquals(actualJson, expectedStylish);

        String actualYml = Differ.generate(NESTED_YML_FILE_PATH1, NESTED_YML_FILE_PATH2, STYLISH_FORMAT);
        assertEquals(actualYml, expectedStylish);

        String compareSameFiles = Differ.generate(NESTED_JSON_FILE_PATH2, NESTED_JSON_FILE_PATH2, STYLISH_FORMAT);
        assertEquals(compareSameFiles, IDENTICAL_FILES_MESSAGE);

        String compareEmptyFiles = Differ.generate(EMPTY_YML_FILE_PATH, EMPTY_JSON_FILE_PATH, STYLISH_FORMAT);
        assertEquals(compareEmptyFiles, EMPTY_FILES_MESSAGE);
    }

    @Test
    void exceptionTest() {
        String fileWithWrongExtension = "./src/test/resources/expected/plain.txt";
        String errorMessage1 = parser.ERROR_MESSAGE + "txt";
        Throwable thrown1 = assertThrows(IOException.class, () ->
                Differ.generate(fileWithWrongExtension, NESTED_YML_FILE_PATH2)
        );
        assertEquals(thrown1.getMessage(), errorMessage1);

        String fileWithoutExtension = "./src/test/resources/expected/nestedStylish";
        String errorMessage2 = Differ.ERROR_MESSAGE_WO_EXT + fileWithoutExtension;
        Throwable thrown2 = assertThrows(IOException.class, () ->
                Differ.generate(fileWithoutExtension, NESTED_YML_FILE_PATH2)
        );
        assertEquals(thrown2.getMessage(), errorMessage2);

        String wrongFormat = "yaml";
        String errorMessage3 = formatter.ERROR_MESSAGE + wrongFormat;
        Throwable thrown3 = assertThrows(IOException.class, () ->
                Differ.generate(NESTED_YML_FILE_PATH1, NESTED_YML_FILE_PATH2, wrongFormat)
        );
        assertEquals(thrown3.getMessage(), errorMessage3);

        String nonExistingFile = "someFile.json";
        String errorMessage4 = Differ.ERROR_MESSAGE_FILE_NF + nonExistingFile;
        Throwable thrown4 = assertThrows(IOException.class, () ->
                Differ.generate(NESTED_YML_FILE_PATH1, nonExistingFile)
        );
        assertEquals(thrown4.getMessage(), errorMessage4);
    }
}
