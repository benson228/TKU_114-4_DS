public class CourseGradeManager {
    static class CourseGrade {
        private String studentId;
        private String name;
        private double participation;
        private double midterm;
        private double finalExam;
        private double attendance;

        public CourseGrade(String studentId, String name, double participation,
                           double midterm, double finalExam, double attendance) {
            this.studentId = studentId;
            this.name = name;
            this.participation = clamp(participation);
            this.midterm = clamp(midterm);
            this.finalExam = clamp(finalExam);
            this.attendance = clamp(attendance);
        }

        private double clamp(double score) {
            return Math.max(0, Math.min(100, score));
        }

        public double calculateFinalScore() {
            return participation * 0.5
                    + midterm * 0.2
                    + finalExam * 0.2
                    + attendance * 0.1;
        }

        public String getLevel() {
            double score = calculateFinalScore();

            if (score >= 90) {
                return "A";
            } else if (score >= 80) {
                return "B";
            } else if (score >= 70) {
                return "C";
            } else if (score >= 60) {
                return "D";
            } else {
                return "E";
            }
        }

        public String getStudentId() {
            return studentId;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return String.format(
                "學號：%s，姓名：%s，平時：%.1f，期中：%.1f，期末：%.1f，出席：%.1f，總分：%.2f，等級：%s",
                studentId, name, participation, midterm, finalExam,
                attendance, calculateFinalScore(), getLevel()
            );
        }
    }

    public static void main(String[] args) {
        CourseGrade[] grades = {
            new CourseGrade("S001", "王小明", 92, 88, 90, 95),
            new CourseGrade("S002", "陳小華", 80, 75, 78, 85),
            new CourseGrade("S003", "李小美", 65, 70, 68, 80),
            new CourseGrade("S004", "林大偉", 95, 92, 96, 98),
            new CourseGrade("S005", "張雅婷", 50, 55, 58, 60)
        };

        double sum = 0;
        double highest = grades[0].calculateFinalScore();

        for (CourseGrade grade : grades) {
            double score = grade.calculateFinalScore();
            sum += score;

            if (score > highest) {
                highest = score;
            }
        }

        double average = sum / grades.length;

        System.out.println("所有學生資料：");

        for (CourseGrade grade : grades) {
            System.out.println(grade);
        }

        System.out.printf("%n平均分數：%.2f%n", average);
        System.out.printf("最高分數：%.2f%n", highest);

        System.out.println("不及格名單：");

        boolean hasFailed = false;

        for (CourseGrade grade : grades) {
            if (grade.calculateFinalScore() < 60) {
                System.out.println(
                    grade.getStudentId() + " - " + grade.getName()
                );
                hasFailed = true;
            }
        }

        if (!hasFailed) {
            System.out.println("無");
        }
    }
}