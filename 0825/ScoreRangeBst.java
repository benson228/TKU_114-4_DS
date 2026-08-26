public class ScoreRangeBst {

    static class Student {
        String studentId;
        String name;
        int score;

        Student(String studentId, String name, int score) {
            this.studentId = studentId;
            this.name = name;
            this.score = score;
        }

        @Override
        public String toString() {
            return studentId + " " + name + " score=" + score;
        }
    }

    static class Node {
        Student student;
        Node left;
        Node right;

        Node(Student student) {
            this.student = student;
        }
    }

    static class ScoreBst {

        private Node root;

        boolean add(Student student) {
            if (student == null
                    || student.studentId == null) {
                return false;
            }

            if (root == null) {
                root = new Node(student);
                return true;
            }

            Node current = root;

            while (true) {
                int comparison =
                        compare(student, current.student);

                if (comparison == 0) {
                    return false;
                }

                if (comparison < 0) {
                    if (current.left == null) {
                        current.left = new Node(student);
                        return true;
                    }

                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new Node(student);
                        return true;
                    }

                    current = current.right;
                }
            }
        }

        int compare(Student a, Student b) {
            if (a.score != b.score) {
                return Integer.compare(
                        a.score,
                        b.score);
            }

            return a.studentId.compareTo(
                    b.studentId);
        }

        void printRange(int low, int high) {
            if (low > high) {
                return;
            }

            printRange(root, low, high);
            System.out.println();
        }

        private void printRange(
                Node node,
                int low,
                int high) {

            if (node == null) {
                return;
            }

            if (node.student.score > low) {
                printRange(
                        node.left,
                        low,
                        high);
            }

            if (node.student.score >= low
                    && node.student.score <= high) {
                System.out.println(node.student);
            }

            if (node.student.score < high) {
                printRange(
                        node.right,
                        low,
                        high);
            }
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
            System.out.println(node.student);
            printInorder(node.right);
        }
    }

    public static void main(String[] args) {
        ScoreBst bst = new ScoreBst();

        bst.add(new Student(
                "S003", "Amy", 85));

        bst.add(new Student(
                "S001", "Ben", 90));

        bst.add(new Student(
                "S005", "Cindy", 85));

        bst.add(new Student(
                "S002", "David", 75));

        bst.add(new Student(
                "S004", "Eva", 90));

        bst.add(new Student(
                "S006", "Frank", 60));

        System.out.println("=== Inorder ===");
        bst.printInorder();

        System.out.println("=== Score Range [80, 90] ===");
        bst.printRange(80, 90);

        System.out.println("=== Score Range [90, 90] ===");
        bst.printRange(90, 90);

        System.out.println("=== Invalid Range [95, 80] ===");
        bst.printRange(95, 80);
    }
}
