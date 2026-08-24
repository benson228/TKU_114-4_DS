import java.util.Arrays;

public class CircularQueuePractice {

    static class CircularQueue<T> {
        private final Object[] data;
        private int front;
        private int rear;
        private int size;

        public CircularQueue(int capacity) {
            data = new Object[Math.max(1, capacity)];
            front = 0;
            rear = 0;
            size = 0;
        }

        public boolean enqueue(T value) {
            if (isFull()) {
                return false;
            }

            data[rear] = value;
            rear = (rear + 1) % data.length;
            size++;

            return true;
        }

        @SuppressWarnings("unchecked")
        public T dequeue() {
            if (isEmpty()) {
                return null;
            }

            T value = (T) data[front];
            data[front] = null;
            front = (front + 1) % data.length;
            size--;

            return value;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == data.length;
        }

        public void printState(String operation) {
            System.out.println(
                    operation
                    + " | array=" + Arrays.toString(data)
                    + ", front=" + front
                    + ", rear=" + rear
                    + ", size=" + size
            );
        }
    }

    public static void main(String[] args) {
        CircularQueue<String> queue =
                new CircularQueue<>(4);

        queue.enqueue("A");
        queue.printState("enqueue A");

        queue.enqueue("B");
        queue.printState("enqueue B");

        queue.enqueue("C");
        queue.printState("enqueue C");

        queue.dequeue();
        queue.printState("dequeue");

        queue.dequeue();
        queue.printState("dequeue");

        queue.enqueue("D");
        queue.printState("enqueue D");

        queue.enqueue("E");
        queue.printState("enqueue E");

        queue.enqueue("F");
        queue.printState("enqueue F");

        queue.dequeue();
        queue.printState("dequeue");

        queue.enqueue("G");
        queue.printState("enqueue G");

        System.out.println("\n=== FIFO 取出 ===");

        while (!queue.isEmpty()) {
            System.out.println("dequeue -> "
                    + queue.dequeue());
        }

        queue.printState("最後狀態");
    }
}
