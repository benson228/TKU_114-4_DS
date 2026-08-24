import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListImplementationLab {

    static void addEnd(List<Integer> list, int value) {
        list.add(value);
    }

    static void insertAt(List<Integer> list, int index, int value) {
        if (index < 0 || index > list.size()) {
            return;
        }

        list.add(index, value);
    }

    static int search(List<Integer> list, int target) {
        return list.indexOf(target);
    }

    static boolean removeAt(List<Integer> list, int index) {
        if (index < 0 || index >= list.size()) {
            return false;
        }

        list.remove(index);
        return true;
    }

    static int sum(List<Integer> list) {
        int total = 0;

        for (int value : list) {
            total += value;
        }

        return total;
    }

    static void testList(String name, List<Integer> list) {
        System.out.println("=== " + name + " ===");

        addEnd(list, 10);
        addEnd(list, 20);
        addEnd(list, 30);

        System.out.println("尾端新增：" + list);

        insertAt(list, 1, 15);
        System.out.println("位置 1 插入 15：" + list);

        System.out.println("搜尋 20 的位置：" +
                search(list, 20));

        System.out.println("刪除位置 2：" +
                removeAt(list, 2));

        System.out.println("刪除後：" + list);

        System.out.println("總和：" + sum(list));

        System.out.println();
    }

    public static void main(String[] args) {
        List<Integer> arrayList =
                new ArrayList<>();

        List<Integer> linkedList =
                new LinkedList<>();

        testList("ArrayList", arrayList);
        testList("LinkedList", linkedList);

        System.out.println("=== 內部成本差異 ===");
        System.out.println(
                "ArrayList：隨機存取通常較快，"
                + "中間插入或刪除可能需要搬移元素。");

        System.out.println(
                "LinkedList：中間插入或刪除若已找到節點，"
                + "不需要搬移大量元素，但搜尋與隨機存取較慢。");
    }
}