import java.util.*;

public class TopologicalSort {
    private Map<Integer, List<Integer>> graph;

    public TopologicalSort(Map<Integer, List<Integer>> graph) {
        this.graph = graph;
    }

    public List<Integer> sort() {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();

        for (Integer node : graph.keySet()) {
            if (!visited.contains(node)) {
                dfs(node, visited, stack);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    private void dfs(Integer node, Set<Integer> visited, Stack<Integer> stack) {
        visited.add(node);

        if (graph.containsKey(node)) {
            for (Integer neighbor : graph.get(node)) {
                if (!visited.contains(neighbor)) {
                    dfs(neighbor, visited, stack);
                }
            }
        }

        stack.push(node);
    }

    // crea el main
    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1));
        graph.put(1, Arrays.asList(2,3));
        graph.put(2, Arrays.asList(3,4));
        graph.put(3, Arrays.asList(4));
        graph.put(4, Arrays.asList());

        TopologicalSort topologicalSort = new TopologicalSort(graph);
        System.out.println(topologicalSort.sort());
    }
}