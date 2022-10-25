package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;

public class DifferTest {
    private final String jsonFilePath1 = "./src/test/resources/file1.json";
    private final String jsonFilePath2 = "./src/test/resources/file2.json";
    private final String ymlFilePath1 = "./src/test/resources/file1.yml";
    private final String ymlFilePath2 = "./src/test/resources/file2.yml";
    private final String incorrectFilePath = "/src/test/resources/fileyml";
    private final String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";

    @Test
    public void jsonDifferGenerateTest() throws Exception {
        String actual = Differ.generate(jsonFilePath1, jsonFilePath2);
        assertEquals(actual, expected);
    }

    @Test
    public void ymlDifferGenerateTest() throws Exception {
        String actual = Differ.generate(ymlFilePath1, ymlFilePath2);
        assertEquals(actual, expected);
    }

//    @Test
//    public void incorrectFileNameTest() throws Exception {
//        String errorMessage = "Unexpected format: null";
//        Throwable thrown = assertThrows(Exception.class, () -> {
//            Differ.generate(ymlFilePath1, incorrectFilePath);
//        });
//        assertEquals(thrown.getMessage(), errorMessage);
//    }
}
