package us.javarush.tkachenko.cryptoanalyser.file;

import us.javarush.tkachenko.cryptoanalyser.exceptions.FileProcessingException;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

public class FileNameValidator {
    public static final List<String> FORBIDDEN_DIRS_FILES = List.of(".bash_history", "bash_profile", "etc", "proc");

    public void validateForWriting(String fileName) {
        Path path = validatePath(fileName);

        if (Files.exists(path)) {
            if (Files.isDirectory(path)) {
                throw new FileProcessingException("Provided path " + path + " is a directory. Please provide a path to the file!");
            }

            if (!Files.isWritable(path)) {
                throw new FileProcessingException("File " + path + "is not accessible for writing!");
            }
        }
    }

    public void validateForReading(String fileName) {
        Path path = validatePath(fileName);

        if (Files.exists(path)) {
            if (Files.isDirectory(path)) {
                throw new FileProcessingException("Provided path " + path + " is a directory. Please provide a path to file!");
            }

            if (!Files.isReadable(path)) {
                throw new FileProcessingException("Permissions to read from this file " + path + " is missed!");
            }
        }

    }

    private Path validatePath(String fileName) {
        for (String pathPart : fileName.split(System.getProperty("file.separator"))) {
            if (FORBIDDEN_DIRS_FILES.contains(pathPart)) {
                throw new FileProcessingException("Path contains forbidden part: " + pathPart);
            }
        }

        try {
            return Path.of(fileName);
        } catch (InvalidPathException exception) {
            throw new FileProcessingException("Invalid path. Reason: " + exception.getMessage(), exception);
        }
    }
}
