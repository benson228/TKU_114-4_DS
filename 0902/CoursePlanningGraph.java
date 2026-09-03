import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CoursePlanningGraph {

    private final Map<String, List<String>> graph =
            new HashMap<>();

    public boolean addCourse(String course) {

        if (course == null || course.trim().isEmpty()) {
            return false;
        }

        String name = course.trim();

        if (graph.containsKey(name)) {
            return false;
        }

        graph.put(name, new ArrayList<>());
        return true;
    }

    public boolean addPrerequisite(
            String prerequisite,
            String course) {

        if (prerequisite == null || course == null) {
            return false;
        }

        String from = prerequisite.trim();
        String to = course.trim();

        if (!graph.containsKey(from)
                || !graph.containsKey(to)
                || from.equals(to)) {
            return false;
        }

        if (graph.get(from).contains(to)) {
            return false;
        }

        graph.get(from).add(to);
        return true;
    }

    public boolean reachable(
            String start,
            String target) {

        if (start == null || target == null
                || !graph.containsKey(start.trim())
                || !graph.containsKey(target.trim())) {
            return false;
        }

        String source = start.trim();
        String destination = target.trim();

        if (source.equals(destination)) {
            return true;
        }

        Set<String> visited = new HashSet<>();

        return dfsReachable(
                source,
                destination,
                visited);
    }

    private boolean dfsReachable(
            String current,
            String target,
            Set<String> visited) {

        if (!visited.add(current)) {
            return false;
        }

        for (String next : graph.getOrDefault(current, List.of())) {

            if (next.equals(target)) {
                return true;
            }

            if (dfsReachable(next, target, visited)) {
                return true;
            }
        }

        return false;
    }

    public List<String> affectedCourses(String course) {

        List<String> result = new ArrayList<>();

        if (course == null
                || !graph.containsKey(course.trim())) {
            return result;
        }

        String start = course.trim();
        Set<String> visited = new HashSet<>();

        dfsCollect(start, visited);

        visited.remove(start);
        result.addAll(visited);

        result.sort(String::compareTo);

        return result;
    }

    private void dfsCollect(
            String current,
            Set<String> visited) {

        if (!visited.add(current)) {
            return;
        }

        for (String next :
                graph.getOrDefault(current, List.of())) {

            dfsCollect(next, visited);
        }
    }

    public List<String> prerequisitesOf(String course) {

        List<String> result = new ArrayList<>();

        if (course == null
                || !graph.containsKey(course.trim())) {
            return result;
        }

        String target = course.trim();

        for (Map.Entry<String, List<String>> entry
                : graph.entrySet()) {

            if (entry.getValue().contains(target)) {
                result.add(entry.getKey());
            }
        }

        result.sort(String::compareTo);

        return result;
    }
}