public class RecursiveTextTools {

    static String reverse(String text) {
        if (text == null || text.length() <= 1) {
            return text == null ? null : text;
        }

        return reverse(text.substring(1))
                + text.charAt(0);
    }

    static boolean isPalindrome(String text) {
        if (text == null) {
            return false;
        }

        String normalized = normalize(text);

        return isPalindrome(
                normalized,
                0,
                normalized.length() - 1);
    }

    static boolean isPalindrome(
            String text,
            int left,
            int right) {

        if (left >= right) {
            return true;
        }

        if (text.charAt(left)
                != text.charAt(right)) {
            return false;
        }

        return isPalindrome(
                text,
                left + 1,
                right - 1);
    }

    static int countCharacter(
            String text,
            char target) {

        if (text == null || text.isEmpty()) {
            return 0;
        }

        int count =
                text.charAt(0) == target ? 1 : 0;

        return count
                + countCharacter(
                        text.substring(1),
                        target);
    }

    static String normalize(String text) {
        return text
                .replaceAll("\\s+", "")
                .toLowerCase();
    }

    public static void main(String[] args) {
        String[] tests = {
                "",
                "A",
                "Level",
                "Hello World"
        };

        for (String text : tests) {
            System.out.println("text = \"" + text + "\"");
            System.out.println(
                    "reverse = " + reverse(text));
            System.out.println(
                    "isPalindrome = "
                    + isPalindrome(text));
            System.out.println();
        }

        System.out.println(
                "countCharacter(\"banana\", 'a') = "
                + countCharacter("banana", 'a'));

        System.out.println(
                "isPalindrome(\"Never Odd Or Even\") = "
                + isPalindrome("Never Odd Or Even"));
    }
}