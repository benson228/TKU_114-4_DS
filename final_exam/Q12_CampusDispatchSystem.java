import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

record Request(String id, String location, int priority, long sequence) {}

public class Q12_CampusDispatchSystem {

    private final Map<String, Set<String>> graph = new LinkedHashMap<>();
    private final Map<String, Request> requests = new HashMap<>();

    private final PriorityQueue<Request> queue = new PriorityQueue<>(
            Comparator.comparingInt(Request::priority)
                    .thenComparingLong(Request::sequence)
    );

    public boolean addLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            return false;
        }

        String normalized = location.trim();

        if (graph.containsKey(normalized)) {
            return false;
        }

        graph.put(normalized, new LinkedHashSet<>());
        return true;
    }

    public boolean addRoad(String first, String second) {
        if (first == null || second == null) {
            return false;
        }

        String a = first.trim();
        String b = second.trim();

        if (!graph.containsKey(a)
                || !graph.containsKey(b)
                || a.equals(b)) {
            return false;
        }

        if (!graph.get(a).add(b)) {
            return false;
        }

        graph.get(b).add(a);

        return true;
    }

    public boolean submit(Request request) {
        if (request == null
                || request.id() == null
                || request.location() == null
                || request.id().trim().isEmpty()
                || request.location().trim().isEmpty()) {
            return false;
        }

        String id = request.id().trim();
        String location = request.location().trim();

        if (!graph.containsKey(location) || requests.containsKey(id)) {
            return false;
        }

        Request normalized = new Request(
                id,
                location,
                request.priority(),
                request.sequence()
        );

        requests.put(id, normalized);
        queue.offer(normalized);

        return true;
    }

    public Request nextReachable(String serviceCenter) {
        if (serviceCenter == null) {
            return null;
        }

        String center = serviceCenter.trim();

        if (!graph.containsKey(center)) {
            return null;
        }

        Set<String> reachable = reachableFrom(center);

        List<Request> skipped = new ArrayList<>();
        Request selected = null;

        while (!queue.isEmpty()) {
            Request request = queue.poll();

            if (reachable.contains(request.location())) {
                selected = request;
                break;
            }

            skipped.add(request);
        }

        for (Request request : skipped) {
            queue.offer(request);
        }

        if (selected != null) {
            requests.remove(selected.id());
        }

        return selected;
    }

    public List<String> route(String start, String target) {
        List<String> result = new ArrayList<>();

        if (start == null || target == null) {
            return result;
        }

        String source = start.trim();
        String destination = target.trim();

        if (!graph.containsKey(source)
                || !graph.containsKey(destination)) {
            return result;
        }

        if (source.equals(destination)) {
            result.add(source);
            return result;
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> predecessor = new HashMap<>();

        queue.offer(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            for (String neighbor : graph.get(current)) {
                if (visited.contains(neighbor)) {
                    continue;
                }

                visited.add(neighbor);
                predecessor.put(neighbor, current);

                if (neighbor.equals(destination)) {
                    queue.clear();
                    break;
                }

                queue.offer(neighbor);
            }
        }

        if (!visited.contains(destination)) {
            return result;
        }

        String current = destination;

        while (current != null) {
            result.add(0, current);

            if (current.equals(source)) {
                break;
            }

            current = predecessor.get(current);
        }

        return result;
    }

    public int pendingCount() {
        return requests.size();
    }

    private Set<String> reachableFrom(String start) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();

        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            for (String neighbor : graph.get(current)) {
                if (visited.add(neighbor)) {
                    queue.offer(neighbor);
                }
            }
        }

        return visited;
    }
}