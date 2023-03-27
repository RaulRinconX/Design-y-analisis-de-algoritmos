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
    public static int[][] dijkstraComplete(String rutaArchivo) throws IOException {
        int[][] matriz = leerMatriz(rutaArchivo);
        int[][] resultado = new int[matriz.length][];

        for (int i = 0; i < matriz.length; i++) {
            resultado[i] = dijkstra(matriz, i);
        }
        return resultado;
    }
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
    public static int[][] bellmanFordComplete(String rutaArchivo) throws IOException 
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
        int n = matriz.length;
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    if (matriz[u][v] != -1) {
                        if (dist[u] != Integer.MAX_VALUE && dist[u] + matriz[u][v] < dist[v]) {
                            dist[v] = dist[u] + matriz[u][v];
                        }
                    }
                }
            }
        }
        return dist;
    }

    /////////////// PRINT MATRIZ /////////////////
    public static void printMatrix(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            System.out.println(Arrays.toString(matriz[i]).replaceAll("\\[|\\]|,", ""));
        }
    }
    /////////////// MAIN /////////////////
    public static void main(String[] args) throws IOException {
        int[][] matriz = leerMatriz("data\\"+args[0]);
        System.out.println("\n Matriz de costos:\n");
        printMatrix(matriz);
        // Floyd-Warshall
        System.out.println("\n Floyd-Warshall:\n");
        long startTime = System.nanoTime();
        printMatrix(floydWarshall(matriz));
        long endTime = System.nanoTime();
        Double totalTime = (endTime - startTime)/1_000_000_000.0;
        System.out.println("\nTiempo total de ejecucion en segundos para Floyd-Warshall: " + totalTime);
        // Dijkstra
        System.out.println("\n Dijkstra:\n");
        long startTime1 = System.nanoTime();
        printMatrix(dijkstraComplete("data\\"+args[0]));
        long endTime1 = System.nanoTime();
        Double totalTime1 = (endTime1 - startTime1)/1_000_000_000.0;
        System.out.println("\nTiempo total de ejecucion en segundos para Dijkstra: " + totalTime1);
        // Bellman-Ford
        System.out.println("\n Bellman-Ford:\n");
        long startTime2 = System.nanoTime();
        printMatrix(bellmanFordComplete("data\\"+args[0]));
        long endTime2 = System.nanoTime();
        Double totalTime2 = (endTime2 - startTime2)/1_000_000_000.0;
        System.out.println("\nTiempo total de ejecucion en segundos para Bellman-Ford: " + totalTime2);
        System.out.println("\n");
    }
}