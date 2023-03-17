import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Parte2 {
    public static ArrayList<ArrayList<Integer>> findConnectedComponents(int[][] graph) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        ArrayList<ArrayList<Integer>> components = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ArrayList<Integer> component = new ArrayList<>();
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                visited[i] = true;

                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    component.add(node);

                    for (int neighbor = 0; neighbor < n; neighbor++) {
                        if (graph[node][neighbor] == 1 && !visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.offer(neighbor);
                        }
                    }
                }

                components.add(component);
            }
        }

        return components;
    }

    public static void main(String[] args) {
        int[][] graph = {
            {-1, -1, -1, 1, -1, -1, -1},
            {-1, -1, -1, -1, -1, 1, -1},
            {-1, -1, -1, 1, -1, -1, -1},
            {1, -1, 1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, 1},
            {-1, 1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, 1, -1, -1}
        };

        ArrayList<ArrayList<Integer>> components = findConnectedComponents(graph);

        for (ArrayList<Integer> component : components) {
            System.out.println(component);
        }
    }
}
