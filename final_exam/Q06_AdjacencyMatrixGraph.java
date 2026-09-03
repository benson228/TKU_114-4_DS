import java.util.ArrayList;
import java.util.List;

public class Q06_AdjacencyMatrixGraph {

    private final List<String> vertices;
    private final boolean[][] matrix;

    public Q06_AdjacencyMatrixGraph(List<String> vertices) {
        this.vertices = new ArrayList<>();

        if (vertices != null) {
            this.vertices.addAll(vertices);
        }

        this.matrix = new boolean[this.vertices.size()][this.vertices.size()];
    }

    public boolean addEdge(String first, String second) {
        int a = indexOf(first);
        int b = indexOf(second);

        if (a < 0 || b < 0 || a == b) {
            return false;
        }

        if (matrix[a][b]) {
            return false;
        }

        matrix[a][b] = true;
        matrix[b][a] = true;

        return true;
    }

    public boolean removeEdge(String first, String second) {
        int a = indexOf(first);
        int b = indexOf(second);

        if (a < 0 || b < 0 || a == b) {
            return false;
        }

        if (!matrix[a][b]) {
            return false;
        }

        matrix[a][b] = false;
        matrix[b][a] = false;

        return true;
    }

    public boolean hasEdge(String first, String second) {
        int a = indexOf(first);
        int b = indexOf(second);

        if (a < 0 || b < 0) {
            return false;
        }

        return matrix[a][b];
    }

    public int degree(String vertex) {
        int index = indexOf(vertex);

        if (index < 0) {
            return 0;
        }

        int count = 0;

        for (boolean connected : matrix[index]) {
            if (connected) {
                count++;
            }
        }

        return count;
    }

    public List<String> neighbors(String vertex) {
        int index = indexOf(vertex);
        List<String> result = new ArrayList<>();

        if (index < 0) {
            return result;
        }

        for (int i = 0; i < vertices.size(); i++) {
            if (matrix[index][i]) {
                result.add(vertices.get(i));
            }
        }

        return result;
    }

    private int indexOf(String vertex) {
        if (vertex == null) {
            return -1;
        }

        return vertices.indexOf(vertex);
    }
}