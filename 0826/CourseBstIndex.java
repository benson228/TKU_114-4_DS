public class CourseBstIndex {

    static class Course {
        String courseCode;
        String name;
        int credit;

        Course(String courseCode, String name, int credit) {
            this.courseCode = courseCode;
            this.name = name;
            this.credit = credit;
        }

        @Override
        public String toString() {
            return courseCode
                    + " " + name
                    + " credit=" + credit;
        }
    }

    static class Node {
        Course course;
        Node left;
        Node right;

        Node(Course course) {
            this.course = course;
        }
    }

    static class CourseBst {
        private Node root;

        boolean add(Course course) {
            if (course == null
                    || course.courseCode == null
                    || course.courseCode.isBlank()
                    || course.credit < 1
                    || course.credit > 6) {
                return false;
            }

            if (root == null) {
                root = new Node(course);
                return true;
            }

            Node current = root;

            while (true) {
                int comparison =
                        course.courseCode.compareTo(
                                current.course.courseCode);

                if (comparison == 0) {
                    return false;
                }

                if (comparison < 0) {
                    if (current.left == null) {
                        current.left = new Node(course);
                        return true;
                    }

                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new Node(course);
                        return true;
                    }

                    current = current.right;
                }
            }
        }

        Course find(String courseCode) {
            if (courseCode == null
                    || courseCode.isBlank()) {
                return null;
            }

            Node current = root;

            while (current != null) {
                int comparison =
                        courseCode.compareTo(
                                current.course.courseCode);

                if (comparison == 0) {
                    return current.course;
                }

                if (comparison < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return null;
        }

        boolean updateCredit(
                String courseCode,
                int newCredit) {

            if (newCredit < 1
                    || newCredit > 6) {
                return false;
            }

            Course course = find(courseCode);

            if (course == null) {
                return false;
            }

            course.credit = newCredit;
            return true;
        }

        boolean remove(String courseCode) {
            if (find(courseCode) == null) {
                return false;
            }

            root = remove(root, courseCode);
            return true;
        }

        private Node remove(
                Node node,
                String courseCode) {

            if (node == null) {
                return null;
            }

            int comparison =
                    courseCode.compareTo(
                            node.course.courseCode);

            if (comparison < 0) {
                node.left =
                        remove(node.left, courseCode);
            } else if (comparison > 0) {
                node.right =
                        remove(node.right, courseCode);
            } else {
                if (node.left == null) {
                    return node.right;
                }

                if (node.right == null) {
                    return node.left;
                }

                Node successor =
                        findMin(node.right);

                node.course = successor.course;

                node.right =
                        remove(
                                node.right,
                                successor.course.courseCode);
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

        void rangeQuery(
                String low,
                String high) {

            if (low == null
                    || high == null
                    || low.compareTo(high) > 0) {
                return;
            }

            rangeQuery(root, low, high);
        }

        private void rangeQuery(
                Node node,
                String low,
                String high) {

            if (node == null) {
                return;
            }

            String code =
                    node.course.courseCode;

            if (code.compareTo(low) > 0) {
                rangeQuery(
                        node.left,
                        low,
                        high);
            }

            if (code.compareTo(low) >= 0
                    && code.compareTo(high) <= 0) {
                System.out.println(node.course);
            }

            if (code.compareTo(high) < 0) {
                rangeQuery(
                        node.right,
                        low,
                        high);
            }
        }

        void printInorder() {
            printInorder(root);
        }

        private void printInorder(Node node) {
            if (node == null) {
                return;
            }

            printInorder(node.left);
            System.out.println(node.course);
            printInorder(node.right);
        }
    }

    public static void main(String[] args) {
        CourseBst bst = new CourseBst();

        System.out.println(
                "add CS101: "
                + bst.add(
                new Course(
                        "CS101",
                        "Programming",
                        3)));

        System.out.println(
                "add CS205: "
                + bst.add(
                new Course(
                        "CS205",
                        "Data Structures",
                        3)));

        System.out.println(
                "add CS150: "
                + bst.add(
                new Course(
                        "CS150",
                        "Database",
                        2)));

        System.out.println(
                "add CS310: "
                + bst.add(
                new Course(
                        "CS310",
                        "Algorithms",
                        4)));

        System.out.println(
                "add CS220: "
                + bst.add(
                new Course(
                        "CS220",
                        "Networks",
                        3)));

        System.out.println(
                "duplicate CS101: "
                + bst.add(
                new Course(
                        "CS101",
                        "Other",
                        5)));

        System.out.println(
                "invalid credit: "
                + bst.add(
                new Course(
                        "CS400",
                        "Invalid",
                        7)));

        System.out.println("\n=== Inorder Report ===");
        bst.printInorder();

        System.out.println("\n=== Find ===");
        System.out.println(
                "CS205 -> " + bst.find("CS205"));

        System.out.println(
                "CS999 -> " + bst.find("CS999"));

        System.out.println("\n=== Update Credit ===");
        System.out.println(
                "update CS205 -> 4: "
                + bst.updateCredit("CS205", 4));

        System.out.println(
                "update CS205 -> 8: "
                + bst.updateCredit("CS205", 8));

        System.out.println("\n=== Range Query ===");
        bst.rangeQuery("CS150", "CS310");

        System.out.println("\n=== Remove ===");
        System.out.println(
                "remove CS205: "
                + bst.remove("CS205"));

        System.out.println(
                "remove CS999: "
                + bst.remove("CS999"));

        System.out.println("\n=== Final Report ===");
        bst.printInorder();
    }
}
