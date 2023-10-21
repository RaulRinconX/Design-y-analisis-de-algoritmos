import java.util.*;

public class BellmanFord {
    public static void bellmanFord(int[][] graph, int source) {
        int[] distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;
        
        // Run the algorithm |V| - 1 times
        for (int i = 0; i < graph.length - 1; i++) {
            for (int j = 0; j < graph.length; j++) {
                for (int k = 0; k < graph.length; k++) {
                    if (graph[j][k] != 0 && distances[j] != Integer.MAX_VALUE && distances[j] + graph[j][k] < distances[k]) {
                        distances[k] = distances[j] + graph[j][k]; // Relaxation
                    }
                }
            }
        }
        
        // Check for negative cycles
        for (int i = 0; i < graph.length - 1; i++) {
            for (int j = 0; j < graph.length; j++) {
                for (int k = 0; k < graph.length; k++) {
                    if (graph[j][k] != 0 && distances[j] != Integer.MAX_VALUE && distances[j] + graph[j][k] < distances[k]) {
                        System.out.println("Graph contains negative cycle");
                        return;
                    }
                }
            }
        }
        
        // Print the distances from the source vertex to all other vertices
        for (int i = 0; i < graph.length; i++) {
            System.out.println("Distance from " + source + " to " + i + " is " + distances[i]);
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 6, 0, 7, 0},
            {0, 0, 5, 8, -4},
            {0, -2, 0, 0, 0},
            {0, 0, -3, 0, 9},
            {2, 0, 7, 0, 0}
        };
        bellmanFord(graph, 0);
    }
}