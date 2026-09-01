import java.util.ArrayList;
import java.util.List;

public class CampusMatrixGraph {
    private final List<String> vertices;
    private final boolean[][] matrix;
    private int edgeCount;

    public CampusMatrixGraph(List<String> vertices) {
        if (vertices == null || vertices.isEmpty()) {
            throw new IllegalArgumentException("vertices");
        }

        this.vertices = new ArrayList<>(vertices);
        this.matrix = new boolean[vertices.size()][vertices.size()];
    }

    private int indexOf(String vertex) {
        int index = vertices.indexOf(vertex);

        if (index < 0) {
            throw new IllegalArgumentException("unknown vertex");
        }

        return index;
    }

    public void addEdge(String first, String second) {
        int a = indexOf(first);
        int b = indexOf(second);

        if (!matrix[a][b]) {
            matrix[a][b] = true;
            matrix[b][a] = true;
            edgeCount++;
        }
    }

    public void removeEdge(String first, String second) {
        int a = indexOf(first);
        int b = indexOf(second);

        if (matrix[a][b]) {
            matrix[a][b] = false;
            matrix[b][a] = false;
            edgeCount--;
        }
    }

    public boolean hasEdge(String first, String second) {
        int a = indexOf(first);
        int b = indexOf(second);

        return matrix[a][b];
    }

    public int degree(String vertex) {
        int row = indexOf(vertex);
        int count = 0;

        for (boolean connected : matrix[row]) {
            if (connected) {
                count++;
            }
        }

        return count;
    }

    public List<String> neighbors(String vertex) {
        int row = indexOf(vertex);
        List<String> result = new ArrayList<>();

        for (int i = 0; i < vertices.size(); i++) {
            if (matrix[row][i]) {
                result.add(vertices.get(i));
            }
        }

        return result;
    }

    public int edgeCount() {
        return edgeCount;
    }
}