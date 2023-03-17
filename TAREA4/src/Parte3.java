import java.util.Stack;

public class Parte3 {

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
        int[][] grafo = {
            {0, 90, 80, -1, -1},
            {15, 0, 69, 48, -1},
            {91, -1, 0, 12, 39},
            {78, -1, -1, 0, 36},
            {26, 12, 39, 33, 0}
        };

        if (tieneCiclo(grafo)) {
            System.out.println("\n El grafo tiene al menos un ciclo.\n");
        } else {
            System.out.println("El grafo no tiene ciclos.");
        }
    }
}
