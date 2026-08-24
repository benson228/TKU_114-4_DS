import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseCollectionManager {

    static class Enrollment {
        private final String studentId;
        private final String name;
        private final String tag;
        private int score;

        public Enrollment(
                String studentId,
                String name,
                String tag,
                int score) {

            this.studentId = studentId;
            this.name = name;
            this.tag = tag;
            this.score = score;
        }

        public String getStudentId() {
            return studentId;
        }

        public String getName() {
            return name;
        }

        public String getTag() {
            return tag;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Enrollment other = (Enrollment) obj;

            return studentId.equals(other.studentId);
        }

        @Override
        public int hashCode() {
            return studentId.hashCode();
        }

        @Override
        public String toString() {
            return "studentId=" + studentId
                    + ", name=" + name
                    + ", tag=" + tag
                    + ", score=" + score;
        }
    }

    private final List<Enrollment> enrollmentList;
    private final Set<Enrollment> enrollmentSet;
    private final Map<String, Enrollment> enrollmentMap;

    public CourseCollectionManager() {
        enrollmentList = new ArrayList<>();
        enrollmentSet = new HashSet<>();
        enrollmentMap = new HashMap<>();
    }

    public boolean add(Enrollment enrollment) {
        if (enrollment == null
                || enrollment.getStudentId() == null
                || enrollment.getStudentId().isBlank()) {
            return false;
        }

        if (!enrollmentSet.add(enrollment)) {
            return false;
        }

        enrollmentList.add(enrollment);
        enrollmentMap.put(
                enrollment.getStudentId(),
                enrollment);

        return true;
    }

    public boolean updateScore(String studentId, int score) {
        Enrollment enrollment = enrollmentMap.get(studentId);

        if (enrollment == null) {
            return false;
        }

        enrollment.setScore(score);
        return true;
    }

    public List<Enrollment> findByTag(String tag) {
        List<Enrollment> result = new ArrayList<>();

        if (tag == null || tag.isBlank()) {
            return result;
        }

        for (Enrollment enrollment : enrollmentList) {
            if (tag.equals(enrollment.getTag())) {
                result.add(enrollment);
            }
        }

        return result;
    }

    public Map<String, Integer> scoreDistribution() {
        Map<String, Integer> distribution =
                new HashMap<>();

        distribution.put("A", 0);
        distribution.put("B", 0);
        distribution.put("C", 0);
        distribution.put("D", 0);
        distribution.put("F", 0);

        for (Enrollment enrollment : enrollmentList) {
            String grade = getGrade(enrollment.getScore());

            distribution.put(
                    grade,
                    distribution.get(grade) + 1);
        }

        return distribution;
    }

    private String getGrade(int score) {
        if (score >= 90) {
            return "A";
        }

        if (score >= 80) {
            return "B";
        }

        if (score >= 70) {
            return "C";
        }

        if (score >= 60) {
            return "D";
        }

        return "F";
    }

    public List<Enrollment> top(int count) {
        List<Enrollment> result =
                new ArrayList<>(enrollmentList);

        result.sort(
                Comparator.comparingInt(
                        Enrollment::getScore)
                .reversed());

        if (count < 0) {
            return new ArrayList<>();
        }

        if (count >= result.size()) {
            return result;
        }

        return new ArrayList<>(
                result.subList(0, count));
    }

    public int removeBelow(int minimum) {
        List<Enrollment> removed =
                new ArrayList<>();

        for (Enrollment enrollment : enrollmentList) {
            if (enrollment.getScore() < minimum) {
                removed.add(enrollment);
            }
        }

        for (Enrollment enrollment : removed) {
            enrollmentList.remove(enrollment);
            enrollmentSet.remove(enrollment);
            enrollmentMap.remove(
                    enrollment.getStudentId());
        }

        return removed.size();
    }

    public void printAll() {
        for (Enrollment enrollment : enrollmentList) {
            System.out.println(enrollment);
        }
    }

    public int size() {
        return enrollmentList.size();
    }

    public static void main(String[] args) {
        CourseCollectionManager manager =
                new CourseCollectionManager();

        manager.add(new Enrollment(
                "S001", "Amy", "Java", 95));

        manager.add(new Enrollment(
                "S002", "Bob", "Data", 82));

        manager.add(new Enrollment(
                "S003", "Charlie", "Java", 76));

        manager.add(new Enrollment(
                "S004", "David", "", 76));

        manager.add(new Enrollment(
                "S005", "Eva", "Collection", 91));

        manager.add(new Enrollment(
                "S006", "Frank", "Java", 58));

        System.out.println("=== 所有報名資料 ===");
        manager.printAll();

        System.out.println("\n=== 更新成績 ===");
        System.out.println(
                "S006 更新：" +
                manager.updateScore("S006", 65));

        System.out.println("\n=== Java 標籤 ===");
        for (Enrollment enrollment
                : manager.findByTag("Java")) {
            System.out.println(enrollment);
        }

        System.out.println("\n=== 成績分布 ===");
        System.out.println(
                manager.scoreDistribution());

        System.out.println("\n=== Top 3 ===");
        for (Enrollment enrollment
                : manager.top(3)) {
            System.out.println(enrollment);
        }

        System.out.println("\n=== 移除 70 分以下 ===");
        System.out.println(
                "移除數量：" +
                manager.removeBelow(70));

        System.out.println("\n=== 移除後 ===");
        manager.printAll();

        System.out.println(
                "List/Set/Map 資料數量：" +
                manager.size());
    }
}