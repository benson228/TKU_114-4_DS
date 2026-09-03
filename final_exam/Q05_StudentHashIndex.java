import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q05_StudentHashIndex {

    private final Map<String, Set<String>> studentCourses = new HashMap<>();
    private final Map<String, Set<String>> courseStudents = new HashMap<>();
    private int enrollmentCount;

    public boolean enroll(String studentId, String courseId) {
        String student = normalize(studentId);
        String course = normalize(courseId);

        if (student == null || course == null) {
            return false;
        }

        Set<String> courses = studentCourses.computeIfAbsent(student, k -> new HashSet<>());

        if (!courses.add(course)) {
            return false;
        }

        courseStudents
                .computeIfAbsent(course, k -> new HashSet<>())
                .add(student);

        enrollmentCount++;
        return true;
    }

    public boolean drop(String studentId, String courseId) {
        String student = normalize(studentId);
        String course = normalize(courseId);

        if (student == null || course == null) {
            return false;
        }

        Set<String> courses = studentCourses.get(student);

        if (courses == null || !courses.remove(course)) {
            return false;
        }

        if (courses.isEmpty()) {
            studentCourses.remove(student);
        }

        Set<String> students = courseStudents.get(course);

        if (students != null) {
            students.remove(student);

            if (students.isEmpty()) {
                courseStudents.remove(course);
            }
        }

        enrollmentCount--;
        return true;
    }

    public Set<String> coursesOf(String studentId) {
        String student = normalize(studentId);

        if (student == null) {
            return Set.of();
        }

        Set<String> courses = studentCourses.get(student);

        if (courses == null) {
            return Set.of();
        }

        return Set.copyOf(courses);
    }

    public Set<String> studentsIn(String courseId) {
        String course = normalize(courseId);

        if (course == null) {
            return Set.of();
        }

        Set<String> students = courseStudents.get(course);

        if (students == null) {
            return Set.of();
        }

        return Set.copyOf(students);
    }

    public int enrollmentCount() {
        return enrollmentCount;
    }

    private String normalize(String value) {
        if (value == null) {
            return null;
        }

        String result = value.trim().toUpperCase();

        if (result.isEmpty()) {
            return null;
        }

        return result;
    }
}
