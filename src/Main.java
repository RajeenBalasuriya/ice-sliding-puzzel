import java.nio.file.Path;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
// Specify the directory containing the maze files
        String mazeDirectory = "../maze";

        // Create an instance of MazeFileHandler
        MazeFileHandler fileHandler = new MazeFileHandler(mazeDirectory);

        // Get a random maze file from the directory
        Path randomMazeFile = fileHandler.getRandomMazeFile();
        if (randomMazeFile != null) {
            System.out.println("Selected maze file: " + randomMazeFile.getFileName());


        } else {
            System.out.println("No maze files found in the directory.");
        }
    }

    }
