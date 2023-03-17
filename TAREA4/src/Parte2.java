import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Parte2 {
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
        int[][] grafo = {
            {-1, -1, -1, 1, -1, -1, -1},
            {-1, -1, -1, -1, -1, 1, -1},
            {-1, -1, -1, 1, -1, -1, -1},
            {1, -1, 1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, 1},
            {-1, 1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, 1, -1, -1}
        };

        ArrayList<ArrayList<Integer>> componentes = componentesConectados(grafo);

        for (ArrayList<Integer> componente : componentes) {
            System.out.println(componente);
        }
    }
}
