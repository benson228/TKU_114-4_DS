import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseTagReport {

    public static void main(String[] args) {
        List<String> tags = new ArrayList<>();

        tags.add("Java");
        tags.add("資料結構");
        tags.add("Java");
        tags.add("演算法");
        tags.add("Java");
        tags.add("資料結構");
        tags.add("Git");

        Set<String> uniqueTags = new HashSet<>(tags);

        Map<String, Integer> tagCounts = new HashMap<>();

        for (String tag : tags) {
            tagCounts.put(
                    tag,
                    tagCounts.getOrDefault(tag, 0) + 1
            );
        }

        System.out.println("=== List：原始順序 ===");
        System.out.println(tags);

        System.out.println("\n=== Set：不重複標籤 ===");
        System.out.println(uniqueTags);

        System.out.println("\n=== Map：標籤次數 ===");
        System.out.println(tagCounts);

        System.out.println("\n=== 用途說明 ===");
        System.out.println("List：保存原始資料與順序");
        System.out.println("Set：保存不重複的標籤");
        System.out.println("Map：保存每個標籤的出現次數");
    }
}