import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseDependencyGraph {
    private final Map<String, Set<String>> graph =
            new LinkedHashMap<>();

    public boolean addCourse(String course) {
        if (course == null || course.isBlank()) {
            return false;
        }

        String name = course.trim();

        if (graph.containsKey(name)) {
            return false;
        }

        graph.put(name, new LinkedHashSet<>());
        return true;
    }

    public boolean addDependency(
            String prerequisite,
            String course) {

        if (!graph.containsKey(prerequisite)
                || !graph.containsKey(course)) {
            return false;
        }

        if (prerequisite.equals(course)) {
            return false;
        }

        return graph.get(prerequisite).add(course);
    }

    public boolean removeDependency(
            String prerequisite,
            String course) {

        if (!graph.containsKey(prerequisite)
                || !graph.containsKey(course)) {
            return false;
        }

        return graph.get(prerequisite).remove(course);
    }

    public List<String> prerequisitesOf(String course) {
        List<String> result = new ArrayList<>();

        if (!graph.containsKey(course)) {
            return result;
        }

        for (Map.Entry<String, Set<String>> entry : graph.entrySet()) {
            if (entry.getValue().contains(course)) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    public List<String> nextCourses(String course) {
        Set<String> next = graph.get(course);

        if (next == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(next);
    }

    public int inDegree(String course) {
        return prerequisitesOf(course).size();
    }

    public int outDegree(String course) {
        return nextCourses(course).size();
    }

    public List<String> courses() {
        return new ArrayList<>(graph.keySet());
    }

    public List<String> report() {
        List<String> result = new ArrayList<>();

        for (String course : graph.keySet()) {
            result.add(
                course
                + "|prerequisites=" + prerequisitesOf(course)
                + "|next=" + nextCourses(course)
                + "|in=" + inDegree(course)
                + "|out=" + outDegree(course)
            );
        }

        return result;
    }
}