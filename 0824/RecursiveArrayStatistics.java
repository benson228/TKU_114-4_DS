public class RecursiveArrayStatistics {

    static int maximum(int[] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException(
                    "Array cannot be null or empty");
        }

        return maximum(data, 0);
    }

    static int maximum(int[] data, int index) {
        if (index == data.length - 1) {
            return data[index];
        }

        int restMaximum =
                maximum(data, index + 1);

        return Math.max(data[index], restMaximum);
    }

    static int minimum(int[] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException(
                    "Array cannot be null or empty");
        }

        return minimum(data, 0);
    }

    static int minimum(int[] data, int index) {
        if (index == data.length - 1) {
            return data[index];
        }

        int restMinimum =
                minimum(data, index + 1);

        return Math.min(data[index], restMinimum);
    }

    static int countAbove(int[] data, int threshold) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException(
                    "Array cannot be null or empty");
        }

        return countAbove(data, threshold, 0);
    }

    static int countAbove(
            int[] data,
            int threshold,
            int index) {

        if (index == data.length) {
            return 0;
        }

        int count =
                data[index] > threshold ? 1 : 0;

        return count
                + countAbove(data, threshold, index + 1);
    }

    public static void main(String[] args) {
        int[] numbers = {12, 5, 27, 8, 19, 3};

        System.out.println("Array：");
        for (int number : numbers) {
            System.out.print(number + " ");
        }

        System.out.println();

        System.out.println("maximum："
                + maximum(numbers));

        System.out.println("minimum："
                + minimum(numbers));

        System.out.println("countAbove(10)："
                + countAbove(numbers, 10));

        System.out.println("\n=== 邊界測試 ===");

        try {
            maximum(null);
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "maximum(null)：IllegalArgumentException");
        }

        try {
            minimum(new int[0]);
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "minimum(empty)：IllegalArgumentException");
        }

        try {
            countAbove(new int[0], 10);
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "countAbove(empty)：IllegalArgumentException");
        }
    }
}