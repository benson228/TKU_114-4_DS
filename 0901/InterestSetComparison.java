import java.util.HashSet;
import java.util.Set;

public class InterestSetComparison {

    public static Set<String> union(
            Set<String> first,
            Set<String> second) {

        Set<String> result = new HashSet<>();

        if (first != null) {
            result.addAll(first);
        }

        if (second != null) {
            result.addAll(second);
        }

        return result;
    }

    public static Set<String> intersection(
            Set<String> first,
            Set<String> second) {

        Set<String> result = new HashSet<>();

        if (first == null || second == null) {
            return result;
        }

        result.addAll(first);
        result.retainAll(second);

        return result;
    }

    public static Set<String> firstOnly(
            Set<String> first,
            Set<String> second) {

        Set<String> result = new HashSet<>();

        if (first == null) {
            return result;
        }

        result.addAll(first);

        if (second != null) {
            result.removeAll(second);
        }

        return result;
    }

    public static Set<String> secondOnly(
            Set<String> first,
            Set<String> second) {

        Set<String> result = new HashSet<>();

        if (second == null) {
            return result;
        }

        result.addAll(second);

        if (first != null) {
            result.removeAll(first);
        }

        return result;
    }
}
