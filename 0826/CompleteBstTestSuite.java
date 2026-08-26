public class CompleteBstTestSuite {

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

        boolean add(int value) {
            if (root == null) {
                root = new Node(value);
                return true;
            }

            Node current = root;

            while (true) {
                if (value == current.value) {
                    return false;
                }

                if (value < current.value) {
                    if (current.left == null) {
                        current.left = new Node(value);
                        return true;
                    }

                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new Node(value);
                        return true;
                    }

                    current = current.right;
                }
            }
        }

        boolean contains(int value) {
            Node current = root;

            while (current != null) {
                if (value == current.value) {
                    return true;
                }

                if (value < current.value) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return false;
        }

        boolean remove(int value) {
            if (!contains(value)) {
                return false;
            }

            root = remove(root, value);
            return true;
        }

        private Node remove(Node node, int value) {
            if (node == null) {
                return null;
            }

            if (value < node.value) {
                node.left = remove(node.left, value);
            } else if (value > node.value) {
                node.right = remove(node.right, value);
            } else {
                if (node.left == null) {
                    return node.right;
                }

                if (node.right == null) {
                    return node.left;
                }

                Node successor = findMin(node.right);

                node.value = successor.value;

                node.right =
                        remove(node.right, successor.value);
            }

            return node;
        }

        private Node findMin(Node node) {
            Node current = node;

            while (current.left != null) {
                current = current.left;
            }

            return current;
        }

        int size() {
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

        boolean isValid() {
            return isValid(
                    root,
                    Long.MIN_VALUE,
                    Long.MAX_VALUE);
        }

        private boolean isValid(
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

        int countBetween(int low, int high) {
            return countBetween(
                    root,
                    low,
                    high);
        }

        private int countBetween(
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

        int sumBetween(int low, int high) {
            return sumBetween(
                    root,
                    low,
                    high);
        }

        private int sumBetween(
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

        String inorder() {
            StringBuilder result =
                    new StringBuilder();

            inorder(root, result);

            return result.toString().trim();
        }

        private void inorder(
                Node node,
                StringBuilder result) {

            if (node == null) {
                return;
            }

            inorder(node.left, result);

            result.append(node.value)
                    .append(" ");

            inorder(node.right, result);
        }
    }

    static int passed = 0;
    static int failed = 0;

    static void check(
            String description,
            boolean condition) {

        if (condition) {
            System.out.println(
                    "PASS: " + description);
            passed++;
        } else {
            System.out.println(
                    "FAIL: " + description);
            failed++;
        }
    }

    public static void main(String[] args) {

        System.out.println(
                "=== Complete BST Test Suite ===");

        Bst empty = new Bst();

        check(
                "empty size",
                empty.size() == 0);

        check(
                "empty height",
                empty.height() == 0);

        check(
                "empty contains false",
                !empty.contains(10));

        check(
                "empty remove false",
                !empty.remove(10));

        check(
                "empty invariant",
                empty.isValid());

        Bst tree = new Bst();

        check(
                "add root",
                tree.add(50));

        check(
                "root contains",
                tree.contains(50));

        check(
                "root size",
                tree.size() == 1);

        check(
                "root height",
                tree.height() == 1);

        check(
                "duplicate rejected",
                !tree.add(50));

        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);

        check(
                "size after inserts",
                tree.size() == 7);

        check(
                "leaf exists",
                tree.contains(20));

        check(
                "internal node exists",
                tree.contains(30));

        check(
                "missing value",
                !tree.contains(999));

        check(
                "inorder sorted",
                tree.inorder().equals(
                        "20 30 40 50 60 70 80"));

        check(
                "height is correct",
                tree.height() == 3);

        check(
                "invariant after inserts",
                tree.isValid());

        check(
                "range count",
                tree.countBetween(30, 70) == 5);

        check(
                "range sum",
                tree.sumBetween(30, 70) == 250);

        check(
                "empty range count",
                tree.countBetween(100, 200) == 0);

        check(
                "invalid range count",
                tree.countBetween(80, 30) == 0);

        check(
                "remove missing",
                !tree.remove(999));

        check(
                "remove leaf",
                tree.remove(20));

        check(
                "leaf removed",
                !tree.contains(20));

        check(
                "size after leaf delete",
                tree.size() == 6);

        tree.add(65);

        check(
                "one-child node exists",
                tree.contains(60));

        check(
                "remove one-child node",
                tree.remove(60));

        check(
                "child preserved",
                tree.contains(65));

        check(
                "remove two-child node",
                tree.remove(70));

        check(
                "two-child node removed",
                !tree.contains(70));

        check(
                "remaining tree valid",
                tree.isValid());

        check(
                "remaining size",
                tree.size() == 5);

        check(
                "final inorder",
                tree.inorder().equals(
                        "30 40 50 65 80"));

        System.out.println();
        System.out.println(
                "Passed: " + passed);

        System.out.println(
                "Failed: " + failed);

        System.out.println(
                "Total: " + (passed + failed));

        System.out.println(
                "All tests passed: "
                + (failed == 0));
    }
}
