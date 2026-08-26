public class StudentBstIndex {

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
            return studentId
                    + " " + name
                    + " score=" + score;
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

    static class StudentBst {
        private Node root;

        boolean insert(Student student) {
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
                        student.studentId.compareTo(
                                current.student.studentId);

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

        Student search(String studentId) {
            Node current = root;

            while (current != null) {
                int comparison =
                        studentId.compareTo(
                                current.student.studentId);

                if (comparison == 0) {
                    return current.student;
                }

                if (comparison < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return null;
        }

        boolean delete(String studentId) {
            if (studentId == null
                    || search(studentId) == null) {
                return false;
            }

            root = delete(root, studentId);
            return true;
        }

        private Node delete(
                Node node,
                String studentId) {

            if (node == null) {
                return null;
            }

            int comparison =
                    studentId.compareTo(
                            node.student.studentId);

            if (comparison < 0) {
                node.left =
                        delete(node.left, studentId);
            } else if (comparison > 0) {
                node.right =
                        delete(node.right, studentId);
            } else {
                if (node.left == null) {
                    return node.right;
                }

                if (node.right == null) {
                    return node.left;
                }

                Node successor =
                        findMin(node.right);

                node.student = successor.student;

                node.right =
                        delete(
                                node.right,
                                successor.student.studentId);
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
        StudentBst bst = new StudentBst();

        System.out.println(
                "insert S003: "
                + bst.insert(
                new Student("S003", "Amy", 85)));

        System.out.println(
                "insert S001: "
                + bst.insert(
                new Student("S001", "Ben", 90)));

        System.out.println(
                "insert S005: "
                + bst.insert(
                new Student("S005", "Cindy", 78)));

        System.out.println(
                "insert S002: "
                + bst.insert(
                new Student("S002", "David", 88)));

        System.out.println(
                "insert duplicate S003: "
                + bst.insert(
                new Student("S003", "Other", 100)));

        System.out.println("\n=== Inorder ===");
        bst.printInorder();

        System.out.println("\n=== Search ===");
        System.out.println(
                "S002 -> " + bst.search("S002"));

        System.out.println(
                "S999 -> " + bst.search("S999"));

        System.out.println("\n=== Delete ===");

        System.out.println(
                "delete S002: "
                + bst.delete("S002"));

        System.out.println(
                "delete S999: "
                + bst.delete("S999"));

        bst.printInorder();
    }
}