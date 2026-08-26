import java.util.ArrayList;
import java.util.List;

public class TraversalResultCollector {

    static class Node {
        String value;
        Node left;
        Node right;

        Node(String value) {
            this.value = value;
        }
    }

    static List<String> preorder(Node node) {
        List<String> result = new ArrayList<>();
        preorder(node, result);
        return result;
    }

    static void preorder(
            Node node,
            List<String> result) {

        if (node == null) {
            return;
        }

        result.add(node.value);
        preorder(node.left, result);
        preorder(node.right, result);
    }

    static List<String> inorder(Node node) {
        List<String> result = new ArrayList<>();
        inorder(node, result);
        return result;
    }

    static void inorder(
            Node node,
            List<String> result) {

        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.value);
        inorder(node.right, result);
    }

    static List<String> postorder(Node node) {
        List<String> result = new ArrayList<>();
        postorder(node, result);
        return result;
    }

    static void postorder(
            Node node,
            List<String> result) {

        if (node == null) {
            return;
        }

        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.value);
    }

    static List<String> levelOrder(Node node) {
        List<String> result = new ArrayList<>();

        if (node == null) {
            return result;
        }

        List<Node> queue = new ArrayList<>();
        queue.add(node);

        int index = 0;

        while (index < queue.size()) {
            Node current = queue.get(index);
            index++;

            result.add(current.value);

            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }

        return result;
    }

    static void printResults(String name, Node root) {
        System.out.println("=== " + name + " ===");
        System.out.println("Preorder: " + preorder(root));
        System.out.println("Inorder: " + inorder(root));
        System.out.println("Postorder: " + postorder(root));
        System.out.println("Level-order: " + levelOrder(root));
        System.out.println();
    }

    public static void main(String[] args) {

        Node empty = null;

        Node single = new Node("A");

        Node leftSkewed = new Node("A");
        leftSkewed.left = new Node("B");
        leftSkewed.left.left = new Node("C");

        Node complete = new Node("A");
        complete.left = new Node("B");
        complete.right = new Node("C");
        complete.left.left = new Node("D");
        complete.left.right = new Node("E");
        complete.right.left = new Node("F");
        complete.right.right = new Node("G");

        printResults("Empty", empty);
        printResults("Single Node", single);
        printResults("Left Skewed", leftSkewed);
        printResults("Complete Tree", complete);
    }
}
