import java.util.Stack;

public class Parte3 {

    private static boolean dfs(int[][] graph, boolean[] visited, int currentNode) {
        Stack<Integer> stack = new Stack<>();
        stack.push(currentNode);

        while (!stack.isEmpty()) {
            int node = stack.pop();

            if (visited[node]) {
                return true; // Se encuentra un ciclo
            }

            visited[node] = true;

            for (int adjacentNode = 0; adjacentNode < graph.length; adjacentNode++) {
                if (graph[node][adjacentNode] >= 0 && !visited[adjacentNode]) {
                    stack.push(adjacentNode);
                }
            }
        }

        return false;
    }

    public static boolean tieneCiclo(int[][] graph) {
        boolean[] visited = new boolean[graph.length];

        for (int startNode = 0; startNode < graph.length; startNode++) {
            if (!visited[startNode]) {
                if (dfs(graph, visited, startNode)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 90, 80, -1, -1},
            {15, 0, 69, 48, -1},
            {91, -1, 0, 12, 39},
            {78, -1, -1, 0, 36},
            {26, 12, 39, 33, 0}
        };

        if (tieneCiclo(graph)) {
            System.out.println("El grafo tiene al menos un ciclo.");
        } else {
            System.out.println("El grafo no tiene ciclos.");
        }
    }
}
