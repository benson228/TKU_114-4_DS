import java.util.ArrayDeque;
import java.util.Deque;

public class LevelOrderByLine {

    static class Node {
        String value;
        Node left;
        Node right;

        Node(String value) {
            this.value = value;
        }
    }

    static void printLevelOrder(Node root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }

        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        int level = 0;

        while (!queue.isEmpty()) {
            int nodeCount = queue.size();

            System.out.print(
                    "Level " + level
                    + " (" + nodeCount + " nodes): "
            );

            for (int i = 0; i < nodeCount; i++) {
                Node current = queue.poll();

                System.out.print(current.value + " ");

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            System.out.println();
            level++;
        }
    }

    public static void main(String[] args) {
        Node root = new Node("M");

        root.left = new Node("F");
        root.right = new Node("T");

        root.left.left = new Node("B");
        root.right.left = new Node("R");
        root.right.right = new Node("Z");

        root.left.left.left = new Node("A");

        System.out.println("=== Level Order ===");
        printLevelOrder(root);

        System.out.println("\n=== Empty Tree ===");
        printLevelOrder(null);
    }
}