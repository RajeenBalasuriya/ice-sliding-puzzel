import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RandomMazeGraph {
    private char[][] maze;
    private int numRows;
    private int numCols;
    private int[][] adjacencyMatrix;
    private int startRow;
    private int startCol;
    private int endRow;
    private int endCol;

    public RandomMazeGraph(String mazeFileName) {
        parseMazeFromFile(mazeFileName);
        createAdjacencyMatrix();
    }

    private void parseMazeFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            List<String> lines = new ArrayList<>();
            String line;
            // Read all lines from the file
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            // Initialize numRows based on the number of lines
            numRows = lines.size();
            if (numRows == 0) {
                throw new IllegalArgumentException("Empty maze file: " + filename);
            }
            // Initialize numCols based on the length of the first line
            numCols = lines.get(0).length();
            maze = new char[numRows][numCols];
            // Parse each line into the maze array
            for (int i = 0; i < numRows; i++) {
                line = lines.get(i);
                if (line.length() != numCols) {
                    throw new IllegalArgumentException("Inconsistent row length in maze file: " + filename);
                }
                for (int j = 0; j < numCols; j++) {
                    maze[i][j] = line.charAt(j);
                    // Locate start and end positions
                    if (maze[i][j] == 'S') {
                        startRow = i;
                        startCol = j;
                    } else if (maze[i][j] == 'F') {
                        endRow = i;
                        endCol = j;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to create the adjacency matrix
    private void createAdjacencyMatrix() {
        adjacencyMatrix = new int[numRows][numCols];
        // Initialize adjacency matrix with zeros
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }
        // Populate adjacency matrix based on maze connectivity
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (maze[i][j] != '0') {
                    int vertexIndex = i * numCols + j;
                    if (i > 0 && maze[i - 1][j] != '0') {
                        // Connect to cell above
                        adjacencyMatrix[i][j] = 1;
                        adjacencyMatrix[i - 1][j] = 1;
                    }
                    if (i < numRows - 1 && maze[i + 1][j] != '0') {
                        // Connect to cell below
                        adjacencyMatrix[i][j] = 1;
                        adjacencyMatrix[i + 1][j] = 1;
                    }
                    if (j > 0 && maze[i][j - 1] != '0') {
                        // Connect to cell on the left
                        adjacencyMatrix[i][j] = 1;
                        adjacencyMatrix[i][j - 1] = 1;
                    }
                    if (j < numCols - 1 && maze[i][j + 1] != '0') {
                        // Connect to cell on the right
                        adjacencyMatrix[i][j] = 1;
                        adjacencyMatrix[i][j + 1] = 1;
                    }
                }
            }
        }
    }

    // Method to print the adjacency matrix (for testing)
    public void printAdjacencyMatrix() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Getters for start and end positions
    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getEndCol() {
        return endCol;
    }
}
