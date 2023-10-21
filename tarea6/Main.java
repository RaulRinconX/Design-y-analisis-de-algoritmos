import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Ejemplo de uso
        List<Set<Integer>> setC = new ArrayList<Set<Integer>>();
        int U; // Valor entero
        Set<Set<Integer>> setF = new HashSet<Set<Integer>>();
        
        // Inicializa las variables
        U = 1;
        boolean result = verify(setC, U, setF);
        System.out.println(result);
    }

    public static boolean verify(List<Set<Integer>> C, int U, Set<Set<Integer>> F) {
        boolean r = C.size() <= U;
        boolean s = false; 
        for (Set<Integer> c : C) {
            for (Set<Integer> n : F) {
                if (c.equals(n)) s = true;
                else break;
            }
            r = r && s;
        }
        return r;
   }
}
