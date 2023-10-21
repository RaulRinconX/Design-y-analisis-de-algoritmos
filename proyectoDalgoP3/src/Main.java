import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    private static LinkedHashMap<Integer, Set<Integer>> politicians = new LinkedHashMap<>();
    private static HashSet<Integer> citizens = new HashSet<>();
    private static Set<Integer> intersection;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("default.in"));
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            String[] line = br.readLine().split(" ");
            int N = Integer.parseInt(line[0]);
            int M = Integer.parseInt(line[1]);
            for(int m = 1; m <= M; m++) {
                politicians.put(m, new HashSet<>());
            }
            for(int n = 1; n <= N; n++) {
                citizens.add(n);
                line = br.readLine().split(" ");
                for(String s : line) {
                    int politician = Integer.parseInt(s);
                    politicians.get(politician).add(n);
                }
            }
            greedySetCover();
            politicians.clear();
            citizens.clear();
        }
        br.close();
    }

    private static void greedySetCover() {
        ArrayList<Integer> requiredPoliticians = new ArrayList<>();

        while (!citizens.isEmpty()) {
            int max = Integer.MIN_VALUE;
            int maxKey = -1;
            Set<Integer> maxIntersection = new HashSet<>();
            for (int key : politicians.keySet()) {
                intersection = new HashSet<>(politicians.get(key));
                intersection.retainAll(citizens);
                if (max < intersection.size()) {
                    maxKey = key;
                    max = intersection.size();
                    maxIntersection = intersection;
                }
            }
            citizens.removeAll(maxIntersection);
            requiredPoliticians.add(maxKey);
        }
        Collections.sort(requiredPoliticians);
        for(int politician : requiredPoliticians) {
            System.out.print(politician + " ");
        }
        System.out.println();
    }
}