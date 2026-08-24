public class GenericArrayStackDemo {

    static class ArrayStack<T> {
        private final T[] data;
        private int top;

        @SuppressWarnings("unchecked")
        public ArrayStack(int capacity) {
            data = (T[]) new Object[Math.max(1, capacity)];
            top = 0;
        }

        public boolean push(T value) {
            if (isFull()) {
                return false;
            }

            data[top] = value;
            top++;
            return true;
        }

        public T pop() {
            if (isEmpty()) {
                return null;
            }

            top--;
            T value = data[top];
            data[top] = null;
            return value;
        }

        public T peek() {
            if (isEmpty()) {
                return null;
            }

            return data[top - 1];
        }

        public int size() {
            return top;
        }

        public boolean isEmpty() {
            return top == 0;
        }

        public boolean isFull() {
            return top == data.length;
        }
    }

    public static void main(String[] args) {
        ArrayStack<String> stringStack =
                new ArrayStack<>(3);

        System.out.println("=== String Stack ===");

        System.out.println("push A："
                + stringStack.push("A"));

        System.out.println("push B："
                + stringStack.push("B"));

        System.out.println("push C："
                + stringStack.push("C"));

        System.out.println("push D："
                + stringStack.push("D"));

        System.out.println("peek："
                + stringStack.peek());

        System.out.println("size："
                + stringStack.size());

        System.out.println("isFull："
                + stringStack.isFull());

        System.out.println("pop："
                + stringStack.pop());

        System.out.println("pop："
                + stringStack.pop());

        System.out.println("size："
                + stringStack.size());

        ArrayStack<Integer> integerStack =
                new ArrayStack<>(3);

        System.out.println("\n=== Integer Stack ===");

        integerStack.push(10);
        integerStack.push(20);
        integerStack.push(30);

        System.out.println("peek："
                + integerStack.peek());

        System.out.println("pop："
                + integerStack.pop());

        System.out.println("pop："
                + integerStack.pop());

        System.out.println("pop："
                + integerStack.pop());

        System.out.println("pop empty："
                + integerStack.pop());

        System.out.println("isEmpty："
                + integerStack.isEmpty());
    }
}
