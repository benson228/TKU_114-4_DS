public class BinaryTreeStructureReport {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static class BinaryTree {
        private Node root;

        BinaryTree(Node root) {
            this.root = root;
        }

        int size() {
            return size(root);
        }

        private int size(Node node) {
            if (node == null) {
                return 0;
            }

            return 1 + size(node.left) + size(node.right);
        }

        int leafCount() {
            return leafCount(root);
        }

        private int leafCount(Node node) {
            if (node == null) {
                return 0;
            }

            if (node.left == null && node.right == null) {
                return 1;
            }

            return leafCount(node.left)
                    + leafCount(node.right);
        }

        int height() {
            return height(root);
        }

        private int height(Node node) {
            if (node == null) {
                return 0;
            }

            return 1 + Math.max(
                    height(node.left),
                    height(node.right)
            );
        }

        void printLeaves() {
            printLeaves(root);
            System.out.println();
        }

        private void printLeaves(Node node) {
            if (node == null) {
                return;
            }

            if (node.left == null && node.right == null) {
                System.out.print(node.value + " ");
                return;
            }

            printLeaves(node.left);
            printLeaves(node.right);
        }

        void printReport() {
            System.out.println("root："
                    + (root == null ? "null" : root.value));

            System.out.println("leaf：");
            printLeaves();

            System.out.println("size：" + size());
            System.out.println("leaf count："
                    + leafCount());
            System.out.println("height："
                    + height());
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);

        root.left = new Node(5);
        root.right = new Node(15);

        root.left.left = new Node(3);
        root.left.right = new Node(7);

        root.right.left = new Node(12);
        root.right.right = new Node(20);

        BinaryTree tree =
                new BinaryTree(root);

        System.out.println("=== 7 Node Tree ===");
        tree.printReport();

        System.out.println("\n=== Empty Tree ===");

        BinaryTree emptyTree =
                new BinaryTree(null);

        emptyTree.printReport();

        System.out.println("\n=== Single Node Tree ===");

        BinaryTree singleTree =
                new BinaryTree(new Node(100));

        singleTree.printReport();
    }
}
