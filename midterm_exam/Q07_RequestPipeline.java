import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q07_RequestPipeline {

    public static boolean isBalanced(String text) {
        if (text == null) {
            return false;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);

            if (current == '('
                    || current == '['
                    || current == '{') {
                stack.push(current);
            } else if (current == ')'
                    || current == ']'
                    || current == '}') {

                if (stack.isEmpty()) {
                    return false;
                }

                char opening = stack.pop();

                if (!matches(opening, current)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static boolean matches(
            char opening,
            char closing) {

        return (opening == '(' && closing == ')')
                || (opening == '[' && closing == ']')
                || (opening == '{' && closing == '}');
    }

    public static List<String> process(
            String[] commands) {

        List<String> result = new ArrayList<>();

        if (commands == null) {
            return result;
        }

        Deque<String> normalQueue =
                new ArrayDeque<>();

        Deque<String> urgentQueue =
                new ArrayDeque<>();

        for (String command : commands) {
            if (command == null
                    || command.trim().isEmpty()) {
                continue;
            }

            String trimmed = command.trim();

            if (trimmed.equals("PROCESS")) {
                if (!urgentQueue.isEmpty()) {
                    result.add(urgentQueue.removeFirst());
                } else if (!normalQueue.isEmpty()) {
                    result.add(normalQueue.removeFirst());
                } else {
                    result.add("EMPTY");
                }

                continue;
            }

            String[] parts =
                    trimmed.split("\\s+");

            if (parts.length != 2
                    || parts[1].isEmpty()) {
                continue;
            }

            if (parts[0].equals("NORMAL")) {
                normalQueue.addLast(parts[1]);
            } else if (parts[0].equals("URGENT")) {
                urgentQueue.addLast(parts[1]);
            }
        }

        return result;
    }
}