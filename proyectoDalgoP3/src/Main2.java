import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main2 {
    private static LinkedHashMap<Integer, Set<Integer>> politicians = new LinkedHashMap<>();
    private static HashSet<Integer> citizens = new HashSet<>();

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
                    politicians.computeIfAbsent(politician, k -> new HashSet<>()).add(n);
                }
            }
            greedySetCover();
            politicians.clear();
            citizens.clear();
        }
        br.close();
    }

    private static void greedySetCover() {
        PriorityQueue<Map.Entry<Integer, Set<Integer>>> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(b.getValue().size(), a.getValue().size())
        );
        pq.addAll(politicians.entrySet());

        ArrayList<Integer> requiredPoliticians = new ArrayList<>();
        while (!citizens.isEmpty() && !pq.isEmpty()) {
            Map.Entry<Integer, Set<Integer>> entry = pq.poll();
            Set<Integer> intersect = new HashSet<>(entry.getValue());
            intersect.retainAll(citizens);

            if (!intersect.isEmpty()) {
                requiredPoliticians.add(entry.getKey());
                citizens.removeAll(intersect);
                for (Map.Entry<Integer, Set<Integer>> e : pq) {
                    e.getValue().removeAll(intersect);
                }
                pq.removeIf(e -> e.getValue().isEmpty());
            }
        }

        Collections.sort(requiredPoliticians);
        for(int politician : requiredPoliticians) {
            System.out.print(politician + " ");
        }
        System.out.println();
    }
}

