public class RecursiveCallReport {

    static int sum(int[] data, int index) {
        if (data == null || index >= data.length) {
            return 0;
        }

        System.out.println(
                "index=" + index
                + ", current value=" + data[index]);

        int recursiveResult =
                sum(data, index + 1);

        int returnValue =
                data[index] + recursiveResult;

        System.out.println(
                "index=" + index
                + ", recursive result=" + recursiveResult
                + ", return value=" + returnValue);

        return returnValue;
    }

    static void test(int[] data) {
        System.out.println("=== Test ===");

        if (data == null || data.length == 0) {
            System.out.println("empty array");
            System.out.println("sum=0");
            System.out.println();
            return;
        }

        int result = sum(data, 0);

        System.out.println("sum=" + result);
        System.out.println();
    }

    public static void main(String[] args) {
        test(new int[]{10, 20, 30, 40});
        test(new int[]{99});
        test(new int[]{});
    }
}