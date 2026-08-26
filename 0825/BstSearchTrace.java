public class BstSearchTrace {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static class SearchResult {
        boolean found;
        int comparisons;

        SearchResult(boolean found, int comparisons) {
            this.found = found;
            this.comparisons = comparisons;
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

    static SearchResult search(Node root, int target) {
        int comparisons = 0;
        Node current = root;

        while (current != null) {
            comparisons++;

            System.out.println(
                    "compare " + comparisons
                    + ": current=" + current.value);

            if (target == current.value) {
                System.out.println("direction: found");
                return new SearchResult(
                        true, comparisons);
            }

            if (target < current.value) {
                System.out.println("direction: left");
                current = current.left;
            } else {
                System.out.println("direction: right");
                current = current.right;
            }
        }

        System.out.println("direction: missing");

        return new SearchResult(
                false, comparisons);
    }

    static void testSearch(Node root, int target) {
        System.out.println(
                "\n=== Search " + target + " ===");

        SearchResult result =
                search(root, target);

        System.out.println(
                "found=" + result.found);

        System.out.println(
                "comparison count="
                + result.comparisons);
    }

    public static void main(String[] args) {
        Node root = null;

        int[] values = {
                50, 30, 70, 20, 40,
                60, 80, 65
        };

        for (int value : values) {
            root = insert(root, value);
        }

        testSearch(root, 50);
        testSearch(root, 20);
        testSearch(root, 60);
        testSearch(root, 65);
        testSearch(root, 999);
    }
}
