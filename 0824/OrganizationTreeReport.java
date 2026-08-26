import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class OrganizationTreeReport {

    static class Node {
        String name;
        Node left;
        Node right;

        Node(String name) {
            this.name = name;
        }
    }

    static Node findNode(Node node, String target) {
        if (node == null || target == null) {
            return null;
        }

        if (target.equals(node.name)) {
            return node;
        }

        Node leftResult = findNode(node.left, target);

        if (leftResult != null) {
            return leftResult;
        }

        return findNode(node.right, target);
    }

    static Node findParent(Node root, String target) {
        if (root == null || target == null) {
            return null;
        }

        if ((root.left != null
                && target.equals(root.left.name))
                || (root.right != null
                && target.equals(root.right.name))) {
            return root;
        }

        Node leftParent =
                findParent(root.left, target);

        if (leftParent != null) {
            return leftParent;
        }

        return findParent(root.right, target);
    }

    static int findDepth(Node root, String target) {
        return findDepth(root, target, 0);
    }

    static int findDepth(
            Node node,
            String target,
            int depth) {

        if (node == null || target == null) {
            return -1;
        }

        if (target.equals(node.name)) {
            return depth;
        }

        int leftDepth =
                findDepth(
                        node.left,
                        target,
                        depth + 1);

        if (leftDepth != -1) {
            return leftDepth;
        }

        return findDepth(
                node.right,
                target,
                depth + 1);
    }

    static List<String> pathFromRoot(
            Node root,
            String target) {

        List<String> path = new ArrayList<>();

        if (findPath(root, target, path)) {
            return path;
        }

        return new ArrayList<>();
    }

    static boolean findPath(
            Node node,
            String target,
            List<String> path) {

        if (node == null || target == null) {
            return false;
        }

        path.add(node.name);

        if (target.equals(node.name)) {
            return true;
        }

        if (findPath(node.left, target, path)
                || findPath(node.right, target, path)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    static void printByLevel(Node root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }

        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        int level = 0;

        while (!queue.isEmpty()) {
            int count = queue.size();

            System.out.print(
                    "Level " + level + ": ");

            for (int i = 0; i < count; i++) {
                Node current = queue.poll();

                System.out.print(
                        current.name + " ");

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
        Node root = new Node("CEO");

        root.left = new Node("Engineering");
        root.right = new Node("Sales");

        root.left.left =
                new Node("Backend");

        root.left.right =
                new Node("Frontend");

        root.right.left =
                new Node("Domestic");

        root.right.right =
                new Node("International");

        root.left.left.left =
                new Node("Database");

        System.out.println("=== Parent ===");

        System.out.println(
                "Backend parent："
                + findParent(
                        root,
                        "Backend").name);

        System.out.println(
                "CEO parent："
                + findParent(root, "CEO"));

        System.out.println(
                "Unknown parent："
                + findParent(root, "Unknown"));

        System.out.println("\n=== Depth ===");

        System.out.println(
                "CEO depth："
                + findDepth(root, "CEO"));

        System.out.println(
                "Engineering depth："
                + findDepth(
                        root,
                        "Engineering"));

        System.out.println(
                "Database depth："
                + findDepth(root, "Database"));

        System.out.println(
                "Unknown depth："
                + findDepth(root, "Unknown"));

        System.out.println("\n=== Path ===");

        System.out.println(
                "Database path："
                + pathFromRoot(
                        root,
                        "Database"));

        System.out.println(
                "Sales path："
                + pathFromRoot(
                        root,
                        "Sales"));

        System.out.println(
                "Unknown path："
                + pathFromRoot(
                        root,
                        "Unknown"));

        System.out.println("\n=== Level Order ===");
        printByLevel(root);

        System.out.println("\n=== Empty Tree ===");
        printByLevel(null);
        System.out.println(
                "Empty path："
                + pathFromRoot(null, "CEO"));
    }
}
