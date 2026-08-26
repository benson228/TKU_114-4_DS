import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Q06_EnrollmentIndex {

    private final Map<String, Set<String>> enrollments;

    public Q06_EnrollmentIndex() {
        enrollments = new TreeMap<>();
    }

    public boolean enroll(String courseCode, String studentId) {
        if (courseCode == null
                || courseCode.trim().isEmpty()
                || studentId == null
                || studentId.trim().isEmpty()) {
            return false;
        }

        courseCode = courseCode.trim();
        studentId = studentId.trim();

        Set<String> students =
                enrollments.computeIfAbsent(
                        courseCode,
                        key -> new TreeSet<>());

        return students.add(studentId);
    }

    public boolean drop(String courseCode, String studentId) {
        if (courseCode == null
                || courseCode.trim().isEmpty()
                || studentId == null
                || studentId.trim().isEmpty()) {
            return false;
        }

        courseCode = courseCode.trim();
        studentId = studentId.trim();

        Set<String> students =
                enrollments.get(courseCode);

        if (students == null) {
            return false;
        }

        boolean removed = students.remove(studentId);

        if (students.isEmpty()) {
            enrollments.remove(courseCode);
        }

        return removed;
    }

    public int courseSize(String courseCode) {
        if (courseCode == null
                || courseCode.trim().isEmpty()) {
            return 0;
        }

        Set<String> students =
                enrollments.get(courseCode.trim());

        return students == null ? 0 : students.size();
    }

    public List<String> studentsOf(String courseCode) {
        List<String> result = new ArrayList<>();

        if (courseCode == null
                || courseCode.trim().isEmpty()) {
            return result;
        }

        Set<String> students =
                enrollments.get(courseCode.trim());

        if (students != null) {
            result.addAll(students);
        }

        return result;
    }

    public List<String> coursesOf(String studentId) {
        List<String> result = new ArrayList<>();

        if (studentId == null
                || studentId.trim().isEmpty()) {
            return result;
        }

        String id = studentId.trim();

        for (Map.Entry<String, Set<String>> entry
                : enrollments.entrySet()) {

            if (entry.getValue().contains(id)) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    public Map<String, Integer> summary() {
        Map<String, Integer> result = new TreeMap<>();

        for (Map.Entry<String, Set<String>> entry
                : enrollments.entrySet()) {

            result.put(
                    entry.getKey(),
                    entry.getValue().size());
        }

        return result;
    }
}