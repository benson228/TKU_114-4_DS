import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

record ReachabilityQuery(String from, String to) {}

public class DirectedReachability {

    public static boolean reachable(
            Map<String, List<String>> graph,
            String from,
            String to) {

        if (graph == null || from == null || to == null
                || !graph.containsKey(from)
                || !graph.containsKey(to)) {
            return false;
        }

        if (from.equals(to)) {
            return true;
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        queue.offer(from);
        visited.add(from);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            for (String next : graph.getOrDefault(current, List.of())) {
                if (next == null || !graph.containsKey(next)) {
                    continue;
                }

                if (next.equals(to)) {
                    return true;
                }

                if (visited.add(next)) {
                    queue.offer(next);
                }
            }
        }

        return false;
    }

    public static List<Boolean> checkQueries(
            Map<String, List<String>> graph,
            List<ReachabilityQuery> queries) {

        List<Boolean> result = new ArrayList<>();

        if (queries == null) {
            return result;
        }

        for (ReachabilityQuery query : queries) {
            if (query == null) {
                result.add(false);
            } else {
                result.add(reachable(
                        graph,
                        query.from(),
                        query.to()));
            }
        }

        return result;
    }
}
