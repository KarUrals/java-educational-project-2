package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

@Command(name = "gendiff", version = "gendiff 1.0", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable {

    @Option(names = { "-f", "--format" }, paramLabel = "format", defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;

    @Parameters(index = "0", paramLabel = "filepath1", defaultValue = "./src/test/resources/nestedFile1.json",
            description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", paramLabel = "filepath2", defaultValue = "./src/test/resources/nestedFile2.json",
            description = "path to first file")
    private String filepath2;

    @Override
    public Object call() throws Exception {
        System.out.println(Differ.generate(filepath1, filepath2, format));
        return null;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
