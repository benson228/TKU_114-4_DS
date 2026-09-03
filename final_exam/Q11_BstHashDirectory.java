import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q11_BstHashDirectory {

    private static class Node {
        int id;
        String name;
        Node left;
        Node right;

        Node(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    private Node root;
    private final Map<Integer, String> index = new HashMap<>();
    private int size;

    public boolean add(int id, String name) {
        if (id <= 0 || name == null || name.trim().isEmpty()) {
            return false;
        }

        String normalizedName = name.trim();

        if (index.containsKey(id)) {
            return false;
        }

        root = insert(root, id, normalizedName);
        index.put(id, normalizedName);
        size++;

        return true;
    }

    private Node insert(Node node, int id, String name) {
        if (node == null) {
            return new Node(id, name);
        }

        if (id < node.id) {
            node.left = insert(node.left, id, name);
        } else if (id > node.id) {
            node.right = insert(node.right, id, name);
        }

        return node;
    }

    public String findName(int id) {
        if (id <= 0) {
            return null;
        }

        return index.get(id);
    }

    public boolean remove(int id) {
        if (id <= 0 || !index.containsKey(id)) {
            return false;
        }

        root = removeNode(root, id);
        index.remove(id);
        size--;

        return true;
    }

    private Node removeNode(Node node, int id) {
        if (node == null) {
            return null;
        }

        if (id < node.id) {
            node.left = removeNode(node.left, id);
        } else if (id > node.id) {
            node.right = removeNode(node.right, id);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor = findMin(node.right);
            node.id = successor.id;
            node.name = successor.name;
            node.right = removeNode(node.right, successor.id);
        }

        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    public List<Integer> idsBetween(int low, int high) {
        List<Integer> result = new ArrayList<>();

        if (low > high) {
            return result;
        }

        collectRange(root, low, high, result);

        return result;
    }

    private void collectRange(
            Node node,
            int low,
            int high,
            List<Integer> result) {

        if (node == null) {
            return;
        }

        if (node.id > low) {
            collectRange(node.left, low, high, result);
        }

        if (node.id >= low && node.id <= high) {
            result.add(node.id);
        }

        if (node.id < high) {
            collectRange(node.right, low, high, result);
        }
    }

    public int size() {
        return size;
    }
}
