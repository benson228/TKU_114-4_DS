import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SocialNetworkGraph {
    private final Map<String, Set<String>> friends =
            new LinkedHashMap<>();

    public boolean addUser(String user) {
        if (user == null || user.isBlank()) {
            return false;
        }

        String name = user.trim();

        if (friends.containsKey(name)) {
            return false;
        }

        friends.put(name, new LinkedHashSet<>());
        return true;
    }

    public boolean addFriend(String first, String second) {
        if (!friends.containsKey(first)
                || !friends.containsKey(second)
                || first.equals(second)) {
            return false;
        }

        boolean changed = friends.get(first).add(second);
        friends.get(second).add(first);

        return changed;
    }

    public boolean removeFriend(String first, String second) {
        if (!friends.containsKey(first)
                || !friends.containsKey(second)) {
            return false;
        }

        boolean changed = friends.get(first).remove(second);
        friends.get(second).remove(first);

        return changed;
    }

    public List<String> friendsOf(String user) {
        Set<String> set = friends.get(user);

        if (set == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(set);
    }

    public List<String> commonFriends(
            String first,
            String second) {

        List<String> result = new ArrayList<>();

        if (!friends.containsKey(first)
                || !friends.containsKey(second)) {
            return result;
        }

        Set<String> firstFriends = friends.get(first);
        Set<String> secondFriends = friends.get(second);

        for (String user : firstFriends) {
            if (secondFriends.contains(user)) {
                result.add(user);
            }
        }

        return result;
    }

    public List<String> isolatedUsers() {
        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Set<String>> entry : friends.entrySet()) {
            if (entry.getValue().isEmpty()) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    public int userCount() {
        return friends.size();
    }
}