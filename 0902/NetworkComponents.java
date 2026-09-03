import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class NetworkComponents {

    public static List<Set<String>> components(
            Map<String, List<String>> graph) {

        List<Set<String>> result = new ArrayList<>();

        if (graph == null || graph.isEmpty()) {
            return result;
        }

        Set<String> visited = new HashSet<>();

        for (String start : graph.keySet()) {

            if (visited.contains(start)) {
                continue;
            }

            Set<String> component = new LinkedHashSet<>();
            Queue<String> queue = new ArrayDeque<>();

            queue.offer(start);
            visited.add(start);

            while (!queue.isEmpty()) {
                String current = queue.poll();
                component.add(current);

                for (String next :
                        graph.getOrDefault(current, List.of())) {

                    if (next != null
                            && graph.containsKey(next)
                            && visited.add(next)) {

                        queue.offer(next);
                    }
                }
            }

            result.add(component);
        }

        return result;
    }

    public static int componentCount(
            Map<String, List<String>> graph) {

        return components(graph).size();
    }

    public static Set<String> largestComponent(
            Map<String, List<String>> graph) {

        Set<String> largest = new LinkedHashSet<>();

        for (Set<String> component : components(graph)) {
            if (component.size() > largest.size()) {
                largest = new LinkedHashSet<>(component);
            }
        }

        return largest;
    }
}