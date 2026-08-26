public class BstRangeReport {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }

        if (value < root.value) {
            root.left = insert(root.left, value);
        } else if (value > root.value) {
            root.right = insert(root.right, value);
        }

        return root;
    }

    static int min(Node root) {
        if (root == null) {
            throw new IllegalArgumentException(
                    "Tree is empty");
        }

        Node current = root;

        while (current.left != null) {
            current = current.left;
        }

        return current.value;
    }

    static int max(Node root) {
        if (root == null) {
            throw new IllegalArgumentException(
                    "Tree is empty");
        }

        Node current = root;

        while (current.right != null) {
            current = current.right;
        }

        return current.value;
    }

    static void printRange(
            Node root,
            int low,
            int high) {

        if (root == null) {
            return;
        }

        if (low > high) {
            return;
        }

        if (root.value > low) {
            printRange(root.left, low, high);
        }

        if (root.value >= low
                && root.value <= high) {
            System.out.print(root.value + " ");
        }

        if (root.value < high) {
            printRange(root.right, low, high);
        }
    }

    static void printInorder(Node root) {
        if (root == null) {
            return;
        }

        printInorder(root.left);
        System.out.print(root.value + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        Node root = null;

        int[] values = {
                50, 30, 70, 20,
                40, 60, 80, 65
        };

        for (int value : values) {
            root = insert(root, value);
        }

        System.out.println("=== BST ===");
        printInorder(root);
        System.out.println();

        System.out.println("min：" + min(root));
        System.out.println("max：" + max(root));

        System.out.print("range [30, 65]：");
        printRange(root, 30, 65);
        System.out.println();

        System.out.print("range [50, 80]：");
        printRange(root, 50, 80);
        System.out.println();

        System.out.print("range [70, 30]：");
        printRange(root, 70, 30);
        System.out.println();

        System.out.println("\n=== Empty Tree ===");

        try {
            System.out.println("min：" + min(null));
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "min：IllegalArgumentException");
        }

        try {
            System.out.println("max：" + max(null));
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "max：IllegalArgumentException");
        }
    }
}
