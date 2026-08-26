public class OrderBstSystem {

    static class Order {
        String orderId;
        String customer;
        int amount;

        Order(String orderId, String customer, int amount) {
            this.orderId = orderId;
            this.customer = customer;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return orderId
                    + " " + customer
                    + " amount=" + amount;
        }
    }

    static class Node {
        Order order;
        Node left;
        Node right;

        Node(Order order) {
            this.order = order;
        }
    }

    static class OrderBst {
        private Node root;

        boolean add(Order order) {
            if (order == null || order.orderId == null) {
                return false;
            }

            if (root == null) {
                root = new Node(order);
                return true;
            }

            Node current = root;

            while (true) {
                int comparison =
                        order.orderId.compareTo(
                                current.order.orderId);

                if (comparison == 0) {
                    return false;
                }

                if (comparison < 0) {
                    if (current.left == null) {
                        current.left = new Node(order);
                        return true;
                    }

                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new Node(order);
                        return true;
                    }

                    current = current.right;
                }
            }
        }

        Order find(String orderId) {
            if (orderId == null) {
                return null;
            }

            Node current = root;

            while (current != null) {
                int comparison =
                        orderId.compareTo(
                                current.order.orderId);

                if (comparison == 0) {
                    return current.order;
                }

                if (comparison < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return null;
        }

        boolean cancel(String orderId) {
            if (find(orderId) == null) {
                return false;
            }

            root = delete(root, orderId);
            return true;
        }

        private Node delete(Node node, String orderId) {
            if (node == null) {
                return null;
            }

            int comparison =
                    orderId.compareTo(
                            node.order.orderId);

            if (comparison < 0) {
                node.left =
                        delete(node.left, orderId);
            } else if (comparison > 0) {
                node.right =
                        delete(node.right, orderId);
            } else {
                if (node.left == null) {
                    return node.right;
                }

                if (node.right == null) {
                    return node.left;
                }

                Node successor =
                        findMin(node.right);

                node.order = successor.order;

                node.right =
                        delete(
                                node.right,
                                successor.order.orderId);
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

        boolean updateAmount(
                String orderId,
                int newAmount) {

            if (newAmount < 0) {
                return false;
            }

            Order order = find(orderId);

            if (order == null) {
                return false;
            }

            order.amount = newAmount;
            return true;
        }

        void rangeReport(
                String low,
                String high) {

            if (low == null
                    || high == null
                    || low.compareTo(high) > 0) {
                return;
            }

            rangeReport(root, low, high);
        }

        private void rangeReport(
                Node node,
                String low,
                String high) {

            if (node == null) {
                return;
            }

            String id = node.order.orderId;

            if (id.compareTo(low) > 0) {
                rangeReport(
                        node.left,
                        low,
                        high);
            }

            if (id.compareTo(low) >= 0
                    && id.compareTo(high) <= 0) {
                System.out.println(node.order);
            }

            if (id.compareTo(high) < 0) {
                rangeReport(
                        node.right,
                        low,
                        high);
            }
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

        int totalAmount() {
            return totalAmount(root);
        }

        private int totalAmount(Node node) {
            if (node == null) {
                return 0;
            }

            return node.order.amount
                    + totalAmount(node.left)
                    + totalAmount(node.right);
        }

        void printInorder() {
            printInorder(root);
        }

        private void printInorder(Node node) {
            if (node == null) {
                return;
            }

            printInorder(node.left);
            System.out.println(node.order);
            printInorder(node.right);
        }

        void printSummary() {
            System.out.println("Order count: " + size());
            System.out.println(
                    "Total amount: " + totalAmount());
        }
    }

    public static void main(String[] args) {
        OrderBst bst = new OrderBst();

        System.out.println(
                "add O103: "
                + bst.add(
                new Order(
                        "O103",
                        "Amy",
                        1200)));

        System.out.println(
                "add O101: "
                + bst.add(
                new Order(
                        "O101",
                        "Ben",
                        800)));

        System.out.println(
                "add O105: "
                + bst.add(
                new Order(
                        "O105",
                        "Cindy",
                        2500)));

        System.out.println(
                "add O102: "
                + bst.add(
                new Order(
                        "O102",
                        "David",
                        1500)));

        System.out.println(
                "add O104: "
                + bst.add(
                new Order(
                        "O104",
                        "Eva",
                        900)));

        System.out.println(
                "add duplicate O103: "
                + bst.add(
                new Order(
                        "O103",
                        "Other",
                        9999)));

        System.out.println("\n=== Inorder ===");
        bst.printInorder();

        System.out.println("\n=== Find ===");
        System.out.println(
                "O102 -> " + bst.find("O102"));

        System.out.println(
                "O999 -> " + bst.find("O999"));

        System.out.println("\n=== Update Amount ===");
        System.out.println(
                "update O102 -> 1800: "
                + bst.updateAmount("O102", 1800));

        System.out.println(
                "update O999 -> 1000: "
                + bst.updateAmount("O999", 1000));

        System.out.println("\n=== Range Report ===");
        bst.rangeReport("O102", "O104");

        System.out.println("\n=== Cancel ===");
        System.out.println(
                "cancel O103: "
                + bst.cancel("O103"));

        System.out.println(
                "cancel O999: "
                + bst.cancel("O999"));

        System.out.println("\n=== Final Summary ===");
        bst.printInorder();
        bst.printSummary();
    }
}