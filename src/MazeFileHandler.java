import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MazeFileHandler {
    private final String mazeDirectory;

    public MazeFileHandler(String mazeDirectory) {
        this.mazeDirectory = mazeDirectory;
    }

    // Method to list all maze files in the directory
    public List<Path> listMazeFiles() {
        List<Path> mazeFiles = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(mazeDirectory))) {
            for (Path entry : stream) {
                if (Files.isRegularFile(entry) && entry.toString().endsWith(".txt")) {
                    mazeFiles.add(entry);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mazeFiles;
    }

    // Method to choose a random maze file from the list of maze files
    public Path getRandomMazeFile() {
        List<Path> mazeFiles = listMazeFiles();
        if (!mazeFiles.isEmpty()) {
            Random random = new Random();
            return mazeFiles.get(random.nextInt(mazeFiles.size()));
        }
        return null;
    }

}
