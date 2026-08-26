public class BstDeleteTestSuite {

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

        boolean insert(int value) {
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

        boolean delete(int value) {
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

        void printInorder() {
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

        boolean isEmpty() {
            return root == null;
        }
    }

    static void printState(
            String testName,
            Bst tree,
            boolean result) {

        System.out.println("=== " + testName + " ===");
        System.out.println("result: " + result);

        System.out.print("inorder: ");
        tree.printInorder();

        System.out.println("size: " + tree.size());
        System.out.println("empty: " + tree.isEmpty());
        System.out.println();
    }

    public static void main(String[] args) {

        System.out.println("=== Empty Tree ===");

        Bst empty = new Bst();

        printState(
                "delete from empty",
                empty,
                empty.delete(10));

        System.out.println("=== Missing Value ===");

        Bst missing = new Bst();
        missing.insert(50);
        missing.insert(30);
        missing.insert(70);

        printState(
                "delete missing 999",
                missing,
                missing.delete(999));

        System.out.println("=== Single Root ===");

        Bst single = new Bst();
        single.insert(50);

        printState(
                "delete root 50",
                single,
                single.delete(50));

        System.out.println("=== Root With One Child ===");

        Bst oneChild = new Bst();
        oneChild.insert(50);
        oneChild.insert(30);

        printState(
                "delete root 50",
                oneChild,
                oneChild.delete(50));

        System.out.println("=== Root With Two Children ===");

        Bst twoChildren = new Bst();
        twoChildren.insert(50);
        twoChildren.insert(30);
        twoChildren.insert(70);
        twoChildren.insert(60);
        twoChildren.insert(80);

        printState(
                "delete root 50",
                twoChildren,
                twoChildren.delete(50));

        System.out.println("=== Delete Until Empty ===");

        Bst allDelete = new Bst();

        int[] values = {
                50, 30, 70, 20, 40, 60, 80
        };

        for (int value : values) {
            allDelete.insert(value);
        }

        for (int value : values) {
            boolean result = allDelete.delete(value);

            System.out.println(
                    "delete " + value
                    + " -> " + result);

            System.out.print("inorder: ");
            allDelete.printInorder();

            System.out.println(
                    "size: " + allDelete.size());

            System.out.println(
                    "empty: " + allDelete.isEmpty());

            System.out.println();
        }
    }
}