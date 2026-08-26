public class RecursiveDigitReport {

    static int digitSum(int number) {
        number = Math.abs(number);

        if (number < 10) {
            return number;
        }

        return number % 10 + digitSum(number / 10);
    }

    static int digitCount(int number) {
        number = Math.abs(number);

        if (number < 10) {
            return 1;
        }

        return 1 + digitCount(number / 10);
    }

    static int countDigit(int number, int target) {
        number = Math.abs(number);
        target = Math.abs(target);

        if (number < 10) {
            return number == target ? 1 : 0;
        }

        int count = number % 10 == target ? 1 : 0;

        return count + countDigit(number / 10, target);
    }

    public static void main(String[] args) {
        int[] numbers = {50205, 0, -731};

        for (int number : numbers) {
            System.out.println("number = " + number);
            System.out.println("digitSum = "
                    + digitSum(number));
            System.out.println("digitCount = "
                    + digitCount(number));
            System.out.println("countDigit(0) = "
                    + countDigit(number, 0));
            System.out.println();
        }
    }
}