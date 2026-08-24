import java.util.ArrayDeque;
import java.util.Deque;

public class CounterWaitingQueue {

    static class Customer {
        private final String id;
        private final String name;

        public Customer(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return id + " " + name;
        }
    }

    static class WaitingQueue {
        private final Deque<Customer> queue =
                new ArrayDeque<>();

        public boolean add(Customer customer) {
            if (customer == null) {
                return false;
            }

            queue.offerLast(customer);
            return true;
        }

        public Customer peekNext() {
            return queue.peekFirst();
        }

        public Customer serveNext() {
            return queue.pollFirst();
        }

        public int size() {
            return queue.size();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

        public void printQueue() {
            System.out.println(queue);
        }
    }

    public static void main(String[] args) {
        WaitingQueue waitingQueue =
                new WaitingQueue();

        System.out.println("=== 加入顧客 ===");

        System.out.println(
                "加入：" +
                waitingQueue.add(
                        new Customer("C001", "Amy")));

        System.out.println(
                "加入：" +
                waitingQueue.add(
                        new Customer("C002", "Bob")));

        System.out.println(
                "加入：" +
                waitingQueue.add(
                        new Customer("C003", "Charlie")));

        System.out.println("\n=== 等候隊列 ===");
        waitingQueue.printQueue();

        System.out.println(
                "等候人數：" +
                waitingQueue.size());

        System.out.println(
                "下一位：" +
                waitingQueue.peekNext());

        System.out.println(
                "服務：" +
                waitingQueue.serveNext());

        System.out.println(
                "服務：" +
                waitingQueue.serveNext());

        System.out.println("\n=== 剩餘隊列 ===");
        waitingQueue.printQueue();

        System.out.println(
                "等候人數：" +
                waitingQueue.size());

        System.out.println(
                "最後服務：" +
                waitingQueue.serveNext());

        System.out.println(
                "空隊列服務：" +
                waitingQueue.serveNext());

        System.out.println(
                "是否為空：" +
                waitingQueue.isEmpty());
    }
}