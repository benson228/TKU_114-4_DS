public class BstDuplicateCounter {

    static class Node {
        int key;
        int count;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
            this.count = 1;
        }
    }

    static Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }

        if (key < root.key) {
            root.left = insert(root.left, key);
        } else if (key > root.key) {
            root.right = insert(root.right, key);
        } else {
            root.count++;
        }

        return root;
    }

    static void inorder(Node node) {
        if (node == null) {
            return;
        }

        inorder(node.left);

        System.out.print(
                node.key + "(" + node.count + ") ");

        inorder(node.right);
    }

    static int totalCount(Node node) {
        if (node == null) {
            return 0;
        }

        return node.count
                + totalCount(node.left)
                + totalCount(node.right);
    }

    public static void main(String[] args) {
        Node root = null;

        int[] values = {
                50, 30, 70, 30,
                50, 50, 20, 70,
                80, 30
        };

        for (int value : values) {
            root = insert(root, value);
        }

        System.out.println("=== Inorder ===");
        inorder(root);
        System.out.println();

        System.out.println(
                "Total values: "
                + totalCount(root));

        System.out.println(
                "30 count: "
                + findCount(root, 30));

        System.out.println(
                "50 count: "
                + findCount(root, 50));

        System.out.println(
                "70 count: "
                + findCount(root, 70));
    }

    static int findCount(Node root, int key) {
        Node current = root;

        while (current != null) {
            if (key == current.key) {
                return current.count;
            }

            if (key < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return 0;
    }
}
