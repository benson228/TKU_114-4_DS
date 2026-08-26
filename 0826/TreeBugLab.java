public class TreeBugLab {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static Node buildSampleTree() {
        Node root = new Node(50);

        root.left = new Node(30);
        root.right = new Node(70);

        root.left.left = new Node(20);
        root.left.right = new Node(40);

        root.right.left = new Node(60);
        root.right.right = new Node(80);

        return root;
    }

    static Node buildDeepViolationTree() {
        Node root = new Node(50);

        root.left = new Node(30);
        root.right = new Node(70);

        root.left.right = new Node(60);

        return root;
    }

    static boolean buggySearch(Node root, int target) {
        Node current = root;

        while (current != null) {
            if (target == current.value) {
                return true;
            }

            if (target < current.value) {
                current = current.right;
            } else {
                current = current.left;
            }
        }

        return false;
    }

    static boolean correctSearch(Node root, int target) {
        Node current = root;

        while (current != null) {
            if (target == current.value) {
                return true;
            }

            if (target < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    static void buggyInorder(
            Node node,
            StringBuilder result) {

        if (node == null) {
            return;
        }

        buggyInorder(node.right, result);

        result.append(node.value)
                .append(" ");

        buggyInorder(node.left, result);
    }

    static void correctInorder(
            Node node,
            StringBuilder result) {

        if (node == null) {
            return;
        }

        correctInorder(node.left, result);

        result.append(node.value)
                .append(" ");

        correctInorder(node.right, result);
    }

    static Node buggyDelete(Node node, int target) {
        if (node == null) {
            return null;
        }

        if (target < node.value) {
            node.left = buggyDelete(
                    node.left,
                    target);
        } else if (target > node.value) {
            node.right = buggyDelete(
                    node.right,
                    target);
        } else {
            if (node.left == null) {
                return null;
            }

            if (node.right == null) {
                return null;
            }

            Node successor =
                    findMin(node.right);

            node.value = successor.value;

            node.right = buggyDelete(
                    node.right,
                    successor.value);
        }

        return node;
    }

    static Node correctDelete(Node node, int target) {
        if (node == null) {
            return null;
        }

        if (target < node.value) {
            node.left = correctDelete(
                    node.left,
                    target);
        } else if (target > node.value) {
            node.right = correctDelete(
                    node.right,
                    target);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor =
                    findMin(node.right);

            node.value = successor.value;

            node.right = correctDelete(
                    node.right,
                    successor.value);
        }

        return node;
    }

    static Node findMin(Node node) {
        Node current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    static boolean buggyValidation(Node node) {
        if (node == null) {
            return true;
        }

        if (node.left != null
                && node.left.value >= node.value) {
            return false;
        }

        if (node.right != null
                && node.right.value <= node.value) {
            return false;
        }

        return buggyValidation(node.left)
                && buggyValidation(node.right);
    }

    static boolean correctValidation(Node node) {
        return correctValidation(
                node,
                Long.MIN_VALUE,
                Long.MAX_VALUE);
    }

    static boolean correctValidation(
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

        return correctValidation(
                node.left,
                min,
                node.value)
                && correctValidation(
                node.right,
                node.value,
                max);
    }

    static String inorder(Node root) {
        StringBuilder result =
                new StringBuilder();

        correctInorder(root, result);

        return result.toString().trim();
    }

    public static void main(String[] args) {

        System.out.println(
                "=== 1. Search Direction Bug ===");

        Node searchTree =
                buildSampleTree();

        System.out.println(
                "buggy search 20: "
                + buggySearch(searchTree, 20));

        System.out.println(
                "correct search 20: "
                + correctSearch(searchTree, 20));

        System.out.println();

        System.out.println(
                "=== 2. Inorder Order Bug ===");

        Node inorderTree =
                buildSampleTree();

        StringBuilder buggyResult =
                new StringBuilder();

        buggyInorder(
                inorderTree,
                buggyResult);

        System.out.println(
                "buggy inorder: "
                + buggyResult.toString().trim());

        System.out.println(
                "correct inorder: "
                + inorder(inorderTree));

        System.out.println();

        System.out.println(
                "=== 3. Delete Child Bug ===");

        Node deleteTree =
                buildSampleTree();

        deleteTree =
                buggyDelete(
                        deleteTree,
                        30);

        System.out.println(
                "buggy delete 30: "
                + inorder(deleteTree));

        deleteTree =
                buildSampleTree();

        deleteTree =
                correctDelete(
                        deleteTree,
                        30);

        System.out.println(
                "correct delete 30: "
                + inorder(deleteTree));

        System.out.println();

        System.out.println(
                "=== 4. Validation Deep Bug ===");

        Node invalidTree =
                buildDeepViolationTree();

        System.out.println(
                "buggy validation: "
                + buggyValidation(invalidTree));

        System.out.println(
                "correct validation: "
                + correctValidation(invalidTree));
    }
}