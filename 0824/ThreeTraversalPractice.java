public class ThreeTraversalPractice {

    static class Node {
        String value;
        Node left;
        Node right;

        Node(String value) {
            this.value = value;
        }
    }

    static void preorder(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.value + " ");
        preorder(node.left);
        preorder(node.right);
    }

    static void inorder(Node node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }

    static void postorder(Node node) {
        if (node == null) {
            return;
        }

        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value + " ");
    }

    public static void main(String[] args) {
        Node root = new Node("M");

        root.left = new Node("F");
        root.left.left = new Node("B");

        root.right = new Node("T");
        root.right.left = new Node("R");
        root.right.right = new Node("Z");

        System.out.println("=== Preorder ===");
        preorder(root);
        System.out.println();

        System.out.println("=== Inorder ===");
        inorder(root);
        System.out.println();

        System.out.println("=== Postorder ===");
        postorder(root);
        System.out.println();

        System.out.println("=== Null Tree ===");
        preorder(null);
        System.out.println();
    }
}