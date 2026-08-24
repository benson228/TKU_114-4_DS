import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionChoiceReport {

    public static void main(String[] args) {

        List<String> searchHistory =
                new ArrayList<>();

        searchHistory.add("Java");
        searchHistory.add("Data Structure");
        searchHistory.add("Java");

        System.out.println("=== 1. 搜尋紀錄 ===");
        System.out.println("Interface：List");
        System.out.println("Implementation：ArrayList");
        System.out.println("資料：" + searchHistory);
        System.out.println("允許重複：" +
                searchHistory.size());

        Set<String> memberIds =
                new HashSet<>();

        memberIds.add("M001");
        memberIds.add("M002");
        memberIds.add("M001");

        System.out.println("\n=== 2. 不重複會員編號 ===");
        System.out.println("Interface：Set");
        System.out.println("Implementation：HashSet");
        System.out.println("資料：" + memberIds);

        Map<String, Integer> scores =
                new HashMap<>();

        scores.put("S001", 85);
        scores.put("S002", 92);
        scores.put("S003", 78);

        System.out.println("\n=== 3. 以學號查詢成績 ===");
        System.out.println("Interface：Map");
        System.out.println("Implementation：HashMap");
        System.out.println("S002 成績：" +
                scores.get("S002"));

        Deque<String> printQueue =
                new ArrayDeque<>();

        printQueue.offerLast("Document A");
        printQueue.offerLast("Document B");
        printQueue.offerLast("Document C");

        System.out.println("\n=== 4. 列印工作 ===");
        System.out.println("Interface：Deque");
        System.out.println("Implementation：ArrayDeque");

        System.out.println("處理：" +
                printQueue.pollFirst());

        System.out.println("處理：" +
                printQueue.pollFirst());

        System.out.println("剩餘：" + printQueue);

        Deque<String> operationStack =
                new ArrayDeque<>();

        operationStack.push("新增資料");
        operationStack.push("修改資料");
        operationStack.push("刪除資料");

        System.out.println("\n=== 5. 復原最近操作 ===");
        System.out.println("Interface：Deque");
        System.out.println("Implementation：ArrayDeque");
        System.out.println("Undo：" +
                operationStack.pop());

        System.out.println("剩餘操作：" +
                operationStack);
    }
}