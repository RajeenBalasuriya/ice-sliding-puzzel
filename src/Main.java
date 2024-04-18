import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {

        String mazeDirectory = "../maze";

        // Create an instance of MazeFileHandler
        MazeFileHandler fileHandler = new MazeFileHandler(mazeDirectory);

        // Get a random maze file from the directory
        Path randomMazeFile = fileHandler.getRandomMazeFile();
        if (randomMazeFile != null) {
            System.out.println("Selected maze file: " + randomMazeFile.getFileName());
            // Create an instance of RandomMazeGraph and pass the maze file
            RandomMazeGraph mazeGraph = new RandomMazeGraph(randomMazeFile.toString());
            mazeGraph.printAdjacencyMatrix();



        } else {
            System.out.println("No maze files found in the directory.");
        }
    }

    }
