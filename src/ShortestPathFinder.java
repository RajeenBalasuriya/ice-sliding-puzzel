import java.util.*;

public class ShortestPathFinder {
    private char[][] maze;
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Possible movements: up, down, left, right

    public ShortestPathFinder(char[][] maze) {
        this.maze = maze;
    }

    public List<int[]> findShortestPath(int[] start, int[] end) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);

        Map<String, int[]> parentMap = new HashMap<>(); // Using String as key for mapping coordinates

        String startKey = getKey(start);
        parentMap.put(startKey, null);

        List<int[]> validPath = new ArrayList<>(); // Store the valid path

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (Arrays.equals(current, end)) {
                // If the end square is found, reconstruct the path and store it
                validPath.add(current);
                String tempKey = getKey(current);
                while (parentMap.get(tempKey) != null) {
                    int[] temp = parentMap.get(tempKey);
                    validPath.add(temp);
                    tempKey = getKey(temp);
                }
                Collections.reverse(validPath); // Reverse the path to start from the start point
                break; // Exit the loop as we found the end square
            }

            for (int[] direction : directions) {
                int newX = current[0] + direction[0];
                int newY = current[1] + direction[1];
                int[] next = {newX, newY};
                String nextKey = getKey(next);

                if (isValidMove(next) && !parentMap.containsKey(nextKey)) {
                    queue.add(next);
                    parentMap.put(nextKey, current);
                }
            }
        }

        return validPath;
    }

    private boolean isValidMove(int[] cell) {
        int x = cell[0];
        int y = cell[1];

        // Check if the cell is within the maze boundaries
        if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length) {
            return false; // Cell is out of bounds
        }

        // Check if the cell is an obstacle
        return maze[x][y] != '0'; // Allow movement through empty spaces ('.') and finish ('F') positions
    }

    private String getKey(int[] cell) {
        return cell[0] + "," + cell[1]; // Generating a unique key for the coordinates
    }
}
