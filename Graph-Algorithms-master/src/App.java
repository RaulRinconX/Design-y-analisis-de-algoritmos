import shortestPaths.*;
import elementaryAlgorithms.*;
import MST.*;

public class App {
    public static void main(String[] args) throws Exception {
        int[][] directedGraph = {{0, 4, 0, 0, 0, 0, 0, 8, 0},
                                {10, 0, 8, 1, 0, 0, 0, 11, 0},
                                {0, 8, 0, 7, 5, 4, 0, 0, 2},
                                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                                {0, 0, 0, 9, 0, 10, 0, 2, 0},
                                {0, 3, 4, 0, 10, 0, 2, 0, 0},
                                {1, 0, 0, 14, 0, 2, 0, 1, 6},
                                {8, 11, 0, 8, 0, 1, 1, 0, 7},
                                {0, 0, 2, 8, 0, 0, 6, 7, 0} };
        
        int[][] undirectedGraph = {{0, 10, 0, 0, 0, 0, 1, 8, 0},
                                   {10, 0, 8, 0, 0, 3, 0, 11, 0},
                                   {0, 8, 0, 7, 0, 4, 0, 0, 2},
                                   {0, 0, 7, 0, 9, 14, 14, 0, 0},
                                   {0, 0, 0, 9, 0, 10, 0, 2, 0},
                                   {0, 3, 4, 14, 10, 0, 2, 0, 0},
                                   {1, 0, 0, 14, 0, 2, 0, 1, 6},
                                   {8, 11, 0, 0, 2, 0, 1, 0, 7},
                                   {0, 0, 2, 0, 0, 0, 6, 7, 0}};
        Dijkstra dijkstra = new Dijkstra();
        printSolution(dijkstra.dijkstra(directedGraph, 5), 5);

        BellmanFord bellman = new BellmanFord();
        printSolution(bellman.bellmanFord(directedGraph, 5), 5);

        FloydWarshall floyd = new FloydWarshall();
        printFloyd(floyd.floydWarshall(directedGraph));

        BreadthFirstSearch bfs =  new BreadthFirstSearch();
        System.out.println("\n"+bfs.bfsShortestPath(directedGraph, 0, 6)+"\n");

        PrimsAlgorithm prim = new PrimsAlgorithm(undirectedGraph);
        printFloyd(prim.getMST());

    }

    private static void printSolution(int[] dist, int source)
    {
        System.out.println("\nDistancia del vertice desde el vertice " + Integer.toString(source)+"\n");
        for (int i = 0; i < dist.length; i++)
        {
            System.out.println(i + " \t\t " + dist[i]);
        }
    }

    private static void printFloyd(int[][] answer)
    {
        for (int i = 0; i < answer.length; ++i) 
        {
            for (int j = 0; j < answer.length; ++j) 
            {
              if (answer[i][j] == Integer.MAX_VALUE)
                System.out.print("INF ");
              else
                System.out.print(answer[i][j] + "  ");
            }
            System.out.println();
          }
    }

    
}
