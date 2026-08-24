public class DynamicArrayPractice {

    static class DynamicArray<T> {
        private Object[] data;
        private int size;

        public DynamicArray(int capacity) {
            data = new Object[Math.max(1, capacity)];
            size = 0;
        }

        public void add(T value) {
            ensureCapacity();

            data[size] = value;
            size++;
        }

        public void add(int index, T value) {
            if (index < 0 || index > size) {
                return;
            }

            ensureCapacity();

            for (int i = size; i > index; i--) {
                data[i] = data[i - 1];
            }

            data[index] = value;
            size++;
        }

        @SuppressWarnings("unchecked")
        public T get(int index) {
            if (index < 0 || index >= size) {
                return null;
            }

            return (T) data[index];
        }

        @SuppressWarnings("unchecked")
        public T set(int index, T value) {
            if (index < 0 || index >= size) {
                return null;
            }

            T oldValue = (T) data[index];
            data[index] = value;
            return oldValue;
        }

        @SuppressWarnings("unchecked")
        public T remove(int index) {
            if (index < 0 || index >= size) {
                return null;
            }

            T removedValue = (T) data[index];

            for (int i = index; i < size - 1; i++) {
                data[i] = data[i + 1];
            }

            size--;
            data[size] = null;

            return removedValue;
        }

        public int size() {
            return size;
        }

        public int capacity() {
            return data.length;
        }

        private void ensureCapacity() {
            if (size < data.length) {
                return;
            }

            Object[] newData = new Object[data.length * 2];

            for (int i = 0; i < data.length; i++) {
                newData[i] = data[i];
            }

            data = newData;
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder("[");

            for (int i = 0; i < size; i++) {
                if (i > 0) {
                    result.append(", ");
                }

                result.append(data[i]);
            }

            result.append("]");
            return result.toString();
        }
    }

    public static void main(String[] args) {
        DynamicArray<String> strings =
                new DynamicArray<>(2);

        System.out.println("=== String ===");

        strings.add("A");
        strings.add("B");

        System.out.println("資料：" + strings);
        System.out.println("size：" + strings.size());
        System.out.println("capacity：" + strings.capacity());

        strings.add("C");

        System.out.println("加入 C 後：" + strings);
        System.out.println("capacity：" + strings.capacity());

        strings.add(1, "X");

        System.out.println("位置 1 插入 X：" + strings);

        System.out.println("get(2)：" + strings.get(2));

        System.out.println("set(2, Y) 舊值："
                + strings.set(2, "Y"));

        System.out.println("set 後：" + strings);

        System.out.println("remove(1)："
                + strings.remove(1));

        System.out.println("remove 後：" + strings);

        System.out.println("\n=== Integer ===");

        DynamicArray<Integer> integers =
                new DynamicArray<>(2);

        integers.add(10);
        integers.add(20);
        integers.add(30);

        System.out.println("資料：" + integers);
        System.out.println("size：" + integers.size());
        System.out.println("capacity：" + integers.capacity());

        System.out.println("\n=== 邊界測試 ===");

        integers.add(-1, 100);
        System.out.println("add(-1, 100)：" + integers);

        integers.add(integers.size(), 40);
        System.out.println("add(size, 40)：" + integers);

        System.out.println("get(-1)："
                + integers.get(-1));

        System.out.println("get(size)："
                + integers.get(integers.size()));

        System.out.println("remove(-1)："
                + integers.remove(-1));

        System.out.println("remove(size)："
                + integers.remove(integers.size()));

        DynamicArray<Integer> empty =
                new DynamicArray<>(2);

        System.out.println("空結構 remove(0)："
                + empty.remove(0));
    }
}