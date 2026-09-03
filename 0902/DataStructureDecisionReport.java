import java.util.ArrayList;
import java.util.List;

record Decision(
        String requirement,
        String structure,
        String reason,
        String bigO) {}

public class DataStructureDecisionReport {

    public static Decision decide(String requirement) {

        if (requirement == null
                || requirement.trim().isEmpty()) {
            return new Decision(
                    "",
                    "Unknown",
                    "需求為空，無法判斷",
                    "N/A");
        }

        String text = requirement.trim().toLowerCase();

        if (text.contains("priority")
                || text.contains("優先")
                || text.contains("最高優先")) {

            return new Decision(
                    requirement,
                    "PriorityQueue / Heap",
                    "需要快速取得最高或最低優先權元素",
                    "peek O(1), insert/remove O(log n)");
        }

        if (text.contains("fifo")
                || text.contains("queue")
                || text.contains("先進先出")) {

            return new Decision(
                    requirement,
                    "Queue",
                    "符合先進先出的處理順序",
                    "offer/poll O(1)");
        }

        if (text.contains("lifo")
                || text.contains("stack")
                || text.contains("後進先出")) {

            return new Decision(
                    requirement,
                    "Stack",
                    "符合後進先出的處理順序",
                    "push/pop O(1)");
        }

        if (text.contains("sorted")
                || text.contains("排序")
                || text.contains("range")
                || text.contains("範圍查詢")) {

            return new Decision(
                    requirement,
                    "BST",
                    "需要維持有序資料並支援搜尋或範圍查詢",
                    "average search O(log n), range O(log n + k)");
        }

        if (text.contains("key")
                || text.contains("id")
                || text.contains("快速查找")
                || text.contains("lookup")) {

            return new Decision(
                    requirement,
                    "Hash Table",
                    "以 key 快速查找資料",
                    "average get/put/remove O(1)");
        }

        if (text.contains("path")
                || text.contains("reachable")
                || text.contains("graph")
                || text.contains("路徑")
                || text.contains("連通")) {

            return new Decision(
                    requirement,
                    "Graph",
                    "資料之間具有節點與連線關係",
                    "BFS/DFS O(V + E)");
        }

        if (text.contains("list")
                || text.contains("sequence")
                || text.contains("序列")) {

            return new Decision(
                    requirement,
                    "List",
                    "資料主要以線性順序儲存與走訪",
                    "append O(1) amortized, search O(n)");
        }

        return new Decision(
                requirement,
                "List",
                "若需求沒有明確的特殊操作，線性結構較簡單",
                "search O(n)");
    }

    public static List<Decision> report(
            List<String> requirements) {

        List<Decision> result = new ArrayList<>();

        if (requirements == null) {
            return result;
        }

        for (String requirement : requirements) {
            result.add(decide(requirement));
        }

        return result;
    }

    public static List<String> formatReport(
            List<String> requirements) {

        List<String> result = new ArrayList<>();

        for (Decision decision : report(requirements)) {
            result.add(
                    "需求: " + decision.requirement()
                    + " | 選擇: " + decision.structure()
                    + " | 理由: " + decision.reason()
                    + " | Big-O: " + decision.bigO()
            );
        }

        return result;
    }
}