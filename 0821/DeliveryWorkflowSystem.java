import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class DeliveryWorkflowSystem {

    static class Delivery {
        private final String id;
        private final String destination;

        public Delivery(String id, String destination) {
            this.id = id;
            this.destination = destination;
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return id + " -> " + destination;
        }
    }

    static class DeliveryManager {
        private final Map<String, Delivery> deliveryMap =
                new HashMap<>();

        private final Deque<Delivery> waitingQueue =
                new ArrayDeque<>();

        private final Deque<Delivery> completedStack =
                new ArrayDeque<>();

        public boolean add(Delivery delivery) {
            if (delivery == null
                    || delivery.getId() == null
                    || delivery.getId().isBlank()
                    || deliveryMap.containsKey(delivery.getId())) {
                return false;
            }

            deliveryMap.put(delivery.getId(), delivery);
            waitingQueue.offerLast(delivery);

            return true;
        }

        public Delivery process() {
            Delivery delivery = waitingQueue.pollFirst();

            if (delivery == null) {
                return null;
            }

            completedStack.push(delivery);
            return delivery;
        }

        public Delivery undo() {
            if (completedStack.isEmpty()) {
                return null;
            }

            Delivery delivery = completedStack.pop();
            waitingQueue.offerFirst(delivery);

            return delivery;
        }

        public Delivery findById(String id) {
            if (id == null) {
                return null;
            }

            return deliveryMap.get(id);
        }

        public int waitingCount() {
            return waitingQueue.size();
        }

        public int completedCount() {
            return completedStack.size();
        }

        public void printSummary() {
            System.out.println("Waiting: " + waitingQueue);
            System.out.println("Completed: " + completedStack);
            System.out.println("Total: " + deliveryMap.size());
        }
    }

    public static void main(String[] args) {
        DeliveryManager manager =
                new DeliveryManager();

        System.out.println("=== 新增配送 ===");

        System.out.println(
                "新增 D001：" +
                manager.add(
                        new Delivery("D001", "Taipei")));

        System.out.println(
                "新增 D002：" +
                manager.add(
                        new Delivery("D002", "Taichung")));

        System.out.println(
                "新增 D003：" +
                manager.add(
                        new Delivery("D003", "Kaohsiung")));

        System.out.println(
                "重複 D002：" +
                manager.add(
                        new Delivery("D002", "Tainan")));

        manager.printSummary();

        System.out.println("\n=== 查詢 ===");
        System.out.println(
                "D002：" + manager.findById("D002"));

        System.out.println(
                "D999：" + manager.findById("D999"));

        System.out.println("\n=== 處理配送 ===");
        System.out.println(
                "處理：" + manager.process());

        System.out.println(
                "處理：" + manager.process());

        manager.printSummary();

        System.out.println("\n=== Undo ===");
        System.out.println(
                "Undo：" + manager.undo());

        manager.printSummary();

        System.out.println("\n=== 統計 ===");
        System.out.println(
                "等待配送：" + manager.waitingCount());

        System.out.println(
                "已完成：" + manager.completedCount());
    }
}
