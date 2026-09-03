import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q07_AdjacencyListGraph {

    private final Map<String, Set<String>> graph = new LinkedHashMap<>();
    private int edgeCount;

    public boolean addVertex(String vertex) {
        if (vertex == null || vertex.isBlank()) {
            return false;
        }

        if (graph.containsKey(vertex)) {
            return false;
        }

        graph.put(vertex, new LinkedHashSet<>());
        return true;
    }

    public boolean addEdge(String from, String to) {
        if (!graph.containsKey(from) || !graph.containsKey(to)) {
            return false;
        }

        if (from.equals(to)) {
            return false;
        }

        if (!graph.get(from).add(to)) {
            return false;
        }

        edgeCount++;
        return true;
    }

    public boolean removeEdge(String from, String to) {
        if (!graph.containsKey(from) || !graph.containsKey(to)) {
            return false;
        }

        if (!graph.get(from).remove(to)) {
            return false;
        }

        edgeCount--;
        return true;
    }

    public List<String> outgoing(String vertex) {
        if (!graph.containsKey(vertex)) {
            return new ArrayList<>();
        }

        return new ArrayList<>(graph.get(vertex));
    }

    public int inDegree(String vertex) {
        if (!graph.containsKey(vertex)) {
            return 0;
        }

        int count = 0;

        for (Set<String> neighbors : graph.values()) {
            if (neighbors.contains(vertex)) {
                count++;
            }
        }

        return count;
    }

    public int edgeCount() {
        return edgeCount;
    }
}