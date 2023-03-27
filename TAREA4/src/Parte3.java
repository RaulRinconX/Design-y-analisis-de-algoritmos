import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Parte3 {
    
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
    private static boolean dfs(int[][] grafo, boolean[] visitado, int verticeActual) {
        Stack<Integer> pila = new Stack<>();
        pila.push(verticeActual);

        while (!pila.isEmpty()) {
            int vertice = pila.pop();

            if (visitado[vertice]) {
                return true; // Se encuentra un ciclo
            }

            visitado[vertice] = true;

            for (int verticeAdyacente = 0; verticeAdyacente < grafo.length; verticeAdyacente++) {
                if (grafo[vertice][verticeAdyacente] >= 0 && !visitado[verticeAdyacente]) {
                    pila.push(verticeAdyacente);
                }
            }
        }

        return false;
    }

    public static boolean tieneCiclo(int[][] grafo) {
        boolean[] visitado = new boolean[grafo.length];

        for (int startvertice = 0; startvertice < grafo.length; startvertice++) {
            if (!visitado[startvertice]) {
                if (dfs(grafo, visitado, startvertice)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
      
        int[][] matriz = leerMatriz("data\\"+args[0]);
        long startTime = System.nanoTime();
        if (tieneCiclo(matriz)) {
            System.out.println("\n El grafo tiene al menos un ciclo.\n");
        } else {
            System.out.println("\n El grafo no tiene ciclos.\n ");
        }
        long endTime = System.nanoTime();
        Double totalTime = (endTime - startTime)/1_000_000_000.0;
        System.out.println("\nTiempo total de ejecucion en segundos para DFS: " + totalTime);
    }
}
