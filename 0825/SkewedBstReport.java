public class SkewedBstReport {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static class Bst {
        private Node root;

        public void insert(int value) {
            root = insert(root, value);
        }

        private Node insert(Node node, int value) {
            if (node == null) {
                return new Node(value);
            }

            if (value < node.value) {
                node.left = insert(node.left, value);
            } else if (value > node.value) {
                node.right = insert(node.right, value);
            }

            return node;
        }

        public int size() {
            return size(root);
        }

        private int size(Node node) {
            if (node == null) {
                return 0;
            }

            return 1
                    + size(node.left)
                    + size(node.right);
        }

        public int height() {
            return height(root);
        }

        private int height(Node node) {
            if (node == null) {
                return 0;
            }

            return 1 + Math.max(
                    height(node.left),
                    height(node.right));
        }

        public int searchComparisons(int target) {
            Node current = root;
            int comparisons = 0;

            while (current != null) {
                comparisons++;

                if (target == current.value) {
                    return comparisons;
                }

                if (target < current.value) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return comparisons;
        }
    }

    static void printReport(
            String name,
            Bst tree,
            int target) {

        System.out.println("=== " + name + " ===");

        System.out.println(
                "size: " + tree.size());

        System.out.println(
                "height: " + tree.height());

        System.out.println(
                "search " + target
                + " comparisons: "
                + tree.searchComparisons(target));

        System.out.println();
    }

    static void addBalancedOrder(
            Bst tree,
            int[] values,
            int left,
            int right) {

        if (left > right) {
            return;
        }

        int middle = (left + right) / 2;

        tree.insert(values[middle]);

        addBalancedOrder(
                tree,
                values,
                left,
                middle - 1);

        addBalancedOrder(
                tree,
                values,
                middle + 1,
                right);
    }

    public static void main(String[] args) {
        int[] values = {
                10, 20, 30, 40, 50,
                60, 70, 80, 90
        };

        Bst sortedTree = new Bst();

        for (int value : values) {
            sortedTree.insert(value);
        }

        Bst balancedTree = new Bst();

        addBalancedOrder(
                balancedTree,
                values,
                0,
                values.length - 1);

        printReport(
                "Sorted Insert",
                sortedTree,
                90);

        printReport(
                "Balanced Insert Order",
                balancedTree,
                90);
    }
}
