import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EnrollmentCleanup {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        names.add("Amy");
        names.add("Bob");
        names.add("Amy");
        names.add(null);
        names.add(" ");
        names.add("Charlie");
        names.add("Bob");
        names.add("");
        names.add("David");

        System.out.println("=== 清理前 ===");
        System.out.println(names);

        Iterator<String> iterator = names.iterator();

        while (iterator.hasNext()) {
            String name = iterator.next();

            if (name == null || name.isBlank()) {
                iterator.remove();
            }
        }

        System.out.println("\n=== 清理後 ===");
        System.out.println(names);

        Set<String> seen = new HashSet<>();
        Set<String> duplicates = new HashSet<>();

        for (String name : names) {
            if (!seen.add(name)) {
                duplicates.add(name);
            }
        }

        System.out.println("\n=== 重複報告 ===");

        if (duplicates.isEmpty()) {
            System.out.println("沒有重複姓名");
        } else {
            for (String name : duplicates) {
                System.out.println("重複姓名：" + name);
            }
        }
    }
}