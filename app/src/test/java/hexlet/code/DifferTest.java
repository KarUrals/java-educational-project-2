package hexlet.code;

//import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DifferTest {
    private final String format = "stylish";
    private final String incorrectFilePath = "./src/test/resources/file.kyml";
    private final String simpleFileResultExpected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
    private final String nestedFileResultExpected = """
              {
                  chars1: [a, b, c]
                - chars2: [d, e, f]
                + chars2: false
                - checked: false
                + checked: true
                - default: null
                + default: [value1, value2]
                - id: 45
                + id: null
                - key1: value1
                + key2: value2
                  numbers1: [1, 2, 3, 4]
                - numbers2: [2, 3, 4, 5]
                + numbers2: [22, 33, 44, 55]
                - numbers3: [3, 4, 5]
                + numbers4: [4, 5, 6]
                + obj1: {nestedKey=value, isNested=true}
                - setting1: Some value
                + setting1: Another value
                - setting2: 200
                + setting2: 300
                - setting3: true
                + setting3: none
              }""";

    @Test
    public void nestedJsonDifferGenerateTest() throws Exception {
        String nestedJsonFilePath1 = "./src/test/resources/nestedFile1.json";
        String nestedJsonFilePath2 = "./src/test/resources/nestedFile2.json";
        String actual = Differ.generate(nestedJsonFilePath1, nestedJsonFilePath2, format);
        assertEquals(actual, nestedFileResultExpected);
    }

    @Test
    public void nestedYmlDifferGenerateTest() throws Exception {
        String nestedYmlFilePath1 = "./src/test/resources/nestedFile1.yml";
        String nestedYmlFilePath2 = "./src/test/resources/nestedFile2.yml";
        String actual = Differ.generate(nestedYmlFilePath1, nestedYmlFilePath2, format);
        assertEquals(actual, nestedFileResultExpected);
    }

    @Test
    public void jsonDifferGenerateTest() throws Exception {
        String jsonFilePath1 = "./src/test/resources/file1.json";
        String jsonFilePath2 = "./src/test/resources/file2.json";
        String actual = Differ.generate(jsonFilePath1, jsonFilePath2, format);
        assertEquals(actual, simpleFileResultExpected);
    }

    @Test
    public void ymlDifferGenerateTest() throws Exception {
        String ymlFilePath1 = "./src/test/resources/file1.yml";
        String ymlFilePath2 = "./src/test/resources/file2.yml";
        String actual = Differ.generate(ymlFilePath1, ymlFilePath2, format);
        assertEquals(actual, simpleFileResultExpected);
    }

    @Test
    void incorrectFileNameTest() {
        String errorMessage = "Unexpected format: .kyml";
        Throwable th = assertThrows(IOException.class, () -> {
            Parser.parse(incorrectFilePath);
        });
        assertEquals(th.getMessage(), errorMessage);
    }
}
