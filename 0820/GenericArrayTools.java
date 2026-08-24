public class GenericArrayTools {

    static <T> int countMatches(T[] data, T target) {
        if (data == null) {
            return 0;
        }

        int count = 0;

        for (T item : data) {
            if (target == null ? item == null : target.equals(item)) {
                count++;
            }
        }

        return count;
    }

    static <T> T last(T[] data) {
        if (data == null || data.length == 0) {
            return null;
        }

        return data[data.length - 1];
    }

    static <T> void swap(T[] data, int first, int second) {
        if (data == null
                || first < 0
                || second < 0
                || first >= data.length
                || second >= data.length) {
            return;
        }

        T temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    public static void main(String[] args) {
        String[] names = {"Amy", "Bob", "Amy", "Charlie"};

        System.out.println("=== countMatches ===");
        System.out.println("Amy 次數："
                + countMatches(names, "Amy"));
        System.out.println("David 次數："
                + countMatches(names, "David"));

        System.out.println("\n=== last ===");
        System.out.println("最後一個：" + last(names));

        System.out.println("\n=== swap ===");
        System.out.println("交換前："
                + java.util.Arrays.toString(names));

        swap(names, 0, 3);

        System.out.println("交換後："
                + java.util.Arrays.toString(names));

        System.out.println("\n=== 邊界測試 ===");
        System.out.println("null 陣列："
                + countMatches(null, "Amy"));
        System.out.println("空陣列最後一個："
                + last(new String[0]));

        swap(names, -1, 2);
        System.out.println("不合法 index 測試完成");
    }
}