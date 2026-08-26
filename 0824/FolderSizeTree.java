import java.util.ArrayList;
import java.util.List;

public class FolderSizeTree {

    static class FolderNode {
        String name;
        int ownSize;
        FolderNode left;
        FolderNode right;

        FolderNode(String name, int ownSize) {
            this.name = name;
            this.ownSize = ownSize;
        }
    }

    static class FolderReport {
        String name;
        int subtreeSize;

        FolderReport(String name, int subtreeSize) {
            this.name = name;
            this.subtreeSize = subtreeSize;
        }

        @Override
        public String toString() {
            return name + "=" + subtreeSize;
        }
    }

    static int calculateSubtreeSize(FolderNode node) {
        if (node == null) {
            return 0;
        }

        int leftSize = calculateSubtreeSize(node.left);
        int rightSize = calculateSubtreeSize(node.right);

        return node.ownSize + leftSize + rightSize;
    }

    static void collectReports(
            FolderNode node,
            List<FolderReport> reports) {

        if (node == null) {
            return;
        }

        collectReports(node.left, reports);
        collectReports(node.right, reports);

        int total = calculateSubtreeSize(node);
        reports.add(new FolderReport(node.name, total));
    }

    static void collectLeaves(
            FolderNode node,
            List<String> leaves) {

        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            leaves.add(node.name);
            return;
        }

        collectLeaves(node.left, leaves);
        collectLeaves(node.right, leaves);
    }

    static FolderReport findMaximumSubtree(
            List<FolderReport> reports) {

        if (reports.isEmpty()) {
            return null;
        }

        FolderReport maximum = reports.get(0);

        for (FolderReport report : reports) {
            if (report.subtreeSize > maximum.subtreeSize) {
                maximum = report;
            }
        }

        return maximum;
    }

    public static void main(String[] args) {
        FolderNode root =
                new FolderNode("Root", 100);

        root.left =
                new FolderNode("Documents", 50);

        root.right =
                new FolderNode("Pictures", 80);

        root.left.left =
                new FolderNode("Homework", 120);

        root.left.right =
                new FolderNode("Reports", 70);

        root.right.left =
                new FolderNode("Photos", 200);

        root.right.right =
                new FolderNode("Wallpapers", 150);

        List<FolderReport> reports =
                new ArrayList<>();

        collectReports(root, reports);

        List<String> leaves =
                new ArrayList<>();

        collectLeaves(root, leaves);

        int totalSize =
                calculateSubtreeSize(root);

        FolderReport maximum =
                findMaximumSubtree(reports);

        System.out.println("=== Folder Size Report ===");

        System.out.println(
                "Total size：" + totalSize);

        System.out.println(
                "Subtree sizes：" + reports);

        System.out.println(
                "Maximum subtree：" + maximum);

        System.out.println(
                "Leaf folders：" + leaves);
    }
}