package shortestPaths;
public class FloydWarshall
{
    public int[][] floydWarshall(int[][] graph)
    {
        int V = graph.length; // number of vertices in the graph
        int [][] distances = new int[V][V]; //array where the distances from the origin to each vertex are save

        // Initialize the distances array with the values from the input graph
        for (int i = 0; i < V; i++) 
        {
            for (int j = 0; j < V; j++) 
            {
                if (graph[i][j] == 0 && i != j) 
                {
                    distances[i][j] = Integer.MAX_VALUE;
                } 
                else 
                {
                    distances[i][j] = graph[i][j];
                }
            }
        }

        for(int i = 0; i < V; i++)
        {
            for(int j = 0; j < V; j++)
            {
                for(int k = 0; k < V; k++)
                {
                    if(distances[j][i] != Integer.MAX_VALUE && distances[i][k] != Integer.MAX_VALUE && distances[j][i] + distances[i][k] < distances[j][k])
                    {
                        distances[j][k] = distances[j][i]+distances[i][k];
                    }
                }
            }
        }
        return distances;
    }
}