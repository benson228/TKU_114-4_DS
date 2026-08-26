public class Q08_RecursiveAudit {

    public static int sumValid(int[] data, int index) {
        if (data == null || index >= data.length) {
            return 0;
        }

        if (index < 0) {
            index = 0;
        }

        int current = data[index];

        if (current < 0 || current > 100) {
            return sumValid(data, index + 1);
        }

        return current + sumValid(data, index + 1);
    }

    public static int countOccurrences(
            int[] data,
            int index,
            int target) {

        if (data == null || index >= data.length) {
            return 0;
        }

        if (index < 0) {
            index = 0;
        }

        int count =
                data[index] == target ? 1 : 0;

        return count
                + countOccurrences(
                        data,
                        index + 1,
                        target);
    }

    public static boolean isPalindrome(
            String text,
            int left,
            int right) {

        if (text == null) {
            return false;
        }

        if (left >= right) {
            return true;
        }

        if (Character.toLowerCase(
                text.charAt(left))
                != Character.toLowerCase(
                text.charAt(right))) {
            return false;
        }

        return isPalindrome(
                text,
                left + 1,
                right - 1);
    }
}