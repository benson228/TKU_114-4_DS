import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CampusNavigationSystem {

    private final Map<String, List<String>> roads = new HashMap<>();

    public boolean addLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            return false;
        }

        String name = location.trim();

        if (roads.containsKey(name)) {
            return false;
        }

        roads.put(name, new ArrayList<>());
        return true;
    }

    public boolean addRoad(String first, String second) {
        if (first == null || second == null) {
            return false;
        }

        String a = first.trim();
        String b = second.trim();

        if (!roads.containsKey(a)
                || !roads.containsKey(b)
                || a.equals(b)) {
            return false;
        }

        if (roads.get(a).contains(b)) {
            return false;
        }

        roads.get(a).add(b);
        roads.get(b).add(a);

        return true;
    }

    public List<String> shortestPath(String start, String target) {

        List<String> result = new ArrayList<>();

        if (start == null || target == null) {
            return result;
        }

        String source = start.trim();
        String destination = target.trim();

        if (!roads.containsKey(source)
                || !roads.containsKey(destination)) {
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

            for (String next : roads.get(current)) {

                if (!visited.add(next)) {
                    continue;
                }

                predecessor.put(next, current);

                if (next.equals(destination)) {
                    queue.clear();
                    break;
                }

                queue.offer(next);
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

    public int edgeCount(String start, String target) {
        List<String> path = shortestPath(start, target);

        if (path.isEmpty()) {
            return -1;
        }

        return path.size() - 1;
    }
}
