import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Q10_UnweightedShortestPath {

    public static List<String> shortestPath(
            Map<String, List<String>> graph,
            String start,
            String target) {

        List<String> result = new ArrayList<>();

        if (graph == null
                || start == null
                || target == null
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
            List<String> neighbors = graph.get(current);

            if (neighbors == null) {
                continue;
            }

            for (String neighbor : neighbors) {
                if (neighbor == null
                        || !graph.containsKey(neighbor)
                        || visited.contains(neighbor)) {
                    continue;
                }

                visited.add(neighbor);
                predecessor.put(neighbor, current);
                queue.offer(neighbor);

                if (neighbor.equals(target)) {
                    queue.clear();
                    break;
                }
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
}