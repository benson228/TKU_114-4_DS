import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MetroTransferPath {

    public static List<String> shortestPath(
            Map<String, List<String>> graph,
            String start,
            String target) {

        List<String> result = new ArrayList<>();

        if (graph == null || start == null || target == null
                || !graph.containsKey(start)
                || !graph.containsKey(target)) {
            return result;
        }

        if (start.equals(target)) {
            result.add(start);
            return result;
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> predecessor = new HashMap<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            for (String next : graph.getOrDefault(current, List.of())) {

                if (next == null
                        || !graph.containsKey(next)
                        || !visited.add(next)) {
                    continue;
                }

                predecessor.put(next, current);

                if (next.equals(target)) {
                    queue.clear();
                    break;
                }

                queue.offer(next);
            }
        }

        if (!visited.contains(target)) {
            return result;
        }

        String current = target;

        while (current != null) {
            result.add(0, current);

            if (current.equals(start)) {
                break;
            }

            current = predecessor.get(current);
        }

        return result;
    }

    public static int edgeCount(
            Map<String, List<String>> graph,
            String start,
            String target) {

        List<String> path = shortestPath(graph, start, target);

        if (path.isEmpty()) {
            return -1;
        }

        return path.size() - 1;
    }
}
