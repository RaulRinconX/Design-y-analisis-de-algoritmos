import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class RedundantNetwork 
{
    public static void main(String[] args)
    {
        try{
            //read a file from the resources folder
            FileInputStream is = new FileInputStream("./data/input3.txt");
            Scanner sc = new Scanner(is);


            String line = sc.nextLine();
            int casos = Integer.parseInt(line);
            line = sc.nextLine();

            for (int i = 0; i < casos && line != null && line.length() > 0 && !"0".equals(line); i++){

                List<int[]> connections = new ArrayList<>();
                final String[] data = line.split(" ");
                int n = Integer.parseInt(data[0]);
                int m = Integer.parseInt(data[1]);

                for (int j = 0; j < m; j++) {

                    line = sc.nextLine();
                    String[] conexion = line.split(" ");
                    int src = Integer.parseInt(conexion[0]);
                    int dest = Integer.parseInt(conexion[1]);
                    int costo = Integer.parseInt(conexion[2]);
                    connections.add(new int[]{src,dest, costo});


                }
                System.out.println(format(checkRedundancy(n, connections)));
                line = sc.nextLine();

            }

        }
        catch (Exception e) {
            //System.out.println(e.getMessage());
        }

    }

    public static int[] checkRedundancy(int n, List<int[]> connections)
    {
        UnionFind ufOptic = new UnionFind(n);
        UnionFind ufCoaxial = new UnionFind(n);

        boolean[] redundant = new boolean[connections.size()];
        int[] answer = new int[connections.size()];

        for (int i = 0; i < connections.size(); i++)
        {
            int[] connection = connections.get(i);
            int u = connection[0] - 1;
            int v = connection[1] - 1;
            int k = connection[2];

            if (k == 1)
            {
                ufOptic.union(u, v);
            }
            else
            {
                ufCoaxial.union(u, v);
            }

            redundant[i] = isRedundant(ufOptic, ufCoaxial);
            if(redundant[i]) answer[i] = 1;
            else answer[i] = 0;
            System.out.println(i);
        }
        return answer;
    }

    // verificar si es redundante
    public static boolean isRedundant(UnionFind ufOptic, UnionFind ufCoaxial)
    {
        for(int i=0;i<ufOptic.size();i++)
        {
            Set<Integer> opticSet = ufOptic.getSet(i);
            Set<Integer> coaxialSet = ufCoaxial.getSet(i);
            if(opticSet.size()!=coaxialSet.size()) return false;
            if(!opticSet.containsAll(coaxialSet) || !coaxialSet.containsAll(opticSet))
            {
                return false;
            }
        }
        return true;
    }


    public static String format(int[] answer)
    {
        String fm = "";
        for (int i = 0; i < answer.length; i++)
        {
            fm += Integer.toString(answer[i]) + " ";
        }
        String a = fm.substring(0, fm.length()-1);
        return a;
    }

    static class UnionFind 
    {
        private int[] parent;
        private int[] rank;
        private Map<Integer, Set<Integer>> sets;
    
        public UnionFind(int size) 
        {
            parent = new int[size];
            rank = new int[size];
            sets = new HashMap<>();
    
            for (int i = 0; i < size; i++) 
            {
                parent[i] = i;
                rank[i] = 1;
    
                Set<Integer> newSet = new HashSet<>();
                newSet.add(i);
                sets.put(i, newSet);
            }
        }
    
        public int find(int x) 
        {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    
        public void union(int x, int y) 
        {
            int rootX = find(x);
            int rootY = find(y);
    
            if (rootX == rootY) 
            {
                return;
            }
            if (rank[rootX] > rank[rootY]) 
            {
                parent[rootY] = rootX;
                sets.get(rootX).addAll(sets.get(rootY));
                sets.remove(rootY);
            } 
            else if (rank[rootX] < rank[rootY]) 
            {
                parent[rootX] = rootY;
                sets.get(rootY).addAll(sets.get(rootX));
                sets.remove(rootX);
            } 
            else 
            {
                parent[rootY] = rootX;
                rank[rootX]++;
                sets.get(rootX).addAll(sets.get(rootY));
                sets.remove(rootY);
            }
        }
    
        public Set<Integer> getSet(int element) 
        {
            return sets.get(find(element));
        }
    
        public int size() 
        {
            return parent.length;
        }
    }
}