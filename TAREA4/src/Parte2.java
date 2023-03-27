import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Parte2 {
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
    public static ArrayList<ArrayList<Integer>> componentesConectados(int[][] grafo) {
        int n = grafo.length;
        boolean[] visitado = new boolean[n];
        ArrayList<ArrayList<Integer>> componentes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!visitado[i]) {
                ArrayList<Integer> componente = new ArrayList<>();
                Queue<Integer> cola = new LinkedList<>();
                cola.offer(i);
                visitado[i] = true;

                while (!cola.isEmpty()) {
                    int node = cola.poll();
                    componente.add(node);

                    for (int vecino = 0; vecino < n; vecino++) {
                        if (grafo[node][vecino] == 1 && !visitado[vecino]) {
                            visitado[vecino] = true;
                            cola.offer(vecino);
                        }
                    }
                }

                componentes.add(componente);
            }
        }

        return componentes;
    }
    public static void main(String[] args) {
        
        int[][] matriz = leerMatriz("data\\"+args[0]);

        ArrayList<ArrayList<Integer>> componentes = componentesConectados(matriz);
        long startTime = System.nanoTime();
        for (ArrayList<Integer> componente : componentes) {
            System.out.println(componente);
        }
        long endTime = System.nanoTime();
        Double totalTime = (endTime - startTime)/1_000_000_000.0;
        System.out.println("\nTiempo total de ejecucion en segundos para BFS: " + totalTime);
    }
}
