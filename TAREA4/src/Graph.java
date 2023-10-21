import java.util.*;

public class Graph {
    private int V;
    private int[][] adjMatrix;

    public Graph(int V) {
        this.V = V;
        adjMatrix = new int[V][V];
    }

    public void addEdge(int u, int v) {
        adjMatrix[u][v] = 1;
    }

    public List<Integer> topologicalSort() {
        Stack<Integer> stack = new Stack<>(); // stack to store the vertices in topological order
        boolean[] visited = new boolean[V];  // to keep track of visited vertices

        for (int i = 0; i < V; i++) { 	 // for each vertex
            if (!visited[i]) {      	 // if it is not visited
                dfs(i, visited, stack);  // perform DFS
            }
        }

        List<Integer> result = new ArrayList<>();  // list to store the vertices in topological order

        while (!stack.isEmpty()) {   // pop vertices from the stack and add them to the list
            result.add(stack.pop()); 
        }

        return result;
    }

    private void dfs(int u, boolean[] visited, Stack<Integer> stack) { // u is the current vertex
        visited[u] = true; // mark u as visited

        for (int v = 0; v < V; v++) { // for each vertex v
            if (adjMatrix[u][v] == 1 && !visited[v]) { // if there is an edge from u to v and v is not visited
                dfs(v, visited, stack);                // perform DFS on v 
            }
        }

        stack.push(u); // push u to the stack
    }
}