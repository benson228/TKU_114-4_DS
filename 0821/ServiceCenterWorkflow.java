import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServiceCenterWorkflow {

    static class ServiceTicket {
        private final String id;
        private final String customer;
        private final String service;

        public ServiceTicket(
                String id,
                String customer,
                String service) {

            this.id = id;
            this.customer = customer;
            this.service = service;
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return id + " " + customer
                    + " - " + service;
        }
    }

    static class ServiceCenter {
        private final Map<String, ServiceTicket> ticketMap =
                new HashMap<>();

        private final Deque<ServiceTicket> waitingQueue =
                new ArrayDeque<>();

        private final Deque<ServiceTicket> completedStack =
                new ArrayDeque<>();

        private final Set<String> ticketIds =
                new HashSet<>();

        public boolean createTicket(ServiceTicket ticket) {
            if (ticket == null
                    || ticket.getId() == null
                    || ticket.getId().isBlank()
                    || ticketIds.contains(ticket.getId())) {
                return false;
            }

            ticketMap.put(ticket.getId(), ticket);
            waitingQueue.offerLast(ticket);
            ticketIds.add(ticket.getId());

            return true;
        }

        public ServiceTicket processNext() {
            ServiceTicket ticket =
                    waitingQueue.pollFirst();

            if (ticket == null) {
                return null;
            }

            completedStack.push(ticket);
            return ticket;
        }

        public boolean cancelWaiting(String id) {
            if (id == null) {
                return false;
            }

            for (ServiceTicket ticket : waitingQueue) {
                if (id.equals(ticket.getId())) {
                    waitingQueue.remove(ticket);
                    ticketMap.remove(id);
                    ticketIds.remove(id);
                    return true;
                }
            }

            return false;
        }

        public ServiceTicket undoLastCompletion() {
            if (completedStack.isEmpty()) {
                return null;
            }

            ServiceTicket ticket =
                    completedStack.pop();

            waitingQueue.offerFirst(ticket);

            return ticket;
        }

        public ServiceTicket findById(String id) {
            if (id == null) {
                return null;
            }

            return ticketMap.get(id);
        }

        public void printSummary() {
            System.out.println(
                    "Waiting：" + waitingQueue);

            System.out.println(
                    "Completed：" + completedStack);

            System.out.println(
                    "Ticket Map：" + ticketMap);

            System.out.println(
                    "Ticket IDs：" + ticketIds);
        }
    }

    public static void main(String[] args) {
        ServiceCenter center =
                new ServiceCenter();

        System.out.println("=== 建立 Ticket ===");

        System.out.println(
                "T001：" +
                center.createTicket(
                        new ServiceTicket(
                                "T001",
                                "Amy",
                                "Account")));

        System.out.println(
                "T002：" +
                center.createTicket(
                        new ServiceTicket(
                                "T002",
                                "Bob",
                                "Password")));

        System.out.println(
                "T003：" +
                center.createTicket(
                        new ServiceTicket(
                                "T003",
                                "Charlie",
                                "Network")));

        System.out.println(
                "重複 T002：" +
                center.createTicket(
                        new ServiceTicket(
                                "T002",
                                "David",
                                "Other")));

        System.out.println("\n=== 查詢 ===");

        System.out.println(
                "T002：" +
                center.findById("T002"));

        System.out.println(
                "T999：" +
                center.findById("T999"));

        System.out.println("\n=== 取消等待中的 Ticket ===");

        System.out.println(
                "取消 T002：" +
                center.cancelWaiting("T002"));

        System.out.println(
                "取消不存在 T999：" +
                center.cancelWaiting("T999"));

        System.out.println("\n=== 處理服務 ===");

        System.out.println(
                "處理：" +
                center.processNext());

        System.out.println(
                "處理：" +
                center.processNext());

        System.out.println("\n=== Undo ===");

        System.out.println(
                "第一次 Undo：" +
                center.undoLastCompletion());

        System.out.println(
                "第二次 Undo：" +
                center.undoLastCompletion());

        System.out.println(
                "第三次 Undo：" +
                center.undoLastCompletion());

        System.out.println("\n=== 空 Queue ===");

        System.out.println(
                "處理：" +
                center.processNext());

        System.out.println("\n=== Summary ===");
        center.printSummary();
    }
}