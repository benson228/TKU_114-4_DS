import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ClinicQueueSystem {

    static class Patient {
        private final String recordId;
        private final String name;

        public Patient(String recordId, String name) {
            this.recordId = recordId;
            this.name = name;
        }

        public String getRecordId() {
            return recordId;
        }

        @Override
        public String toString() {
            return recordId + " " + name;
        }
    }

    static class ClinicQueue {
        private final Deque<Patient> waitingQueue =
                new ArrayDeque<>();

        private final List<Patient> completed =
                new ArrayList<>();

        public boolean register(Patient patient) {
            if (patient == null) {
                return false;
            }

            waitingQueue.offerLast(patient);
            return true;
        }

        public boolean cancel(String recordId) {
            if (recordId == null) {
                return false;
            }

            for (Patient patient : waitingQueue) {
                if (recordId.equals(patient.getRecordId())) {
                    waitingQueue.remove(patient);
                    return true;
                }
            }

            return false;
        }

        public Patient peekNext() {
            return waitingQueue.peekFirst();
        }

        public Patient callNext() {
            Patient patient = waitingQueue.pollFirst();

            if (patient != null) {
                completed.add(patient);
            }

            return patient;
        }

        public int waitingCount() {
            return waitingQueue.size();
        }

        public List<Patient> getCompleted() {
            return new ArrayList<>(completed);
        }

        public void printWaiting() {
            System.out.println("等待隊列：" + waitingQueue);
        }

        public void printCompleted() {
            System.out.println("完成清單：" + completed);
        }
    }

    public static void main(String[] args) {
        ClinicQueue clinic = new ClinicQueue();

        System.out.println("=== 一般掛號 ===");

        System.out.println(
                "掛號：" +
                clinic.register(
                        new Patient("P001", "Amy")));

        System.out.println(
                "掛號：" +
                clinic.register(
                        new Patient("P002", "Bob")));

        System.out.println(
                "掛號：" +
                clinic.register(
                        new Patient("P003", "Charlie")));

        clinic.printWaiting();

        System.out.println("\n=== 查看下一位 ===");
        System.out.println(
                "下一位：" + clinic.peekNext());

        System.out.println("\n=== 取消指定病歷號 ===");
        System.out.println(
                "取消 P002：" + clinic.cancel("P002"));

        System.out.println(
                "取消 P999：" + clinic.cancel("P999"));

        clinic.printWaiting();

        System.out.println("\n=== 叫號 ===");
        System.out.println(
                "服務：" + clinic.callNext());

        System.out.println(
                "服務：" + clinic.callNext());

        clinic.printWaiting();

        System.out.println(
                "等候人數：" + clinic.waitingCount());

        System.out.println("\n=== 當日完成清單 ===");
        clinic.printCompleted();

        System.out.println("\n=== 空 Queue 測試 ===");
        System.out.println(
                "服務：" + clinic.callNext());

        System.out.println(
                "下一位：" + clinic.peekNext());
    }
}
