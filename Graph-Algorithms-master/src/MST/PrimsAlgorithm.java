package MST;

import java.util.Arrays;

public class PrimsAlgorithm 
{
    private int V; 
    private int parent[]; 
    private int key[]; 
    private boolean mstSet[]; 
    private int graph[][]; 

    public PrimsAlgorithm(int graph[][])  // Constructor 
    {
        this.V = graph.length; // number of vertices in the graph
        this.parent = new int[V];  // Array to store constructed MST
        this.key = new int[V]; // Key values used to pick minimum weight edge in cut
        this.mstSet = new boolean[V]; // To represent set of vertices not yet included in MST
        this.graph = graph; // the graph
        Arrays.fill(key, Integer.MAX_VALUE); // Initialize all keys as INFINITE
        Arrays.fill(mstSet, false); // Initialize all keys as false
    } 

    public int[][] getMST() 
    {
        int[][] mst = new int[V][V]; // the minimum spanning tree

        key[0] = 0; // Make key 0 so that this vertex is picked as first vertex
        parent[0] = -1; // First node is always root of MST

        for (int count = 0; count < V - 1; count++)  // The MST will have V vertices
        {
            int u = minKey(); // Pick the minimum key vertex from the set of vertices not yet included in MST
            mstSet[u] = true; // Add the picked vertex to the MST Set

            for (int v = 0; v < V; v++)  // Update key value and parent index of the adjacent vertices of the picked vertex. Consider only those vertices which are not yet included in MST
            {
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v])  // graph[u][v] is non zero only for adjacent vertices of m
                {
                    parent[v] = u; // update parent index of v
                    key[v] = graph[u][v]; // update the key only if graph[u][v] is smaller than key[v]
                }
            }
        }

        for (int i = 1; i < V; i++)  // print the constructed MST
        {
            mst[parent[i]][i] = graph[i][parent[i]]; // the graph is undirected, so the edge is saved in both directions
            mst[i][parent[i]] = graph[i][parent[i]]; // the graph is undirected, so the edge is saved in both directions
        }

        return mst;
    }

    private int minKey() {
        int min = Integer.MAX_VALUE, min_index = -1; // Initialize min value
        for (int v = 0; v < V; v++) // Search not nearest vertex not in the MST
        {
            if (mstSet[v] == false && key[v] < min)  // If key[v] is smaller than min and v is not yet included in MST
            {
                min = key[v]; // update min
                min_index = v; // update min_index  
            }
        }
        return min_index; // return the index of the vertex with the minimum key
    }
    
}
