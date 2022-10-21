package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private final String filePath1 = "./src/test/resources/file1.json";
    private final String filePath2 = "./src/test/resources/file2.json";

    @Test
    public void differGenerateTest() throws Exception {
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        String actual = Differ.generate(filePath1, filePath2);
        assertEquals(actual, expected);
    }
}
