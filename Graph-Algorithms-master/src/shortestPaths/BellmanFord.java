package shortestPaths;
import java.util.Arrays;

public class BellmanFord 
{
    public int[] bellmanFord(int[][] graph, int source)
    {
        int V = graph.length; // number of vertices in the graph

        int [] distances = new int[V]; //array where the distances from the origin to each vertex are save
        Arrays.fill(distances, Integer.MAX_VALUE); // initialize all the distances as infinite
        distances[source] = 0; //the distance from the source to itself is 0

        for(int i = 0; i < V-1; i++)//the algorithm runs V-1 times
        {
            for(int j = 0; j < V; j++)//look each vertex
            {
                for(int k = 0; k < V; k++)//look the ayacent vertices to the vertex it's being analized (j)
                {
                    if(graph[j][k] != 0 && distances[j] != Integer.MAX_VALUE)//if a endge between the vertex j and the vertex k exist
                    {
                        int newDistance = distances[j] + graph[j][k]; //calculate the new distance
                        if(newDistance < distances[k])
                        {
                            distances[k] = newDistance; //set a shortest path between the source and the vertex k
                        }
                    }
                }
            }
        }

        //look if the graph has negative cicles
        for(int i = 0; i < V; i++)
        {
            for(int j = 0; j < V; j++)
            {
                if (graph[i][j] != 0) // if there is an edge between the vertex i and the vertex j
                {
                    int distance = distances[j] + graph[i][j]; //calculate the distance between the vertex i and the vertex j
                    if (distance < distances[j]) //if the distance is less than the current distance
                    {
                        throw new IllegalArgumentException("The graph has negative cicles"); //the graph has negative cicles
                    }
                }
            }
        }

        return distances;
    }
    
}
