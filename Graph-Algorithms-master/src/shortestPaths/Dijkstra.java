package shortestPaths;
import java.util.Arrays;

public class Dijkstra 
{
    public int[] dijkstra(int[][] graph, int source)
    {
        int V = graph.length; // number of vertices in the graph

        int [] distances = new int[V]; //array where the distances from the origin to each vertex are save
        Arrays.fill(distances, Integer.MAX_VALUE); // initialize all the distances as infinite
        distances[source] = 0; //the distance from the source to itself is 0

        boolean[] visited = new boolean[V]; //array to mark the vertices already processed
        Arrays.fill(visited, false); // initialize all the vertices as false

        for(int i = 0; i < V-1; i++) //the algorithm runs V-1 times (the source is already processed) 
        {
            // look for the vertex with the shortest distance from the source
            // in the first iteration, the actualVertex will always be the source
            int minDistance = Integer.MAX_VALUE; //initialize the minDistance as infinite
            int actualVertex = -1; //initialize the actualVertex as -1
            for(int j = 0; j < V; j++) //look each vertex
            {
                if(!visited[j] && distances[j] < minDistance) //if the vertex has not been visited and the distance is < than the minDistance
                {
                    minDistance = distances[j]; //update the minDistance
                    actualVertex = j; //update the actualVertex
                }
            }

            visited[actualVertex] = true; //mark that vertex as visited

            //update the distance of the adjacent vertices of the actual vertex
            for(int j = 0; j < V; j++) //look each vertex
            {
                int distance = graph[actualVertex][j]; //get the distance from the actualVertex to the vertex that is being analized(j)
                if(!visited[j] && distance > 0 && distances[actualVertex] != Integer.MAX_VALUE) //if the vertex has not been visited and an arc between the actualVertex and j exist
                {
                    int newDistance = distances[actualVertex] + distance; // calculate the new possible distance
                    if(newDistance < distances[j]) //if the newDistance is < than the actual distance
                    {
                        distances[j] = newDistance; //update the distance
                    }
                }
            }
        }
        return distances;
    }


    
}
