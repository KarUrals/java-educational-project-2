package hexlet.code;

import com.ginsberg.junit.exit.ExpectSystemExitWithStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static hexlet.code.DifferTest.NESTED_JSON_FILE_PATH1;
import static hexlet.code.DifferTest.NESTED_JSON_FILE_PATH2;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CliTest {
    private static final CommandLine CMD = new CommandLine(new App());
    private static ByteArrayOutputStream output;

    @BeforeEach
    public void setUpStreams() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    void correctParametersCliTest() throws IOException {
        int exitCode = CMD.execute(NESTED_JSON_FILE_PATH1, NESTED_JSON_FILE_PATH2);
        String expected = Files.readString(Paths.get("./src/test/resources/expected/nestedStylish")) + "\n";
        assertEquals(expected, output.toString());
        assertEquals(0, exitCode);
    }

    @Test
    void wrongParametersCliTest() {
        int exitCode = CMD.execute("-f=yaml", NESTED_JSON_FILE_PATH1, NESTED_JSON_FILE_PATH2);
        String expected = Formatter.ERROR_MESSAGE + "yaml" + "\n";
        assertEquals(expected, output.toString());
        assertEquals(0, exitCode);
    }

    @Test
    @ExpectSystemExitWithStatus(0)
    void testMyAppExit() {
        App.main(new String[] {});
    }
}
