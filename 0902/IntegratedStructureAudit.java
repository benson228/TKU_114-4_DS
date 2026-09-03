import java.util.ArrayList;
import java.util.List;

record AuditScenario(
        String requirement,
        String expectedStructure) {}

record AuditResult(
        String requirement,
        String selectedStructure,
        boolean reasonable,
        String diagnostic) {}

public class IntegratedStructureAudit {

    public static AuditResult audit(
            AuditScenario scenario) {

        if (scenario == null
                || scenario.requirement() == null
                || scenario.requirement().trim().isEmpty()) {

            return new AuditResult(
                    "",
                    "Unknown",
                    false,
                    "缺少有效需求");
        }

        String requirement =
                scenario.requirement().trim();

        String expected =
                scenario.expectedStructure() == null
                        ? ""
                        : scenario.expectedStructure()
                                .trim();

        String selected =
                chooseStructure(requirement);

        boolean reasonable =
                expected.isEmpty()
                        || selected.equalsIgnoreCase(expected);

        String diagnostic;

        if (reasonable) {
            diagnostic =
                    "選擇合理，符合需求的主要操作";
        } else {
            diagnostic =
                    "選擇與預期資料結構不同，應重新檢查操作需求";
        }

        return new AuditResult(
                requirement,
                selected,
                reasonable,
                diagnostic);
    }

    public static List<AuditResult> auditAll(
            List<AuditScenario> scenarios) {

        List<AuditResult> result = new ArrayList<>();

        if (scenarios == null) {
            return result;
        }

        for (AuditScenario scenario : scenarios) {
            result.add(audit(scenario));
        }

        return result;
    }

    private static String chooseStructure(
            String requirement) {

        String text =
                requirement.toLowerCase();

        if (text.contains("priority")
                || text.contains("優先")
                || text.contains("highest")) {
            return "Heap";
        }

        if (text.contains("fifo")
                || text.contains("queue")
                || text.contains("先進先出")) {
            return "Queue";
        }

        if (text.contains("sorted")
                || text.contains("bst")
                || text.contains("range")
                || text.contains("排序")
                || text.contains("範圍")) {
            return "BST";
        }

        if (text.contains("key")
                || text.contains("hash")
                || text.contains("快速查找")
                || text.contains("lookup")) {
            return "Hash Table";
        }

        if (text.contains("path")
                || text.contains("graph")
                || text.contains("reachable")
                || text.contains("路徑")
                || text.contains("連通")) {
            return "Graph";
        }

        if (text.contains("stack")
                || text.contains("lifo")
                || text.contains("後進先出")) {
            return "Stack";
        }

        return "List";
    }

    public static String summary(
            List<AuditResult> results) {

        if (results == null || results.isEmpty()) {
            return "沒有可分析的測試情境";
        }

        int reasonable = 0;

        for (AuditResult result : results) {
            if (result != null && result.reasonable()) {
                reasonable++;
            }
        }

        return "合理選擇: "
                + reasonable
                + "/"
                + results.size();
    }
}