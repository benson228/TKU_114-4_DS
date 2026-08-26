import java.util.ArrayList;
import java.util.List;

public class TraversalTestReport {

    static class Node {
        String value;
        Node left;
        Node right;

        Node(String value) {
            this.value = value;
        }
    }

    static List<String> preorder(Node node) {
        List<String> result = new ArrayList<>();
        preorder(node, result);
        return result;
    }

    static void preorder(
            Node node,
            List<String> result) {

        if (node == null) {
            return;
        }

        result.add(node.value);
        preorder(node.left, result);
        preorder(node.right, result);
    }

    static List<String> inorder(Node node) {
        List<String> result = new ArrayList<>();
        inorder(node, result);
        return result;
    }

    static void inorder(
            Node node,
            List<String> result) {

        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.value);
        inorder(node.right, result);
    }

    static List<String> postorder(Node node) {
        List<String> result = new ArrayList<>();
        postorder(node, result);
        return result;
    }

    static void postorder(
            Node node,
            List<String> result) {

        if (node == null) {
            return;
        }

        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.value);
    }

    static List<String> levelOrder(Node node) {
        List<String> result = new ArrayList<>();

        if (node == null) {
            return result;
        }

        List<Node> queue = new ArrayList<>();
        queue.add(node);

        int index = 0;

        while (index < queue.size()) {
            Node current = queue.get(index);
            index++;

            result.add(current.value);

            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }

        return result;
    }

    static void testTree(
            String name,
            Node root,
            List<String> expectedPreorder,
            List<String> expectedInorder,
            List<String> expectedPostorder,
            List<String> expectedLevelOrder) {

        List<String> actualPreorder =
                preorder(root);

        List<String> actualInorder =
                inorder(root);

        List<String> actualPostorder =
                postorder(root);

        List<String> actualLevelOrder =
                levelOrder(root);

        boolean preorderSame =
                expectedPreorder.equals(actualPreorder);

        boolean inorderSame =
                expectedInorder.equals(actualInorder);

        boolean postorderSame =
                expectedPostorder.equals(actualPostorder);

        boolean levelOrderSame =
                expectedLevelOrder.equals(actualLevelOrder);

        System.out.println("=== " + name + " ===");

        printResult(
                "Preorder",
                expectedPreorder,
                actualPreorder,
                preorderSame);

        printResult(
                "Inorder",
                expectedInorder,
                actualInorder,
                inorderSame);

        printResult(
                "Postorder",
                expectedPostorder,
                actualPostorder,
                postorderSame);

        printResult(
                "Level-order",
                expectedLevelOrder,
                actualLevelOrder,
                levelOrderSame);

        System.out.println();
    }

    static void printResult(
            String name,
            List<String> expected,
            List<String> actual,
            boolean same) {

        System.out.println(name);
        System.out.println("預期：" + expected);
        System.out.println("實際：" + actual);
        System.out.println("相同：" + same);
    }

    public static void main(String[] args) {

        Node empty = null;

        testTree(
                "Empty",
                empty,
                List.of(),
                List.of(),
                List.of(),
                List.of());

        Node single = new Node("A");

        testTree(
                "Single Node",
                single,
                List.of("A"),
                List.of("A"),
                List.of("A"),
                List.of("A"));

        Node onlyLeft = new Node("A");
        onlyLeft.left = new Node("B");
        onlyLeft.left.left = new Node("C");

        testTree(
                "Only Left",
                onlyLeft,
                List.of("A", "B", "C"),
                List.of("C", "B", "A"),
                List.of("C", "B", "A"),
                List.of("A", "B", "C"));

        Node onlyRight = new Node("A");
        onlyRight.right = new Node("B");
        onlyRight.right.right = new Node("C");

        testTree(
                "Only Right",
                onlyRight,
                List.of("A", "B", "C"),
                List.of("A", "B", "C"),
                List.of("C", "B", "A"),
                List.of("A", "B", "C"));

        Node complete = new Node("A");
        complete.left = new Node("B");
        complete.right = new Node("C");
        complete.left.left = new Node("D");
        complete.left.right = new Node("E");
        complete.right.left = new Node("F");
        complete.right.right = new Node("G");

        testTree(
                "Complete",
                complete,
                List.of("A", "B", "D", "E", "C", "F", "G"),
                List.of("D", "B", "E", "A", "F", "C", "G"),
                List.of("D", "E", "B", "F", "G", "C", "A"),
                List.of("A", "B", "C", "D", "E", "F", "G"));

        Node irregular = new Node("A");
        irregular.left = new Node("B");
        irregular.right = new Node("C");
        irregular.left.right = new Node("D");
        irregular.right.left = new Node("E");
        irregular.right.left.right = new Node("F");

        testTree(
                "Irregular",
                irregular,
                List.of("A", "B", "D", "C", "E", "F"),
                List.of("B", "D", "A", "E", "F", "C"),
                List.of("D", "B", "F", "E", "C", "A"),
                List.of("A", "B", "C", "D", "E", "F"));
    }
}
