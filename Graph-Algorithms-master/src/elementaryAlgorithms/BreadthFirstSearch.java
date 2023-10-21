package elementaryAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {
    

    public List<Integer> bfsShortestPath(int[][] graph, int source, int dest) 
    {
        int V = graph.length; // number of vertices in the graph
        Queue<Integer> queue = new LinkedList<>(); //initialize the Queue  
        boolean[] visited = new boolean[V]; //array to know if a node have been visited
        int[] parent = new int[V]; //array to know from wich node we get to the current one
        Arrays.fill(parent, -1); //at the beginning no node has a parent
        
        queue.offer(source);
        visited[source] = true;//check the source as visited

        //bfs algorithm
        while (!queue.isEmpty()) 
        {
            int current = queue.poll();//get the first nodo in the queue
            if (current == dest) //if the aim node is reached the loop breaks
            {
                break;
            }

            for (int neighbor = 0; neighbor < V; neighbor++) // loop to iterate among the neighbors of the node
            {
                if (graph[current][neighbor] > 1 && !visited[neighbor]) //if an edge exist and we haven´t visited the neighbor
                {
                    visited[neighbor] = true; //mark the neighbor as visited
                    parent[neighbor] = current; //the parent of that neighbor is the current node
                    queue.offer(neighbor); //put the neighbor in the queue
                }
            }
        }

        List<Integer> path = new ArrayList<>(); //list where the path will be store
        for (int i = dest; i != -1; i = parent[i]) //get the parents starting from the aim node until we get to the source node
        {
            path.add(i);
        }
        Collections.reverse(path);

        return path;
    }
}
