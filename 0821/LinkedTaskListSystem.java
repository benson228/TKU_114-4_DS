public class LinkedTaskListSystem {

    static class Task {
        private final String id;
        private final String name;

        public Task(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return id + " " + name;
        }
    }

    static class TaskNode {
        private final Task task;
        private TaskNode next;

        public TaskNode(Task task) {
            this.task = task;
        }
    }

    static class TaskLinkedList {
        private TaskNode head;
        private int size;

        public boolean addFirst(Task task) {
            if (task == null
                    || task.getId() == null
                    || task.getId().isBlank()
                    || findById(task.getId()) != null) {
                return false;
            }

            TaskNode node = new TaskNode(task);
            node.next = head;
            head = node;
            size++;

            return true;
        }

        public boolean addLast(Task task) {
            if (task == null
                    || task.getId() == null
                    || task.getId().isBlank()
                    || findById(task.getId()) != null) {
                return false;
            }

            TaskNode node = new TaskNode(task);

            if (head == null) {
                head = node;
                size++;
                return true;
            }

            TaskNode current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = node;
            size++;

            return true;
        }

        public Task findById(String id) {
            TaskNode current = head;

            while (current != null) {
                if (id != null
                        && id.equals(current.task.getId())) {
                    return current.task;
                }

                current = current.next;
            }

            return null;
        }

        public boolean removeById(String id) {
            if (id == null || head == null) {
                return false;
            }

            if (id.equals(head.task.getId())) {
                head = head.next;
                size--;
                return true;
            }

            TaskNode current = head;

            while (current.next != null) {
                if (id.equals(current.next.task.getId())) {
                    current.next = current.next.next;
                    size--;
                    return true;
                }

                current = current.next;
            }

            return false;
        }

        public boolean insertAfter(
                String existingId,
                Task task) {

            if (existingId == null
                    || task == null
                    || task.getId() == null
                    || task.getId().isBlank()
                    || findById(task.getId()) != null) {
                return false;
            }

            TaskNode current = head;

            while (current != null) {
                if (existingId.equals(current.task.getId())) {
                    TaskNode node = new TaskNode(task);
                    node.next = current.next;
                    current.next = node;
                    size++;
                    return true;
                }

                current = current.next;
            }

            return false;
        }

        public int size() {
            return size;
        }

        public void printAll() {
            if (head == null) {
                System.out.println("List is empty");
                return;
            }

            TaskNode current = head;

            while (current != null) {
                System.out.println(current.task);
                current = current.next;
            }
        }
    }

    public static void main(String[] args) {
        TaskLinkedList list = new TaskLinkedList();

        System.out.println("=== 空 List ===");
        list.printAll();
        System.out.println("size：" + list.size());

        System.out.println("\n=== addLast ===");
        System.out.println(
                list.addLast(
                        new Task("T002", "Study")));

        System.out.println(
                list.addLast(
                        new Task("T003", "Practice")));

        list.printAll();

        System.out.println("\n=== addFirst ===");
        System.out.println(
                list.addFirst(
                        new Task("T001", "Read")));

        list.printAll();

        System.out.println("\n=== 重複 id ===");
        System.out.println(
                list.addLast(
                        new Task("T002", "Duplicate")));

        System.out.println("\n=== findById ===");
        System.out.println(
                "T002：" + list.findById("T002"));

        System.out.println(
                "T999：" + list.findById("T999"));

        System.out.println("\n=== insertAfter ===");
        System.out.println(
                list.insertAfter(
                        "T002",
                        new Task("T004", "Homework")));

        list.printAll();

        System.out.println("\n=== 刪除 head ===");
        System.out.println(
                list.removeById("T001"));

        list.printAll();

        System.out.println("\n=== 刪除 middle ===");
        System.out.println(
                list.removeById("T003"));

        list.printAll();

        System.out.println("\n=== 刪除 tail ===");
        System.out.println(
                list.removeById("T004"));

        list.printAll();

        System.out.println("\n=== 找不到 id ===");
        System.out.println(
                "remove T999：" +
                list.removeById("T999"));

        System.out.println(
                "insertAfter T999：" +
                list.insertAfter(
                        "T999",
                        new Task("T005", "Test")));

        System.out.println("\n=== 最終 ===");
        list.printAll();
        System.out.println("size：" + list.size());
    }
}