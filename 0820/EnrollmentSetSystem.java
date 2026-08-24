import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EnrollmentSetSystem {

    static class Enrollment {
        private final String studentId;
        private final String courseCode;

        public Enrollment(String studentId, String courseCode) {
            this.studentId = studentId;
            this.courseCode = courseCode;
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

            return Objects.equals(studentId, other.studentId)
                    && Objects.equals(courseCode, other.courseCode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, courseCode);
        }

        @Override
        public String toString() {
            return "studentId=" + studentId
                    + ", courseCode=" + courseCode;
        }
    }

    public static void main(String[] args) {
        Set<Enrollment> enrollments = new HashSet<>();

        Enrollment e1 =
                new Enrollment("S001", "CS101");

        Enrollment e2 =
                new Enrollment("S001", "CS102");

        Enrollment duplicate =
                new Enrollment("S001", "CS101");

        System.out.println("=== 新增 ===");

        System.out.println("加入 e1：" +
                enrollments.add(e1));

        System.out.println("加入 e2：" +
                enrollments.add(e2));

        System.out.println("再次加入相同身分：" +
                enrollments.add(duplicate));

        System.out.println("\n目前報名資料：");
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }

        Enrollment sameIdentity =
                new Enrollment("S001", "CS101");

        System.out.println("\n=== contains ===");
        System.out.println(
                "contains：" +
                enrollments.contains(sameIdentity)
        );

        System.out.println("\n=== remove ===");
        System.out.println(
                "remove：" +
                enrollments.remove(sameIdentity)
        );

        System.out.println("\n移除後：");
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }

        System.out.println("\n剩餘數量：" +
                enrollments.size());
    }
}
