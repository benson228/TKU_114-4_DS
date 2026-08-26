public class BstInvariantChecker {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static boolean isValid(Node root) {
        return isValid(
                root,
                Long.MIN_VALUE,
                Long.MAX_VALUE);
    }

    static boolean isValid(
            Node node,
            long min,
            long max) {

        if (node == null) {
            return true;
        }

        if (node.value <= min
                || node.value >= max) {
            return false;
        }

        return isValid(
                node.left,
                min,
                node.value)
                && isValid(
                node.right,
                node.value,
                max);
    }

    static void printResult(
            String name,
            Node root) {

        System.out.println(
                name + ": " + isValid(root));
    }

    public static void main(String[] args) {

        Node valid = new Node(50);
        valid.left = new Node(30);
        valid.right = new Node(70);
        valid.left.left = new Node(20);
        valid.left.right = new Node(40);
        valid.right.left = new Node(60);
        valid.right.right = new Node(80);

        Node deepLeftViolation = new Node(50);
        deepLeftViolation.left = new Node(30);
        deepLeftViolation.right = new Node(70);
        deepLeftViolation.left.right = new Node(60);

        Node deepRightViolation = new Node(50);
        deepRightViolation.left = new Node(30);
        deepRightViolation.right = new Node(70);
        deepRightViolation.right.left = new Node(40);

        Node deepSubtreeViolation = new Node(50);
        deepSubtreeViolation.left = new Node(30);
        deepSubtreeViolation.right = new Node(70);
        deepSubtreeViolation.left.left = new Node(20);
        deepSubtreeViolation.left.right = new Node(45);
        deepSubtreeViolation.left.right.right =
                new Node(65);

        Node singleNode = new Node(100);

        System.out.println("=== BST Validation ===");

        printResult(
                "Valid tree",
                valid);

        printResult(
                "Deep left violation",
                deepLeftViolation);

        printResult(
                "Deep right violation",
                deepRightViolation);

        printResult(
                "Deep subtree violation",
                deepSubtreeViolation);

        printResult(
                "Single node",
                singleNode);

        printResult(
                "Empty tree",
                null);
    }
}