import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReader {
    private String path;
    private List<String> words;

    public FileReader(String path) {
        this.path = path;
        this.words = new ArrayList<>();
    }

    public void readFileAndSplitByDelimiter(String delimiter) {
        try {
            String content = Files.readString(Path.of(this.path));
            String[] wordArray = content.split(delimiter);
            this.words = Arrays.asList(wordArray);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public List<String> getWords() {
        return this.words;
    }
}