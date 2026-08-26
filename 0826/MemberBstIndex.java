public class MemberBstIndex {

    static class Member {
        String memberId;
        String name;
        String email;

        Member(String memberId, String name, String email) {
            this.memberId = memberId;
            this.name = name;
            this.email = email;
        }

        @Override
        public String toString() {
            return memberId
                    + " " + name
                    + " email=" + email;
        }
    }

    static class Node {
        Member member;
        Node left;
        Node right;

        Node(Member member) {
            this.member = member;
        }
    }

    static class MemberBst {
        private Node root;

        boolean add(Member member) {
            if (member == null
                    || member.memberId == null
                    || member.memberId.isBlank()
                    || member.email == null
                    || member.email.isBlank()) {
                return false;
            }

            if (root == null) {
                root = new Node(member);
                return true;
            }

            Node current = root;

            while (true) {
                int comparison =
                        member.memberId.compareTo(
                                current.member.memberId);

                if (comparison == 0) {
                    return false;
                }

                if (comparison < 0) {
                    if (current.left == null) {
                        current.left = new Node(member);
                        return true;
                    }

                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new Node(member);
                        return true;
                    }

                    current = current.right;
                }
            }
        }

        Member find(String memberId) {
            if (memberId == null
                    || memberId.isBlank()) {
                return null;
            }

            Node current = root;

            while (current != null) {
                int comparison =
                        memberId.compareTo(
                                current.member.memberId);

                if (comparison == 0) {
                    return current.member;
                }

                if (comparison < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return null;
        }

        boolean updateEmail(
                String memberId,
                String newEmail) {

            if (newEmail == null
                    || newEmail.isBlank()) {
                return false;
            }

            Member member = find(memberId);

            if (member == null) {
                return false;
            }

            member.email = newEmail;
            return true;
        }

        boolean remove(String memberId) {
            if (find(memberId) == null) {
                return false;
            }

            root = remove(root, memberId);
            return true;
        }

        private Node remove(
                Node node,
                String memberId) {

            if (node == null) {
                return null;
            }

            int comparison =
                    memberId.compareTo(
                            node.member.memberId);

            if (comparison < 0) {
                node.left =
                        remove(node.left, memberId);
            } else if (comparison > 0) {
                node.right =
                        remove(node.right, memberId);
            } else {
                if (node.left == null) {
                    return node.right;
                }

                if (node.right == null) {
                    return node.left;
                }

                Node successor =
                        findMin(node.right);

                node.member = successor.member;

                node.right =
                        remove(
                                node.right,
                                successor.member.memberId);
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
        }

        private void printInorder(Node node) {
            if (node == null) {
                return;
            }

            printInorder(node.left);
            System.out.println(node.member);
            printInorder(node.right);
        }
    }

    public static void main(String[] args) {
        MemberBst bst = new MemberBst();

        System.out.println(
                "add M003: "
                + bst.add(
                new Member(
                        "M003",
                        "Amy",
                        "amy@example.com")));

        System.out.println(
                "add M001: "
                + bst.add(
                new Member(
                        "M001",
                        "Ben",
                        "ben@example.com")));

        System.out.println(
                "add M005: "
                + bst.add(
                new Member(
                        "M005",
                        "Cindy",
                        "cindy@example.com")));

        System.out.println(
                "add M002: "
                + bst.add(
                new Member(
                        "M002",
                        "David",
                        "david@example.com")));

        System.out.println(
                "add M004: "
                + bst.add(
                new Member(
                        "M004",
                        "Eva",
                        "eva@example.com")));

        System.out.println(
                "add duplicate M003: "
                + bst.add(
                new Member(
                        "M003",
                        "Other",
                        "other@example.com")));

        System.out.println(
                "add blank email: "
                + bst.add(
                new Member(
                        "M006",
                        "Frank",
                        "   ")));

        System.out.println("\n=== Inorder ===");
        bst.printInorder();

        System.out.println("\n=== Find ===");
        System.out.println(
                "M002 -> " + bst.find("M002"));

        System.out.println(
                "M999 -> " + bst.find("M999"));

        System.out.println("\n=== Update Email ===");

        System.out.println(
                "update M002: "
                + bst.updateEmail(
                "M002",
                "newdavid@example.com"));

        System.out.println(
                "update M999: "
                + bst.updateEmail(
                "M999",
                "unknown@example.com"));

        System.out.println(
                "update M002 blank: "
                + bst.updateEmail(
                "M002",
                "   "));

        System.out.println("\n=== Remove ===");

        System.out.println(
                "remove M003: "
                + bst.remove("M003"));

        System.out.println(
                "remove M999: "
                + bst.remove("M999"));

        System.out.println("\n=== Final Inorder ===");
        bst.printInorder();
    }
}
