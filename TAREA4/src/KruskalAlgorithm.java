import java.util.*;

public class KruskalAlgorithm {
    public static void main(String[] args) {
        int[][] graph = {
            {0, 2, 0, 6, 0},
            {2, 0, 3, 8, 5},
            {0, 3, 0, 0, 7},
            {6, 8, 0, 0, 9},
            {0, 5, 7, 9, 0}
        };
        int V = graph.length;
        int[][] mst = kruskalMST(graph, V);
        for (int i = 0; i < V - 1; i++) {
            System.out.println(mst[i][0] + " - " + mst[i][1] + ": " + mst[i][2]);
        }
    }

    static int[][] kruskalMST(int[][] graph, int V) {
        int[][] result = new int[V - 1][3]; // this will store the resultant MST
        int e = 0; // index variable, used for result[]
        int i = 0; // index variable, used for sorted edges
        int[][] edges = new int[V*V][3];
        for (int u = 0; u < V; u++) {
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0) {
                    edges[e][0] = u;
                    edges[e][1] = v;
                    edges[e][2] = graph[u][v];
                    e++;
                }
            }
        }
        Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));
        int[] parent = new int[V];
        for (int v = 0; v < V; v++) {
            parent[v] = v;
        }
        while (i < V - 1) {
            int[] nextEdge = edges[i++];
            int x = find(parent, nextEdge[0]);
            int y = find(parent, nextEdge[1]);
            if (x != y) {
                result[i - 1] = nextEdge;
                union(parent, x, y);
            }
        }
        return result;
    }

    static int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }

    static void union(int[] parent, int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        parent[xset] = yset;
    }
}