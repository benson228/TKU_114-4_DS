public class CustomerOrderSystem {
    static class Customer {
        private String customerId;
        private String name;

        public Customer(String customerId, String name) {
            this.customerId = customerId;
            this.name = name;
        }

        public String getCustomerId() {
            return customerId;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return customerId + " - " + name;
        }
    }

    static class OrderItem {
        private String productName;
        private double price;
        private int quantity;

        public OrderItem(String productName, double price, int quantity) {
            this.productName = productName;
            this.price = price;
            this.quantity = quantity;
        }

        public double getSubtotal() {
            return price * quantity;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public String toString() {
            return String.format(
                "%s x%d, 單價 %.2f, 小計 %.2f",
                productName, quantity, price, getSubtotal()
            );
        }
    }

    static class CustomerOrder {
        private Customer customer;
        private OrderItem[] items;

        public CustomerOrder(Customer customer, OrderItem[] items) {
            this.customer = customer;
            this.items = items;
        }

        public double getTotal() {
            double total = 0;

            for (OrderItem item : items) {
                if (item != null) {
                    total += item.getSubtotal();
                }
            }

            return total;
        }

        public int getTotalQuantity() {
            int totalQuantity = 0;

            for (OrderItem item : items) {
                if (item != null) {
                    totalQuantity += item.getQuantity();
                }
            }

            return totalQuantity;
        }

        public void printSummary() {
            System.out.println("顧客：" + customer);
            System.out.println("訂單明細：");

            for (OrderItem item : items) {
                if (item != null) {
                    System.out.println(item);
                }
            }

            System.out.printf("訂單總額：%.2f%n", getTotal());
            System.out.println("品項總數量：" + getTotalQuantity());
        }
    }

    public static void main(String[] args) {
        Customer customer = new Customer("C001", "王小明");

        OrderItem[] items = {
            new OrderItem("Java 程式設計書", 500, 2),
            new OrderItem("USB 隨身碟", 300, 1),
            new OrderItem("滑鼠", 450, 2)
        };

        CustomerOrder order = new CustomerOrder(customer, items);
        order.printSummary();
    }
}