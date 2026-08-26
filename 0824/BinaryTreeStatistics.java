public class BinaryTreeStatistics {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static int size(Node node) {
        if (node == null) {
            return 0;
        }

        return 1 + size(node.left) + size(node.right);
    }

    static int sum(Node node) {
        if (node == null) {
            return 0;
        }

        return node.value
                + sum(node.left)
                + sum(node.right);
    }

    static int maximum(Node node) {
        if (node == null) {
            throw new IllegalArgumentException(
                    "Tree is empty");
        }

        return maximumValue(node);
    }

    static int maximumValue(Node node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }

        int leftMaximum = maximumValue(node.left);
        int rightMaximum = maximumValue(node.right);

        return Math.max(
                node.value,
                Math.max(leftMaximum, rightMaximum));
    }

    static int leafCount(Node node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        return leafCount(node.left)
                + leafCount(node.right);
    }

    static int height(Node node) {
        if (node == null) {
            return 0;
        }

        return 1 + Math.max(
                height(node.left),
                height(node.right));
    }

    static boolean contains(Node node, int target) {
        if (node == null) {
            return false;
        }

        if (node.value == target) {
            return true;
        }

        return contains(node.left, target)
                || contains(node.right, target);
    }

    public static void main(String[] args) {
        Node root = new Node(10);

        root.left = new Node(5);
        root.right = new Node(20);

        root.left.left = new Node(3);
        root.left.right = new Node(7);

        root.right.left = new Node(15);
        root.right.right = new Node(25);

        System.out.println("=== Binary Tree ===");
        System.out.println("size：" + size(root));
        System.out.println("sum：" + sum(root));
        System.out.println("maximum：" + maximum(root));
        System.out.println("leaf count："
                + leafCount(root));
        System.out.println("height：" + height(root));

        System.out.println("contains 15："
                + contains(root, 15));

        System.out.println("contains 99："
                + contains(root, 99));

        System.out.println("\n=== Empty Tree ===");

        System.out.println("size：" + size(null));
        System.out.println("sum：" + sum(null));
        System.out.println("leaf count："
                + leafCount(null));
        System.out.println("height：" + height(null));

        try {
            System.out.println(
                    "maximum：" + maximum(null));
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "maximum：IllegalArgumentException");
        }
    }
}
