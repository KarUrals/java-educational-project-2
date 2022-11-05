package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static hexlet.code.Formatter.STYLISH;

public class Differ {
    public static final String ERROR_MESSAGE_WO_EXT = "File without extension: ";
    public static final String ERROR_MESSAGE_FILE_NF = "File not found: ";

    public static String generate(String filePath1, String filePath2, String format) throws IOException {

        Map<String, Object> map1 = Parser.parse(getFileExtension(filePath1), getFileContent(filePath1));
        Map<String, Object> map2 = Parser.parse(getFileExtension(filePath2), getFileContent(filePath2));

        return Formatter.generateResult(format, TreeDifferenceFinder.genListOfDiffMaps(map1, map2));
    }

    public static String generate(String filePath1, String filePath2) throws IOException {

        return generate(filePath1, filePath2, STYLISH);
    }

    private static String getFileContent(String filePath) throws IOException {
        if (getFileExtension(filePath) == null) {
            throw new IOException(ERROR_MESSAGE_WO_EXT + filePath);
        }

        Path absolutePath = findAbsolutePath(filePath);
        File file = new File(absolutePath.toString());

        if (!file.exists()) {
            throw new IOException(ERROR_MESSAGE_FILE_NF + filePath);
        }

        return Files.readString(absolutePath);
    }

    private static String getFileExtension(String filePath) {
        String absolutFilePath = findAbsolutePath(filePath).toString();
        int index = absolutFilePath.lastIndexOf('.');

        return index == -1 ? null : absolutFilePath.substring(index + 1);
    }

    private static Path findAbsolutePath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }
}
