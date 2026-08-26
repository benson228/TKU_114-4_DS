public class BstShapeExperiment {

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

        void insert(int value) {
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

        int height() {
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

        int searchComparisons(int target) {
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

        int totalSearchComparisons(int[] values) {
            int total = 0;

            for (int value : values) {
                total += searchComparisons(value);
            }

            return total;
        }
    }

    static Bst buildTree(int[] values) {
        Bst tree = new Bst();

        for (int value : values) {
            tree.insert(value);
        }

        return tree;
    }

    static void printReport(
            String name,
            Bst tree,
            int[] values) {

        int total =
                tree.totalSearchComparisons(values);

        double average =
                (double) total / values.length;

        System.out.println("=== " + name + " ===");

        System.out.println(
                "height: " + tree.height());

        System.out.println(
                "total search comparisons: "
                + total);

        System.out.println(
                "average search comparisons: "
                + average);

        System.out.println();
    }

    public static void main(String[] args) {

        int[] values = {
                1, 2, 3, 4, 5,
                6, 7, 8, 9, 10,
                11, 12, 13, 14, 15
        };

        int[] sortedOrder = {
                1, 2, 3, 4, 5,
                6, 7, 8, 9, 10,
                11, 12, 13, 14, 15
        };

        int[] reverseOrder = {
                15, 14, 13, 12, 11,
                10, 9, 8, 7, 6,
                5, 4, 3, 2, 1
        };

        int[] balancedOrder = {
                8,
                4, 12,
                2, 6, 10, 14,
                1, 3, 5, 7,
                9, 11, 13, 15
        };

        Bst sortedTree =
                buildTree(sortedOrder);

        Bst reverseTree =
                buildTree(reverseOrder);

        Bst balancedTree =
                buildTree(balancedOrder);

        printReport(
                "Sorted Order",
                sortedTree,
                values);

        printReport(
                "Reverse Order",
                reverseTree,
                values);

        printReport(
                "Balanced Order",
                balancedTree,
                values);
    }
}