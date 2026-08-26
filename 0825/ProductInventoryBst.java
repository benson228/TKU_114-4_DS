public class ProductInventoryBst {

    static class Product {
        String id;
        String name;
        int stock;

        Product(String id, String name, int stock) {
            this.id = id;
            this.name = name;
            this.stock = stock;
        }

        @Override
        public String toString() {
            return id + " " + name + " stock=" + stock;
        }
    }

    static class Node {
        Product product;
        Node left;
        Node right;

        Node(Product product) {
            this.product = product;
        }
    }

    static class ProductBst {
        private Node root;

        boolean add(Product product) {
            if (product == null || product.id == null) {
                return false;
            }

            if (root == null) {
                root = new Node(product);
                return true;
            }

            Node current = root;

            while (true) {
                int comparison =
                        product.id.compareTo(
                                current.product.id);

                if (comparison == 0) {
                    return false;
                }

                if (comparison < 0) {
                    if (current.left == null) {
                        current.left = new Node(product);
                        return true;
                    }

                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new Node(product);
                        return true;
                    }

                    current = current.right;
                }
            }
        }

        Product find(String id) {
            if (id == null) {
                return null;
            }

            Node current = root;

            while (current != null) {
                int comparison =
                        id.compareTo(
                                current.product.id);

                if (comparison == 0) {
                    return current.product;
                }

                if (comparison < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return null;
        }

        boolean restock(String id, int amount) {
            if (amount <= 0) {
                return false;
            }

            Product product = find(id);

            if (product == null) {
                return false;
            }

            product.stock += amount;
            return true;
        }

        boolean reduceStock(String id, int amount) {
            if (amount <= 0) {
                return false;
            }

            Product product = find(id);

            if (product == null
                    || product.stock < amount) {
                return false;
            }

            product.stock -= amount;
            return true;
        }

        boolean delete(String id) {
            if (find(id) == null) {
                return false;
            }

            root = delete(root, id);
            return true;
        }

        private Node delete(Node node, String id) {
            if (node == null) {
                return null;
            }

            int comparison =
                    id.compareTo(node.product.id);

            if (comparison < 0) {
                node.left =
                        delete(node.left, id);
            } else if (comparison > 0) {
                node.right =
                        delete(node.right, id);
            } else {
                if (node.left == null) {
                    return node.right;
                }

                if (node.right == null) {
                    return node.left;
                }

                Node successor =
                        findMin(node.right);

                node.product =
                        successor.product;

                node.right =
                        delete(
                                node.right,
                                successor.product.id);
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
            System.out.println(node.product);
            printInorder(node.right);
        }
    }

    public static void main(String[] args) {
        ProductBst bst = new ProductBst();

        System.out.println(
                "add P003: "
                + bst.add(
                new Product(
                        "P003", "Keyboard", 20)));

        System.out.println(
                "add P001: "
                + bst.add(
                new Product(
                        "P001", "Mouse", 15)));

        System.out.println(
                "add P005: "
                + bst.add(
                new Product(
                        "P005", "Monitor", 8)));

        System.out.println(
                "add P002: "
                + bst.add(
                new Product(
                        "P002", "Headset", 12)));

        System.out.println(
                "add P004: "
                + bst.add(
                new Product(
                        "P004", "Webcam", 10)));

        System.out.println("\n=== Inorder ===");
        bst.printInorder();

        System.out.println("\n=== Find ===");
        System.out.println(
                "P003 -> " + bst.find("P003"));

        System.out.println(
                "P999 -> " + bst.find("P999"));

        System.out.println("\n=== Stock Update ===");

        System.out.println(
                "restock P003 +10: "
                + bst.restock("P003", 10));

        System.out.println(
                "reduce P001 -5: "
                + bst.reduceStock("P001", 5));

        System.out.println(
                "reduce P005 -20: "
                + bst.reduceStock("P005", 20));

        bst.printInorder();

        System.out.println("\n=== Delete ===");

        System.out.println(
                "delete P002: "
                + bst.delete("P002"));

        System.out.println(
                "delete P999: "
                + bst.delete("P999"));

        bst.printInorder();
    }
}