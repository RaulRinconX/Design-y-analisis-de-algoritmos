import java.util.*;
import java.io.*;

public class Parte1 {

    public static final int INF = Integer.MAX_VALUE;
    /// Función para leer la matriz de costos desde un archivo de texto ///
    public static int[][] leerMatriz(String filename) {
        int[][] matriz = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (matriz == null) {
                    matriz = new int[parts.length][parts.length];
                }
                for (int j = 0; j < parts.length; j++) {
                    matriz[i][j] = Integer.parseInt(parts[j]);
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matriz;
    }
    /////////// FLOYD-WARSHALL //////////////
    public static int[][] floydWarshall(int[][] matriz) {
        int n = matriz.length;
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = matriz[i][j] != -1 ? matriz[i][j] : INF;
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        int newDist = dist[i][k] + dist[k][j];
                        if (dist[i][j] == INF || newDist < dist[i][j]) {
                            dist[i][j] = newDist;
                        }
                    }
                }
            }
        }
        return dist;
    }

    ///////////  DIJKSTRA  //////////////
    public static int[] dijkstra(int[][] grafo, int fuente) {
        int n = grafo.length;
        boolean[] visitados = new boolean[n];
        int[] distancia = new int[n];
        Arrays.fill(distancia, Integer.MAX_VALUE);
        distancia[fuente] = 0;
        for (int k = 0; k < n; k++) {
            int minimo = Integer.MAX_VALUE;
            int u = -1;
            for (int i = 0; i < n; i++) {
                if (!visitados[i] && distancia[i] < minimo) {
                    minimo = distancia[i];
                    u = i;
                }
            }
            if (u == -1) break;
            visitados[u] = true;
            for (int v = 0; v < n; v++) {
                if (grafo[u][v] != -1 && !visitados[v]) {
                    distancia[v] = Math.min(distancia[v], distancia[u] + grafo[u][v]);
                }
            }
        }
        return distancia;
    }
    /////////// BELLMANFORD //////////////
    public int[][] bellmanFordComplete(String rutaArchivo) throws IOException 
    {
        int[][] matriz = leerMatriz(rutaArchivo);
        int[][] resultado = new int[matriz.length][];

        for (int i = 0; i < matriz.length; i++) {
            resultado[i] = bellmanFord(matriz, i);
        }

        return resultado;
    }

    private static int[] bellmanFord(int[][] matriz, int start) 
    {
        int[] memory = new int[matriz.length];
        Arrays.fill(memory, Integer.MAX_VALUE);
        memory[start] = 0;

        for (int i = 0; i < matriz.length - 1; i++) 
        {
            for (int u = 0; u < matriz.length; u++) 
            {
                for (int v = 0; v < matriz[0].length; v++) 
                {
                    int peso = matriz[u][v];
                    if (peso != 0 && memory[u] != Integer.MAX_VALUE && memory[u] + peso < memory[v]) 
                    {
                        memory[v] = memory[u] + peso;
                    }
                }
            }
        }

        return memory;
    }

    /////////////// PRINT MATRIZ /////////////////
    public static void printMatrix(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            System.out.println(Arrays.toString(matriz[i]).replaceAll("\\[|\\]|,", ""));
        }
    }
    /////////////// MAIN /////////////////
    public static void main(String[] args) {
        int[][] matriz = leerMatriz("data\\"+args[0]);
        System.out.println("Matriz de costos:");
        printMatrix(matriz);
        // Floyd-Warshall
        System.out.println("Floyd-Warshall:");
        printMatrix(floydWarshall(matriz));
        // Dijkstra
        System.out.println("Dijkstra:");
        printMatrix(new int[][]{dijkstra(matriz, 0)});
        // Bellman-Ford
        System.out.println("Bellman-Ford:");
        printMatrix(new int[][]{bellmanFord(matriz, 0)});
    }
}