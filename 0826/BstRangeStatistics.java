public class BstRangeStatistics {

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

        void valuesBetween(
                Node node,
                int low,
                int high,
                StringBuilder result) {

            if (node == null || low > high) {
                return;
            }

            if (node.value > low) {
                valuesBetween(
                        node.left,
                        low,
                        high,
                        result);
            }

            if (node.value >= low
                    && node.value <= high) {
                result.append(node.value)
                        .append(" ");
            }

            if (node.value < high) {
                valuesBetween(
                        node.right,
                        low,
                        high,
                        result);
            }
        }

        int countBetween(
                Node node,
                int low,
                int high) {

            if (node == null || low > high) {
                return 0;
            }

            int count = 0;

            if (node.value > low) {
                count += countBetween(
                        node.left,
                        low,
                        high);
            }

            if (node.value >= low
                    && node.value <= high) {
                count++;
            }

            if (node.value < high) {
                count += countBetween(
                        node.right,
                        low,
                        high);
            }

            return count;
        }

        int sumBetween(
                Node node,
                int low,
                int high) {

            if (node == null || low > high) {
                return 0;
            }

            int sum = 0;

            if (node.value > low) {
                sum += sumBetween(
                        node.left,
                        low,
                        high);
            }

            if (node.value >= low
                    && node.value <= high) {
                sum += node.value;
            }

            if (node.value < high) {
                sum += sumBetween(
                        node.right,
                        low,
                        high);
            }

            return sum;
        }

        void printStatistics(
                int low,
                int high) {

            System.out.println(
                    "range=[" + low + ", " + high + "]");

            if (low > high) {
                System.out.println(
                        "values=[]");
                System.out.println(
                        "count=0");
                System.out.println(
                        "sum=0");
                System.out.println();
                return;
            }

            StringBuilder result =
                    new StringBuilder();

            valuesBetween(
                    root,
                    low,
                    high,
                    result);

            System.out.println(
                    "values=["
                    + result.toString().trim()
                    + "]");

            System.out.println(
                    "count="
                    + countBetween(
                    root,
                    low,
                    high));

            System.out.println(
                    "sum="
                    + sumBetween(
                    root,
                    low,
                    high));

            System.out.println();
        }
    }

    public static void main(String[] args) {
        Bst tree = new Bst();

        int[] values = {
                50, 30, 70,
                20, 40, 60,
                80, 65
        };

        for (int value : values) {
            tree.insert(value);
        }

        System.out.println(
                "=== Range Statistics ===");

        tree.printStatistics(30, 65);
        tree.printStatistics(50, 80);

        System.out.println(
                "=== Empty Range ===");

        tree.printStatistics(100, 120);

        System.out.println(
                "=== Invalid Range ===");

        tree.printStatistics(70, 30);
    }
}