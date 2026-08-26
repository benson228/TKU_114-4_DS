public class TraversalSelector {

    static class Node {
        String value;
        Node left;
        Node right;

        Node(String value) {
            this.value = value;
        }
    }

    static String preorder(Node node) {
        if (node == null) {
            return "";
        }

        return node.value
                + " "
                + preorder(node.left)
                + preorder(node.right);
    }

    static String inorder(Node node) {
        if (node == null) {
            return "";
        }

        if (node.left == null && node.right == null) {
            return node.value;
        }

        return "("
                + inorder(node.left)
                + " " + node.value + " "
                + inorder(node.right)
                + ")";
    }

    static String postorder(Node node) {
        if (node == null) {
            return "";
        }

        return postorder(node.left)
                + postorder(node.right)
                + node.value + " ";
    }

    static void printResult(
            String name,
            String result) {

        System.out.println(
                name + ": " + result.trim());
    }

    public static void main(String[] args) {

        Node root = new Node("*");

        root.left = new Node("+");
        root.right = new Node("-");

        root.left.left = new Node("A");
        root.left.right = new Node("B");

        root.right.left = new Node("C");
        root.right.right = new Node("D");

        System.out.println("=== Expression Tree ===");

        printResult(
                "Prefix",
                preorder(root));

        printResult(
                "Infix",
                inorder(root));

        printResult(
                "Postfix",
                postorder(root));
    }
}
