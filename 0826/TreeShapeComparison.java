public class TreeShapeComparison {

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
            if (root == null) {
                root = new Node(value);
                return;
            }

            Node current = root;

            while (true) {
                if (value < current.value) {
                    if (current.left == null) {
                        current.left = new Node(value);
                        return;
                    }

                    current = current.left;
                } else if (value > current.value) {
                    if (current.right == null) {
                        current.right = new Node(value);
                        return;
                    }

                    current = current.right;
                } else {
                    return;
                }
            }
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

    static Bst buildTree(int[] order) {
        Bst tree = new Bst();

        for (int value : order) {
            tree.insert(value);
        }

        return tree;
    }

    static void printReport(
            String name,
            Bst tree,
            int[] values,
            int missingKey) {

        int total =
                tree.totalSearchComparisons(values);

        int missingComparisons =
                tree.searchComparisons(missingKey);

        System.out.println("=== " + name + " ===");

        System.out.println(
                "height: " + tree.height());

        System.out.println(
                "search comparison total: "
                + total);

        System.out.println(
                "missing key: " + missingKey);

        System.out.println(
                "missing comparison count: "
                + missingComparisons);

        System.out.println();
    }

    public static void main(String[] args) {

        int[] values = {
                1, 2, 3, 4, 5,
                6, 7, 8, 9, 10,
                11, 12, 13, 14, 15
        };

        int[] ascending = {
                1, 2, 3, 4, 5,
                6, 7, 8, 9, 10,
                11, 12, 13, 14, 15
        };

        int[] descending = {
                15, 14, 13, 12, 11,
                10, 9, 8, 7, 6,
                5, 4, 3, 2, 1
        };

        int[] balanced = {
                8,
                4, 12,
                2, 6, 10, 14,
                1, 3, 5, 7,
                9, 11, 13, 15
        };

        Bst ascendingTree =
                buildTree(ascending);

        Bst descendingTree =
                buildTree(descending);

        Bst balancedTree =
                buildTree(balanced);

        System.out.println(
                "BST Tree Shape Comparison");

        System.out.println();

        printReport(
                "Ascending Order",
                ascendingTree,
                values,
                100);

        printReport(
                "Descending Order",
                descendingTree,
                values,
                100);

        printReport(
                "Near Balanced Order",
                balancedTree,
                values,
                100);
    }
}
