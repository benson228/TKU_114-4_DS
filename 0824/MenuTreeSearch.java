public class MenuTreeSearch {

    static class Node {
        String name;
        Node left;
        Node right;

        Node(String name) {
            this.name = name;
        }
    }

    static boolean contains(Node node, String target) {
        if (node == null || target == null) {
            return false;
        }

        if (target.equals(node.name)) {
            return true;
        }

        return contains(node.left, target)
                || contains(node.right, target);
    }

    static int findDepth(Node node, String target) {
        return findDepth(node, target, 0);
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
                findDepth(node.left, target, depth + 1);

        if (leftDepth != -1) {
            return leftDepth;
        }

        return findDepth(
                node.right,
                target,
                depth + 1);
    }

    static int countLeaves(Node node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        return countLeaves(node.left)
                + countLeaves(node.right);
    }

    static void preorderDisplay(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.name + " ");
        preorderDisplay(node.left);
        preorderDisplay(node.right);
    }

    public static void main(String[] args) {
        Node root = new Node("Home");

        root.left = new Node("Products");
        root.right = new Node("Account");

        root.left.left = new Node("Laptop");
        root.left.right = new Node("Phone");

        root.right.left = new Node("Profile");
        root.right.right = new Node("Settings");

        root.left.left.left =
                new Node("Gaming Laptop");

        System.out.println("=== Preorder ===");
        preorderDisplay(root);
        System.out.println();

        System.out.println("\n=== Contains ===");
        System.out.println(
                "Phone：" +
                contains(root, "Phone"));

        System.out.println(
                "Camera：" +
                contains(root, "Camera"));

        System.out.println("\n=== Depth ===");
        System.out.println(
                "Home：" +
                findDepth(root, "Home"));

        System.out.println(
                "Products：" +
                findDepth(root, "Products"));

        System.out.println(
                "Gaming Laptop：" +
                findDepth(root, "Gaming Laptop"));

        System.out.println(
                "Camera：" +
                findDepth(root, "Camera"));

        System.out.println("\n=== Leaf Count ===");
        System.out.println(
                "Leaves：" +
                countLeaves(root));
    }
}
