import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

public class CourseGradeMap {
    private final Map<String, List<Integer>> grades = new HashMap<>();

    public boolean addGrade(String course, int score) {
        if (course == null || course.isBlank()) {
            return false;
        }

        grades.computeIfAbsent(course.trim(), k -> new ArrayList<>()).add(score);
        return true;
    }

    public double average(String course) {
        List<Integer> list = grades.get(course);

        if (list == null || list.isEmpty()) {
            return 0.0;
        }

        int sum = 0;

        for (int score : list) {
            sum += score;
        }

        return (double) sum / list.size();
    }

    public int highest(String course) {
        List<Integer> list = grades.get(course);

        if (list == null || list.isEmpty()) {
            return 0;
        }

        return Collections.max(list);
    }

    public List<String> report() {
        List<String> courses = new ArrayList<>(grades.keySet());
        Collections.sort(courses);

        List<String> result = new ArrayList<>();

        for (String course : courses) {
            result.add(
                course + "|" + average(course) + "|" + highest(course)
            );
        }

        return result;
    }

    public List<Integer> gradesOf(String course) {
        List<Integer> list = grades.get(course);

        if (list == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(list);
    }
}
