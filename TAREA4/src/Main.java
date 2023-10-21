import java.util.ArrayList;
import java.util.List;

class Main
{
	// Recursive function to print path of given vertex `u` from source vertex `v`
	private static void printPath(int[][] path, int v, int u, List<Integer> route)
	{
		if (path[v][u] == v) {
			return;
		}
		printPath(path, v, path[v][u], route);
		route.add(path[v][u]);
	}

	// Function to print the shortest cost with path information between
	// all pairs of vertices
	private static void printSolution(int[][] path, int n)
	{
		for (int v = 0; v < n; v++)
		{
			for (int u = 0; u < n; u++)
			{
				if (u != v && path[v][u] != -1)
				{
					List<Integer> route = new ArrayList<>();
					route.add(v);
					printPath(path, v, u, route);
					route.add(u);
					System.out.printf("El camino mas corto de %d hacia %d es %s\n",
						v, u, route);
				}
			}
		}
	}

	// Function to run the Floyd–Warshall algorithm
	public static void floydWarshall(int[][] adjMatrix)
	{
		// base case
		if (adjMatrix ==null || adjMatrix.length == 0) {
			return;
		}

		// total number of vertices in the `adjMatrix`
		int n = adjMatrix.length;

		// cost[] and path[] stores shortest path
		// (shortest cost/shortest route) information
		int[][] cost = new int[n][n];
		int[][] path = new int[n][n];

		// initialize cost[] and path[] matrix between each pair of vertices 
		// `u` and `v`, where `v` is the source vertex and `u` is the destination
		// vertex for all possible edges 
		for (int v = 0; v < n; v++)
		{
			for (int u = 0; u < n; u++)
			{
				// initially, cost would be the same as the weight of the edge
				cost[v][u] = adjMatrix[v][u];

				if (v == u) {
					path[v][u] = 0;
				}
				else if (cost[v][u] != Integer.MAX_VALUE) {
					path[v][u] = v;
				}
				else {
					path[v][u] = -1;
				}
			}
		}

		// run Floyd–Warshall
		for (int k = 0; k < n; k++)
		{
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < n; j++)
				{
					// If vertex `k` is on the shortest path from `v` to `u`,
					// then update the value of cost[v][u] and path[v][u]

					if (cost[i][k] != Integer.MAX_VALUE
						&& cost[k][j] != Integer.MAX_VALUE
						&& (cost[i][k] + cost[k][j] < cost[i][j]))
					{
						cost[i][j] = cost[i][k] + cost[k][j];
						path[i][j] = path[k][j];
					}
				}

				// if diagonal elements become negative, the
				// graph contains a negative-weight cycle
				if (cost[i][i] < 0)
				{
					System.out.println("Negative-weight cycle found!!");
					return;
				}
			}
		}

		// Print the shortest path between all pairs of vertices
		printSolution(path, n);
	}

	public static void main(String[] args)
	{
		// define infinity
		int I = Integer.MAX_VALUE;

		// given adjacency representation of the matrix
		int[][] adjMatrix = new int[][]
		{
			{0,	90,	80,	I,	I},
			{15, 0,	69,	 48,I},
			{91, I,0,	12,	39},
			{78, I,  I,  0, 36},
			{26	,12	,39	,33	,0}
		};

		// Run Floyd–Warshall algorithm
		floydWarshall(adjMatrix);
	}
}