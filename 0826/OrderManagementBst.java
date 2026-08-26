public class OrderManagementBst {

    static class Order {
        String orderId;
        String customer;
        int amount;
        String status;

        Order(
                String orderId,
                String customer,
                int amount,
                String status) {

            this.orderId = orderId;
            this.customer = customer;
            this.amount = amount;
            this.status = status;
        }

        @Override
        public String toString() {
            return orderId
                    + " " + customer
                    + " amount=" + amount
                    + " status=" + status;
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
            if (order == null
                    || order.orderId == null
                    || order.orderId.isBlank()
                    || order.amount < 0
                    || order.status == null
                    || order.status.isBlank()) {
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
            if (orderId == null
                    || orderId.isBlank()) {
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

        boolean updateStatus(
                String orderId,
                String newStatus) {

            if (newStatus == null
                    || newStatus.isBlank()) {
                return false;
            }

            Order order = find(orderId);

            if (order == null) {
                return false;
            }

            order.status = newStatus;
            return true;
        }

        boolean cancel(String orderId) {
            Order order = find(orderId);

            if (order == null) {
                return false;
            }

            if ("CANCELLED".equals(order.status)) {
                return false;
            }

            order.status = "CANCELLED";
            return true;
        }

        boolean remove(String orderId) {
            Order order = find(orderId);

            if (order == null
                    || !"CANCELLED".equals(order.status)) {
                return false;
            }

            root = remove(root, orderId);
            return true;
        }

        private Node remove(
                Node node,
                String orderId) {

            if (node == null) {
                return null;
            }

            int comparison =
                    orderId.compareTo(
                            node.order.orderId);

            if (comparison < 0) {
                node.left =
                        remove(node.left, orderId);
            } else if (comparison > 0) {
                node.right =
                        remove(node.right, orderId);
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
                        remove(
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
    }

    public static void main(String[] args) {

        OrderBst orders = new OrderBst();

        System.out.println(
                "add O103: "
                + orders.add(
                new Order(
                        "O103",
                        "Amy",
                        1200,
                        "NEW")));

        System.out.println(
                "add O101: "
                + orders.add(
                new Order(
                        "O101",
                        "Ben",
                        800,
                        "PAID")));

        System.out.println(
                "add O105: "
                + orders.add(
                new Order(
                        "O105",
                        "Cindy",
                        2500,
                        "SHIPPED")));

        System.out.println(
                "add O102: "
                + orders.add(
                new Order(
                        "O102",
                        "David",
                        1500,
                        "PAID")));

        System.out.println(
                "add O104: "
                + orders.add(
                new Order(
                        "O104",
                        "Eva",
                        900,
                        "NEW")));

        System.out.println(
                "duplicate O103: "
                + orders.add(
                new Order(
                        "O103",
                        "Other",
                        9999,
                        "NEW")));

        System.out.println(
                "negative amount: "
                + orders.add(
                new Order(
                        "O106",
                        "Frank",
                        -100,
                        "NEW")));

        System.out.println("\n=== Find ===");

        System.out.println(
                "O102 -> "
                + orders.find("O102"));

        System.out.println(
                "O999 -> "
                + orders.find("O999"));

        System.out.println("\n=== Update Status ===");

        System.out.println(
                "update O102 -> SHIPPED: "
                + orders.updateStatus(
                "O102",
                "SHIPPED"));

        System.out.println(
                "update O999: "
                + orders.updateStatus(
                "O999",
                "PAID"));

        System.out.println("\n=== Cancel ===");

        System.out.println(
                "cancel O103: "
                + orders.cancel("O103"));

        System.out.println(
                "cancel O103 again: "
                + orders.cancel("O103"));

        System.out.println("\n=== Remove ===");

        System.out.println(
                "remove O103: "
                + orders.remove("O103"));

        System.out.println(
                "remove O102: "
                + orders.remove("O102"));

        System.out.println("\n=== Range Report ===");

        orders.rangeReport(
                "O101",
                "O105");

        System.out.println("\n=== Final Inorder ===");

        orders.printInorder();

        System.out.println();

        System.out.println(
                "Order count: "
                + orders.size());

        System.out.println(
                "Total amount: "
                + orders.totalAmount());
    }
}