public class BstDeleteCases {

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

        public boolean delete(int value) {
            if (!contains(value)) {
                return false;
            }

            root = delete(root, value);
            return true;
        }

        private Node delete(Node node, int value) {
            if (node == null) {
                return null;
            }

            if (value < node.value) {
                node.left = delete(node.left, value);
            } else if (value > node.value) {
                node.right = delete(node.right, value);
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
                        delete(node.right, successor.value);
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

        public boolean contains(int value) {
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

        public void printInorder() {
            printInorder(root);
            System.out.println();
        }

        private void printInorder(Node node) {
            if (node == null) {
                return;
            }

            printInorder(node.left);
            System.out.print(node.value + " ");
            printInorder(node.right);
        }

        public boolean isValid() {
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
    }

    static void printResult(
            Bst tree,
            String operation,
            boolean result) {

        System.out.println(
                operation + " result=" + result);

        System.out.print("inorder: ");
        tree.printInorder();

        System.out.println(
                "size: " + tree.size());

        System.out.println(
                "valid: " + tree.isValid());

        System.out.println();
    }

    public static void main(String[] args) {
        Bst tree = new Bst();

        int[] values = {
                50, 30, 70,
                20, 40, 60, 80,
                65
        };

        for (int value : values) {
            tree.insert(value);
        }

        System.out.println("=== Original ===");
        tree.printInorder();
        System.out.println("size: " + tree.size());
        System.out.println("valid: " + tree.isValid());
        System.out.println();

        System.out.println("=== Delete Leaf ===");
        printResult(
                tree,
                "delete 20",
                tree.delete(20));

        System.out.println("=== Delete Single Child ===");
        printResult(
                tree,
                "delete 60",
                tree.delete(60));

        System.out.println("=== Delete Two Children ===");
        printResult(
                tree,
                "delete 70",
                tree.delete(70));

        System.out.println("=== Delete Missing ===");
        printResult(
                tree,
                "delete 999",
                tree.delete(999));
    }
}