public class LibraryBookBst {

    static class Book {
        String isbn;
        String title;
        String author;
        boolean available;

        Book(String isbn, String title, String author, boolean available) {
            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.available = available;
        }

        @Override
        public String toString() {
            return isbn
                    + " " + title
                    + " author=" + author
                    + " available=" + available;
        }
    }

    static class Node {
        Book book;
        Node left;
        Node right;

        Node(Book book) {
            this.book = book;
        }
    }

    static class BookBst {
        private Node root;

        boolean add(Book book) {
            if (book == null
                    || book.isbn == null
                    || book.isbn.isBlank()) {
                return false;
            }

            if (root == null) {
                root = new Node(book);
                return true;
            }

            Node current = root;

            while (true) {
                int comparison =
                        book.isbn.compareTo(
                                current.book.isbn);

                if (comparison == 0) {
                    return false;
                }

                if (comparison < 0) {
                    if (current.left == null) {
                        current.left = new Node(book);
                        return true;
                    }

                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new Node(book);
                        return true;
                    }

                    current = current.right;
                }
            }
        }

        Book find(String isbn) {
            if (isbn == null || isbn.isBlank()) {
                return null;
            }

            Node current = root;

            while (current != null) {
                int comparison =
                        isbn.compareTo(
                                current.book.isbn);

                if (comparison == 0) {
                    return current.book;
                }

                if (comparison < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return null;
        }

        boolean borrow(String isbn) {
            Book book = find(isbn);

            if (book == null || !book.available) {
                return false;
            }

            book.available = false;
            return true;
        }

        boolean returnBook(String isbn) {
            Book book = find(isbn);

            if (book == null || book.available) {
                return false;
            }

            book.available = true;
            return true;
        }

        boolean remove(String isbn) {
            Book book = find(isbn);

            if (book == null || !book.available) {
                return false;
            }

            root = remove(root, isbn);
            return true;
        }

        private Node remove(
                Node node,
                String isbn) {

            if (node == null) {
                return null;
            }

            int comparison =
                    isbn.compareTo(
                            node.book.isbn);

            if (comparison < 0) {
                node.left =
                        remove(node.left, isbn);
            } else if (comparison > 0) {
                node.right =
                        remove(node.right, isbn);
            } else {
                if (node.left == null) {
                    return node.right;
                }

                if (node.right == null) {
                    return node.left;
                }

                Node successor =
                        findMin(node.right);

                node.book = successor.book;

                node.right =
                        remove(
                                node.right,
                                successor.book.isbn);
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

            String isbn = node.book.isbn;

            if (isbn.compareTo(low) > 0) {
                rangeQuery(
                        node.left,
                        low,
                        high);
            }

            if (isbn.compareTo(low) >= 0
                    && isbn.compareTo(high) <= 0) {
                System.out.println(node.book);
            }

            if (isbn.compareTo(high) < 0) {
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
            System.out.println(node.book);
            printInorder(node.right);
        }
    }

    public static void main(String[] args) {

        BookBst library = new BookBst();

        System.out.println(
                "add B103: "
                + library.add(
                new Book(
                        "B103",
                        "Data Structures",
                        "Alice",
                        true)));

        System.out.println(
                "add B101: "
                + library.add(
                new Book(
                        "B101",
                        "Java Basics",
                        "Bob",
                        true)));

        System.out.println(
                "add B105: "
                + library.add(
                new Book(
                        "B105",
                        "Algorithms",
                        "Cindy",
                        true)));

        System.out.println(
                "add B102: "
                + library.add(
                new Book(
                        "B102",
                        "Database",
                        "David",
                        true)));

        System.out.println(
                "add B104: "
                + library.add(
                new Book(
                        "B104",
                        "Networks",
                        "Eva",
                        true)));

        System.out.println(
                "duplicate B103: "
                + library.add(
                new Book(
                        "B103",
                        "Duplicate",
                        "Other",
                        true)));

        System.out.println("\n=== Inorder Report ===");
        library.printInorder();

        System.out.println("\n=== Find ===");
        System.out.println(
                "B102 -> "
                + library.find("B102"));

        System.out.println(
                "B999 -> "
                + library.find("B999"));

        System.out.println("\n=== Borrow ===");

        System.out.println(
                "borrow B103: "
                + library.borrow("B103"));

        System.out.println(
                "borrow B103 again: "
                + library.borrow("B103"));

        System.out.println("\n=== Remove Borrowed Book ===");

        System.out.println(
                "remove B103: "
                + library.remove("B103"));

        System.out.println("\n=== Return Book ===");

        System.out.println(
                "return B103: "
                + library.returnBook("B103"));

        System.out.println(
                "remove B103: "
                + library.remove("B103"));

        System.out.println("\n=== Range Query ===");
        library.rangeQuery("B102", "B105");

        System.out.println("\n=== Final Inorder ===");
        library.printInorder();
    }
}