import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Q08_BfsTraversal {

    public static List<String> bfs(
            Map<String, List<String>> graph, String start) {

        List<String> result = new ArrayList<>();

        if (graph == null || start == null || !graph.containsKey(start)) {
            return result;
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            result.add(current);

            List<String> neighbors = graph.get(current);

            if (neighbors == null) {
                continue;
            }

            for (String neighbor : neighbors) {
                if (neighbor != null
                        && graph.containsKey(neighbor)
                        && visited.add(neighbor)) {
                    queue.offer(neighbor);
                }
            }
        }

        return result;
    }

    public static Map<String, Integer> distanceFrom(
            Map<String, List<String>> graph, String start) {

        Map<String, Integer> result = new HashMap<>();

        if (graph == null || start == null || !graph.containsKey(start)) {
            return result;
        }

        Queue<String> queue = new ArrayDeque<>();

        result.put(start, 0);
        queue.offer(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentDistance = result.get(current);

            List<String> neighbors = graph.get(current);

            if (neighbors == null) {
                continue;
            }

            for (String neighbor : neighbors) {
                if (neighbor != null
                        && graph.containsKey(neighbor)
                        && !result.containsKey(neighbor)) {

                    result.put(neighbor, currentDistance + 1);
                    queue.offer(neighbor);
                }
            }
        }

        return result;
    }
}