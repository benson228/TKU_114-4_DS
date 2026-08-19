public class CourseComposition {
    static class Instructor {
        private String id;
        private String name;

        public Instructor(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    static class Course {
        private String courseCode;
        private String title;
        private Instructor instructor;

        public Course(String courseCode, String title, Instructor instructor) {
            this.courseCode = courseCode;
            this.title = title;
            this.instructor = instructor;
        }

        public String summary() {
            return "課程代碼：" + courseCode
                    + "，課程名稱：" + title
                    + "，授課教師：" + instructor.getId()
                    + " - " + instructor.getName();
        }
    }

    public static void main(String[] args) {
        Instructor instructor =
                new Instructor("I001", "王老師");

        Course course1 =
                new Course("CS101", "Java 程式設計", instructor);

        Course course2 =
                new Course("CS102", "物件導向程式設計", instructor);

        System.out.println(course1.summary());
        System.out.println(course2.summary());
    }
}