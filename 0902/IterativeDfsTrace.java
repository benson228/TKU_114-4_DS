import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IterativeDfsTrace {

    public static List<String> trace(
            Map<String, List<String>> graph, String start) {

        List<String> result = new ArrayList<>();

        if (graph == null || start == null || !graph.containsKey(start)) {
            return result;
        }

        Deque<String> stack = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        stack.push(start);
        result.add("push " + start
                + " | stack=" + stack
                + " | visited=" + visited);

        while (!stack.isEmpty()) {
            String current = stack.pop();

            result.add("pop " + current
                    + " | stack=" + stack
                    + " | visited=" + visited);

            if (!visited.add(current)) {
                continue;
            }

            result.add("visit " + current
                    + " | stack=" + stack
                    + " | visited=" + visited);

            List<String> neighbors = graph.getOrDefault(current, List.of());

            for (int i = neighbors.size() - 1; i >= 0; i--) {
                String neighbor = neighbors.get(i);

                if (neighbor != null
                        && graph.containsKey(neighbor)
                        && !visited.contains(neighbor)
                        && !stack.contains(neighbor)) {

                    stack.push(neighbor);

                    result.add("push " + neighbor
                            + " | stack=" + stack
                            + " | visited=" + visited);
                }
            }
        }

        return result;
    }

    public static List<String> dfsOrder(
            Map<String, List<String>> graph, String start) {

        List<String> result = new ArrayList<>();

        if (graph == null || start == null || !graph.containsKey(start)) {
            return result;
        }

        Deque<String> stack = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            String current = stack.pop();

            if (!visited.add(current)) {
                continue;
            }

            result.add(current);

            List<String> neighbors =
                    graph.getOrDefault(current, List.of());

            for (int i = neighbors.size() - 1; i >= 0; i--) {
                String neighbor = neighbors.get(i);

                if (neighbor != null
                        && graph.containsKey(neighbor)
                        && !visited.contains(neighbor)) {

                    stack.push(neighbor);
                }
            }
        }

        return result;
    }
}
