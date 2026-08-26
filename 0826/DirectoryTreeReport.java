public class DirectoryTreeReport {

    static class Node {
        String name;
        boolean directory;
        int size;
        Node[] children;

        Node(String name, boolean directory, int size) {
            this.name = name;
            this.directory = directory;
            this.size = size;
        }

        Node(String name, boolean directory, int size, Node... children) {
            this.name = name;
            this.directory = directory;
            this.size = size;
            this.children = children;
        }
    }

    static int totalNodes(Node node) {
        if (node == null) {
            return 0;
        }

        int total = 1;

        if (node.children != null) {
            for (Node child : node.children) {
                total += totalNodes(child);
            }
        }

        return total;
    }

    static int fileCount(Node node) {
        if (node == null) {
            return 0;
        }

        if (!node.directory) {
            return 1;
        }

        int count = 0;

        if (node.children != null) {
            for (Node child : node.children) {
                count += fileCount(child);
            }
        }

        return count;
    }

    static int directoryCount(Node node) {
        if (node == null) {
            return 0;
        }

        int count = node.directory ? 1 : 0;

        if (node.children != null) {
            for (Node child : node.children) {
                count += directoryCount(child);
            }
        }

        return count;
    }

    static int height(Node node) {
        if (node == null) {
            return 0;
        }

        if (node.children == null
                || node.children.length == 0) {
            return 1;
        }

        int maxHeight = 0;

        for (Node child : node.children) {
            maxHeight = Math.max(
                    maxHeight,
                    height(child));
        }

        return maxHeight + 1;
    }

    static int calculateSize(Node node) {
        if (node == null) {
            return 0;
        }

        if (!node.directory) {
            return node.size;
        }

        int total = 0;

        if (node.children != null) {
            for (Node child : node.children) {
                total += calculateSize(child);
            }
        }

        node.size = total;
        return total;
    }

    static Node findLargestFile(Node node) {
        if (node == null) {
            return null;
        }

        Node largest = null;

        if (!node.directory) {
            largest = node;
        }

        if (node.children != null) {
            for (Node child : node.children) {
                Node candidate =
                        findLargestFile(child);

                if (candidate != null
                        && (largest == null
                        || candidate.size > largest.size)) {
                    largest = candidate;
                }
            }
        }

        return largest;
    }

    static void printTree(Node node, String indent) {
        if (node == null) {
            return;
        }

        System.out.println(
                indent
                + node.name
                + (node.directory ? "/" : "")
                + " size=" + node.size);

        if (node.children != null) {
            for (Node child : node.children) {
                printTree(child, indent + "  ");
            }
        }
    }

    public static void main(String[] args) {

        Node root = new Node(
                "project",
                true,
                0,
                new Node(
                        "src",
                        true,
                        0,
                        new Node(
                                "Main.java",
                                false,
                                1200),
                        new Node(
                                "Utils.java",
                                false,
                                800)),
                new Node(
                        "docs",
                        true,
                        0,
                        new Node(
                                "README.md",
                                false,
                                500),
                        new Node(
                                "report.pdf",
                                false,
                                3000)),
                new Node(
                        "data.csv",
                        false,
                        2500));

        int totalSize = calculateSize(root);

        Node largest =
                findLargestFile(root);

        System.out.println("=== Directory Tree ===");
        printTree(root, "");

        System.out.println();
        System.out.println(
                "total node: "
                + totalNodes(root));

        System.out.println(
                "file count: "
                + fileCount(root));

        System.out.println(
                "directory count: "
                + directoryCount(root));

        System.out.println(
                "height: "
                + height(root));

        System.out.println(
                "total size: "
                + totalSize);

        if (largest != null) {
            System.out.println(
                    "largest file: "
                    + largest.name
                    + " size="
                    + largest.size);
        }
    }
}