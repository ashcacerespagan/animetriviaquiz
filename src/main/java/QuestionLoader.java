import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class QuestionLoader {

    public static List<Question> loadFromFile(String filePath) throws IOException {
        List<Question> questions = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts.length == 6) {
                questions.add(new Question(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
            }
        }

        return questions;
    }

    public static List<Question> loadFromSeries(String filePath) throws IOException {
        return loadFromFile(filePath);
    }

    public static List<Question> loadAll(String folderPath) throws IOException {
        List<Question> allQuestions = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(folderPath), "*.txt")) {
            for (Path path : stream) {
                allQuestions.addAll(loadFromFile(path.toString()));
            }
        }
        return allQuestions;
    }
}
