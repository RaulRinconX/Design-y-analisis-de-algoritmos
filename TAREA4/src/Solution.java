import java.util.Arrays;

public class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[] answer = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(answer, Integer.MAX_VALUE);
        answer[k-1] = 0;
        for (int contar=0; contar<n; contar++){
            int minimo = Integer.MAX_VALUE;
            int u = -1;
            for(int i=0; i<n; i++){
                if(visited[i] == false && answer[i] <= minimo){
                    minimo = answer[i];
                    u = i;
                }
            }
            if(u==-1) break;
            visited[u] = true;
            for(int v=0; v<n; v++){
                if(!visited[v] && times[u][2] > 0 && answer[u] != Integer.MAX_VALUE && answer[u] + times[u][2] < answer[v] ){
                    answer[v] = answer[u] + times[u][2];
                }
            }
        }
        int tiempoMinimo = Arrays.stream(answer).sum();
        return tiempoMinimo;
    }

    //haz el main y pasale esto al networkDelayTime times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2

    public static void main(String[] args) {
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        int n = 4;
        int k = 2;
        Solution sol = new Solution();
        System.out.println(sol.networkDelayTime(times, n, k));
    }
}