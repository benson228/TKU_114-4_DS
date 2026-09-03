import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.HashSet;
import java.util.Set;

public class BfsLayerReport {

    public static Map<String, Integer> distanceFrom(
            Map<String, List<String>> graph, String start) {

        Map<String, Integer> result = new LinkedHashMap<>();

        if (graph == null || start == null || !graph.containsKey(start)) {
            return result;
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);
        result.put(start, 0);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int distance = result.get(current);

            for (String next : graph.getOrDefault(current, List.of())) {
                if (next != null
                        && graph.containsKey(next)
                        && visited.add(next)) {

                    result.put(next, distance + 1);
                    queue.offer(next);
                }
            }
        }

        return result;
    }

    public static List<String> report(
            Map<String, List<String>> graph, String start) {

        return distanceFrom(graph, start)
                .entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .toList();
    }
}